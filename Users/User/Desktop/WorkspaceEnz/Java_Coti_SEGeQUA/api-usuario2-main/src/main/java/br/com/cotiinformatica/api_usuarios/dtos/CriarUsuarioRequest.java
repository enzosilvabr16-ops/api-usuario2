package br.com.cotiinformatica.api_usuarios.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CriarUsuarioRequest(

        @NotBlank(message = "O nome é obrigatório.")
        @Size(min = 8, max =150, message = "O nome do usuário deve ter de 8 a 150 caracteres")
        String nome,

        @NotBlank(message = "O email é obrigatório.")
        @Email(message = "Informe um email válido.")
        String email,

        @NotBlank(message = "A senha é obrigatória.")
        @Size(min = 8, max = 20, message = "A senha deve ter de 8 a 20 caracteres.")
        @Pattern(
              regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^A-Za-z\\d\\s])\\S{8,}$",
              message = "A senha deve conter letra maiúscula, minúscula, número e caractere especial, sem espaços"
        )//regular expression
        String senha
) {
}
