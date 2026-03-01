package com.pinguimbit.forumHubChallengeBackEnd.domain.topico;

public record CadastroTopicoDTO(
        String titulo,
        String mensagem,
        Long autor_id,
        Long curso_id
) {
}
