package com.pinguimbit.forumHubChallengeBackEnd.domain.topico;

public record AtualizarTopicoDTO(
        String titulo,
        String mensagem
) {
    public AtualizarTopicoDTO(Topico dados) {
        this(dados.getTitulo(), dados.getMensagem());
    }
}
