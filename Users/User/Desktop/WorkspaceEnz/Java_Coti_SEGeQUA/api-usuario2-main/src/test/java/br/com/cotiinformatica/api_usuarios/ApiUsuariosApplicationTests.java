package br.com.cotiinformatica.api_usuarios;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test") //Executando o /application-test.yaml
class ApiUsuariosApplicationTests {

	//Permite executar os endpoints da API
	@Autowired
	protected MockMvc mockMvc;

	//Permite manipular dados em formato JSON
	@Autowired
	protected ObjectMapper objectMapper;
}
