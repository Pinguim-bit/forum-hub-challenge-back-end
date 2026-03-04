package com.pinguimbit.forumHubChallengeBackEnd.domain.usuario;

public record AtualizarUsuarioDTO(
        Long id,
        String nome,
        String email,
        String senha
) {
    public AtualizarUsuarioDTO (Usuario dados){
        this(dados.getId(), dados.getNome(), dados.getEmail(), dados.getSenha());
    }
}
