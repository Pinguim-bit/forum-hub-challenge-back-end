package com.pinguimbit.forumHubChallengeBackEnd.infra.exceptions;

import com.pinguimbit.forumHubChallengeBackEnd.domain.ValidacaoException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class TratadorDeErros {
    @ExceptionHandler(ValidacaoException.class)
    public ResponseEntity<?> tratarErroRegraDeNegocio(ValidacaoException ex){
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
