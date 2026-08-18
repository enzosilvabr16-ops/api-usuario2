package cotiinformatica.api_produtos.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

/**
 * Swagger / OpenAPI configuration for the API.
 *
 * Projeto: API de Produtos
 * Descrição: API para gerenciar produtos — endpoints para criar, listar, atualizar e remover produtos.
 * Autor: COTI Informática
 * Versão: 1.0.0
 */
@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Produtos")
                        .version("1.0.0")
                        .description("API para gerenciar produtos — endpoints para criar, listar, atualizar e remover produtos.")
                        .contact(new Contact().name("COTI Informática"))

                );
    }

}
