package com.pinguimbit.forumHubChallengeBackEnd.domain.usuario;

import java.util.List;

public record ListarUsuariosDTO(
        String nome,
        String email,
        List<Long> id_perfil
) {
    public ListarUsuariosDTO(Usuario dados) {
        this(dados.getNome(), dados.getEmail(), dados.getPerfis().stream().map(Perfil::getId).toList());
    }
}
