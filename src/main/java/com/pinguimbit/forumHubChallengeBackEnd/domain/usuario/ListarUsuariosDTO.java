package com.pinguimbit.forumHubChallengeBackEnd.domain.usuario;

import org.springframework.data.domain.Page;

public record ListarUsuariosDTO(
        String nome,
        String email
) {
    public ListarUsuariosDTO (Usuario dados) {
        this(dados.getNome(), dados.getEmail());
    }
}
