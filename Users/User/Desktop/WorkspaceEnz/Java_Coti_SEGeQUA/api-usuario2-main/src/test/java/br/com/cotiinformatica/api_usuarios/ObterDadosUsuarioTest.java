package br.com.cotiinformatica.api_usuarios;

import br.com.cotiinformatica.api_usuarios.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.assertj.core.api.Fail.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ObterDadosUsuarioTest extends ApiUsuariosApplicationTests {

    @Test
    @DisplayName("Deve obter dados do usuario autenticado com sucesso")
    public void obterDadosComSucesso() throws Exception {

        var email = "usuario_dados" + UUID.randomUUID() + "@gmail.com";

        //criar usuário
        var criar = new CriarUsuarioRequest(
                "Usuário teste",
                email,
                "@Teste2026"
        );

        var jsonCriar = objectMapper.writeValueAsString(criar);

        mockMvc.perform(post("/api/v1/usuarios/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonCriar))
                .andExpect(status().isCreated());

        //autenticar para obter token
        var auth = new AutenticarUsuarioRequest(email, "@Teste2026");
        var jsonAuth = objectMapper.writeValueAsString(auth);

        var authResponse = mockMvc.perform(post("/api/v1/usuarios/autenticar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAuth))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();

        var token = objectMapper.readTree(authResponse).get("token").asText();

        //chamada para obter dados com header Authorization
        mockMvc.perform(get("/api/v1/usuarios/obter-dados")
                        .header("Authorization", "Bearer " + token))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.email").value(email))
                .andExpect(MockMvcResultMatchers.jsonPath("$.nome").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("$.perfil").isNotEmpty());
    }

    @Test
    @DisplayName("Não deve obter dados para usuario não autenticado.")
    public void obterDadosUsuarioNaoAutenticado() throws Exception {

        //sem header Authorization -> filtro retorna 401
        mockMvc.perform(get("/api/v1/usuarios/obter-dados"))
                .andExpect(status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.status().reason("Acesso não autorizado."));
    }

}
