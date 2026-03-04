package com.pinguimbit.forumHubChallengeBackEnd.controller;

import com.pinguimbit.forumHubChallengeBackEnd.domain.ValidacaoException;
import com.pinguimbit.forumHubChallengeBackEnd.domain.usuario.*;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;
    @Autowired
    private PerfilRepository  perfilRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<Page<ListarUsuariosDTO>> getUsuarios(Pageable pageable) {
        var page =  repository.findAll(pageable).map(ListarUsuariosDTO::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUsuario(@PathVariable Long id){
        return ResponseEntity.ok(new  ListarUsuariosDTO(repository.findById(id).orElseThrow(
                () -> new ValidacaoException("Usuário não encontrado")
        )));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> updateUsuario(@PathVariable Long id, @RequestBody AtualizarUsuarioDTO dados){
        if(!repository.existsById(id))
            throw new ValidacaoException("Usuário não existe!");

        var usuario = repository.getReferenceById(dados.id());
        var senhaBcrypt = passwordEncoder.encode(dados.senha());
        usuario.updateUsuario(dados, senhaBcrypt);

        return  ResponseEntity.ok("Usuario atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteUsuario(@PathVariable Long id){
        if(!repository.existsById(id))
            throw new ValidacaoException("Usuário não existe!");

        perfilRepository.deleteByUsuarioId(id);
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
