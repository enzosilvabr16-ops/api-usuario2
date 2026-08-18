package br.com.cotiinformatica.api_usuarios.dtos;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record CriarUsuarioResponse(
        String mensagem,
        Integer usuarioId,
        String nome,
        String email,
        String perfil,
        LocalDateTime dataHoraCadastro
) {
}
