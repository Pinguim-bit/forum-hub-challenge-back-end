package com.pinguimbit.forumHubChallengeBackEnd.domain.topico.validacoes;

import com.pinguimbit.forumHubChallengeBackEnd.domain.topico.CadastroTopicoDTO;
import com.pinguimbit.forumHubChallengeBackEnd.domain.ValidacaoException;
import com.pinguimbit.forumHubChallengeBackEnd.domain.topico.TopicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class ValidarCriacaoTopico {
    @Autowired
    private TopicoRepository repository;

    public void validar(CadastroTopicoDTO dados){
        //primeira validação
        if(Objects.isNull(dados.titulo()) || dados.titulo().isEmpty())
            throw new ValidacaoException("O Título não pode ser vazio!");

        if(Objects.isNull(dados.mensagem()) || dados.mensagem().isEmpty())
            throw new ValidacaoException("A Mensagem não pode  ser vazia!");

        if(dados.autor_id() == null)
            throw new ValidacaoException("O Autor não pode ser nulo!");

        if(dados.curso_id() == null)
            throw new ValidacaoException("O Curso não pode ser nulo!");

        //segunda validação
        var topico = repository.findByTituloAndMensagem(dados.titulo(),dados.mensagem());
        if(topico.isPresent())
            throw new ValidacaoException("Tópico com esse título e mensagem já existe!");
    }
}
