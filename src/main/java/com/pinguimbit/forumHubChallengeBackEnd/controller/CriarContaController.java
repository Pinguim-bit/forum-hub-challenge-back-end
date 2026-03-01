package com.pinguimbit.forumHubChallengeBackEnd.controller;

import com.pinguimbit.forumHubChallengeBackEnd.domain.usuario.CriarUsuarioDTO;
import com.pinguimbit.forumHubChallengeBackEnd.domain.usuario.Perfil;
import com.pinguimbit.forumHubChallengeBackEnd.domain.usuario.Usuario;
import com.pinguimbit.forumHubChallengeBackEnd.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/create-account")
public class CriarContaController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    public ResponseEntity<?> criarUsuario(@RequestBody @Valid CriarUsuarioDTO dados) {

        var usuario = new Usuario(dados);
        var perfil = new Perfil(usuario);
        usuario.adicionarPerfil(perfil);

        usuarioRepository.save(usuario);
        return ResponseEntity.ok("Usuário Criado com Sucesso");
    }
}
