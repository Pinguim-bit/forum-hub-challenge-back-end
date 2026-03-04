package com.pinguimbit.forumHubChallengeBackEnd.controller;

import com.pinguimbit.forumHubChallengeBackEnd.domain.usuario.LoginUsuarioDTO;
import com.pinguimbit.forumHubChallengeBackEnd.domain.usuario.Usuario;
import com.pinguimbit.forumHubChallengeBackEnd.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<?> login(@RequestBody @Valid LoginUsuarioDTO dados) {
        var authToken = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var authentication = manager.authenticate(authToken);
        var tokenJWT = tokenService.gerarToken((Usuario) Objects.requireNonNull(authentication.getPrincipal()));
        return ResponseEntity.ok(new DadosTokenJWT(tokenJWT));
    }

    private record DadosTokenJWT(String tokenJWT) {
    }
}
