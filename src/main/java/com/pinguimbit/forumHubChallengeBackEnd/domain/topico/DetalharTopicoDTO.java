package com.pinguimbit.forumHubChallengeBackEnd.domain.topico;

import com.pinguimbit.forumHubChallengeBackEnd.domain.curso.Curso;
import com.pinguimbit.forumHubChallengeBackEnd.domain.usuario.Perfil;

import java.time.LocalDateTime;

public record DetalharTopicoDTO(
        String titulo,
        String mensagem,
        String autor,
        String curso,
        LocalDateTime dataCriacao,
        Estatus status
) {
    public DetalharTopicoDTO(Topico dados) {
        this(
                dados.getTitulo(),
                dados.getMensagem(),
                dados.getAutor().getNome(),
                dados.getCurso().getNome(),
                dados.getDataCriacao(),
                dados.getStatus());
    }
}
