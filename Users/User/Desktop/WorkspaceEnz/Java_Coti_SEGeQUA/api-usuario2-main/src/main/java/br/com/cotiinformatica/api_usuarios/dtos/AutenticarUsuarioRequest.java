package br.com.cotiinformatica.api_usuarios.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AutenticarUsuarioRequest(

        @NotBlank(message = "O email de acesso do usuário é obrigatório.")
        @Email(message = "Informe um endereço de email válido.")
        String email,

        @NotBlank(message = "A senha de acesso do usuário é obrigatória.")
        @Size(min = 8, max = 20, message = "A senha deve conter de 8 a 20 caracteres.")
        String senha
) {
}

