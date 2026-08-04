package br.com.cotiinformatica.api_usuarios.configurations;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI apiDocumentation() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Usuários - COTI Informática")
                        .version("1.0.0")
                        .description("API para gerenciamento de usuários.")
                        .contact(new Contact().name("COTI Informática"))
                );
    }
}
