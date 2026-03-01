package com.pinguimbit.forumHubChallengeBackEnd.controller;

import com.pinguimbit.forumHubChallengeBackEnd.domain.curso.CursoRepository;
import com.pinguimbit.forumHubChallengeBackEnd.domain.topico.CadastroTopicoDTO;
import com.pinguimbit.forumHubChallengeBackEnd.domain.topico.DetalharTopicoDTO;
import com.pinguimbit.forumHubChallengeBackEnd.domain.topico.Topico;
import com.pinguimbit.forumHubChallengeBackEnd.domain.topico.TopicoRepository;
import com.pinguimbit.forumHubChallengeBackEnd.domain.usuario.PerfilRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/topicos")
public class TopicosController {
    @Autowired
    private TopicoRepository repository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private CursoRepository cursoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrarTopico(@RequestBody @Valid CadastroTopicoDTO dados, UriComponentsBuilder uriBuilder){
        var autor = perfilRepository.getReferenceById(dados.autor_id());
        var curso = cursoRepository.getReferenceById(dados.curso_id());

        var topico = new Topico(dados, autor, curso);
        repository.save(topico);

        return ResponseEntity.ok(new DetalharTopicoDTO(topico));
    }

    @GetMapping
    public ResponseEntity<?> listarTopicos(){
        var topicos = repository.findAll();
        List<DetalharTopicoDTO> dto  = new ArrayList<>();
        topicos.forEach(t -> dto.add(new DetalharTopicoDTO(t)));

        return ResponseEntity.ok(dto);
    }
}
