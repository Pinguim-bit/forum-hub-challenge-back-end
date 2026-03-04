package com.pinguimbit.forumHubChallengeBackEnd.domain.topico.validacoes;

import com.pinguimbit.forumHubChallengeBackEnd.domain.ValidacaoException;
import com.pinguimbit.forumHubChallengeBackEnd.domain.topico.AtualizarTopicoDTO;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidarAtualizacaoTopico {
    public void validar(AtualizarTopicoDTO dados){
        if(Objects.isNull(dados.titulo()) || dados.titulo().isEmpty())
            throw new ValidacaoException("O título não pode ser vazio!");

        if(Objects.isNull(dados.mensagem()) || dados.mensagem().isEmpty())
            throw new ValidacaoException("A mensagem não pode ser vazia!");
    }
}
