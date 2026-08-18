package br.com.cotiinformatica.api_usuarios.configurations;

import br.com.cotiinformatica.api_usuarios.filters.AuthenticationFilter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthConfiguration {

    @Value("${jwt.secretkey}")
    private String secretKey;

    @Bean
    public FilterRegistrationBean<AuthenticationFilter> registrationFilter() {

        //Registrando o filter de autenticação
        var filter = new FilterRegistrationBean<AuthenticationFilter>();
        filter.setFilter(new AuthenticationFilter(secretKey));

        //Aplicando o filtro para os endpoints desejados
        filter.addUrlPatterns("/api/v1/usuarios/obter-dados");

        return filter;
    }
}

