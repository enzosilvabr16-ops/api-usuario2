package br.com.cotiinformatica.api_usuarios;

import br.com.cotiinformatica.api_usuarios.dtos.CriarUsuarioRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.UUID;

import static org.assertj.core.api.Fail.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("Testes de integração para criação de usuário.")
public class CriarUsuarioTest extends ApiUsuariosApplicationTests {

    @Test
    @DisplayName("Deve criar um usuário válido com sucesso.")
    public void criarUsuarioComSucesso() throws Exception {

        //Preenchendo os dados do DTO para criar um usuário
        var request = new CriarUsuarioRequest(
                "Usuário teste",
                "usuario"+ UUID.randomUUID() +"@gmail.com",
                "@Teste2026"
        );

        //Transformando os dados em JSON
        var json = objectMapper.writeValueAsString(request);

        //Fazendo uma chamada para o ENDPOINT de cadastro de usuário na API
        mockMvc.perform(post("/api/v1/usuarios/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated()); //Deve retornar HTTP 201
    }

    @Test
    @DisplayName("Não deve criar um usuário com email já existente.")
    public void criarUsuarioEmailExistente() throws Exception {

        var email = "usuario_existente"+ UUID.randomUUID() +"@gmail.com";
        var request = new CriarUsuarioRequest(
                "Usuário teste",
                email,
                "@Teste2026"
        );

        var json = objectMapper.writeValueAsString(request);

        //Primeiro cadastro deve dar certo
        mockMvc.perform(post("/api/v1/usuarios/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isCreated());

        //Segunda tentativa com o mesmo email deve retornar conflito (409)
        mockMvc.perform(post("/api/v1/usuarios/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isConflict())
                .andExpect(MockMvcResultMatchers.content().string("O email informado já existe. Tente outro."));
    }

    @Test
    @DisplayName("Não deve criar um usuário com senha fraca.")
    public void criarUsuarioSenhaFraca() throws Exception {

        var request = new CriarUsuarioRequest(
                "Usuário teste",
                "usuario"+ UUID.randomUUID() +"@gmail.com",
                "senha123" //fraca: sem letra maiúscula e sem caractere especial
        );

        var json = objectMapper.writeValueAsString(request);

        mockMvc.perform(post("/api/v1/usuarios/criar")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string(org.hamcrest.Matchers.containsString(
                        "A senha deve conter letra maiúscula, minúscula, número e caractere especial, sem espaços"
                )));
    }
}
