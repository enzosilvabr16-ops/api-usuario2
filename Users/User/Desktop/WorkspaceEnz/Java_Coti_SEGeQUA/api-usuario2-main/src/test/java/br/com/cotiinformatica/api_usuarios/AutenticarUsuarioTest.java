package br.com.cotiinformatica.api_usuarios;

import br.com.cotiinformatica.api_usuarios.dtos.AutenticarUsuarioRequest;
import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.assertj.core.api.Fail.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Testes de integração para autenticação de usuários")
public class AutenticarUsuarioTest extends ApiUsuariosApplicationTests {

    @Test
    @DisplayName("Deve autenticar um usuário válido com sucesso")
    public void autenticarUsuarioComSucesso() throws Exception {

        var email = "usuario_autenticar"+ UUID.randomUUID() +"@gmail.com";

        //criar usuário primeiro
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

        //agora autenticar
        var auth = new AutenticarUsuarioRequest(email, "@Teste2026");
        var jsonAuth = objectMapper.writeValueAsString(auth);

        mockMvc.perform(post("/api/v1/usuarios/autenticar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAuth))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.mensagem").value("Usuário autenticado com sucesso"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.token").isNotEmpty());
    }

    @Test
    @DisplayName("Não deve autenticar um usuário inválido")
    public void acessoNegadoUsuarioInvalido() throws Exception {

        var auth = new AutenticarUsuarioRequest("inexistente"+ UUID.randomUUID() +"@gmail.com", "SenhaErrada1$");
        var jsonAuth = objectMapper.writeValueAsString(auth);

        mockMvc.perform(post("/api/v1/usuarios/autenticar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonAuth))
                .andExpect(status().isUnauthorized())
                .andExpect(MockMvcResultMatchers.content().string("Acesso negado. Verifique os dados informados."));
    }
}
