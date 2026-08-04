package br.com.cotiinformatica.api_usuarios.dtos;

public record CriarUsuarioRequest(
        String nome,
        String email,
        String senha
) {
}
