package br.com.cotiinformatica.api_usuarios.dtos;

public record AutenticarUsuarioRequest(
        String email,
        String senha
) {
}
