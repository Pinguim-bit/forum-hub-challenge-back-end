package com.pinguimbit.forumHubChallengeBackEnd.domain.curso;

public record ListarCursosDTO(
        String nome,
        String categoria
) {
    public ListarCursosDTO(Curso curso) {
        this(curso.getNome(), curso.getCategoria());
    }

    @Override
    public String toString() {
        return "\t" + this.nome + " (" + this.categoria + ")\n";
    }
}