package com.pinguimbit.forumHubChallengeBackEnd.domain.curso;

public record ListarCursosDTO(
        Long id,
        String nome,
        String categoria
) {
    public ListarCursosDTO(Curso curso) {
        this(curso.getId(), curso.getNome(), curso.getCategoria());
    }
}