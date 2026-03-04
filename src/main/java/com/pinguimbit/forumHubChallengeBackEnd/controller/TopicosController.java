package com.pinguimbit.forumHubChallengeBackEnd.controller;

import com.pinguimbit.forumHubChallengeBackEnd.domain.curso.CursoRepository;
import com.pinguimbit.forumHubChallengeBackEnd.domain.topico.*;
import com.pinguimbit.forumHubChallengeBackEnd.domain.topico.validacoes.ValidarAtualizacaoTopico;
import com.pinguimbit.forumHubChallengeBackEnd.domain.topico.validacoes.ValidarCriacaoTopico;
import com.pinguimbit.forumHubChallengeBackEnd.domain.usuario.PerfilRepository;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/topicos")
public class TopicosController {
    @Autowired
    private TopicoRepository repository;
    @Autowired
    private PerfilRepository perfilRepository;
    @Autowired
    private CursoRepository cursoRepository;
    @Autowired
    private ValidarCriacaoTopico validarCriacaoTopico;
    @Autowired
    private ValidarAtualizacaoTopico validarAtualizacaoTopico;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarTopico(@RequestBody @Valid CadastroTopicoDTO dados) {
        validarCriacaoTopico.validar(dados);

        var autor = perfilRepository.getReferenceById(dados.autor_id());
        var curso = cursoRepository.getReferenceById(dados.curso_id());

        var topico = new Topico(dados, autor, curso);
        repository.save(topico);

        return ResponseEntity.ok(new DetalharTopicoDTO(topico));
    }

    @GetMapping
    public ResponseEntity<Page<DetalharTopicoDTO>> listarTopicos(
            @PageableDefault(size = 10, sort = {"dataCriacao"}, direction = Sort.Direction.DESC)
            Pageable paginacao) {
        //na sugestão do trello pediu em ordem ASC
        // mas me parece mais vantajoso ordenar pelo mais recente
        //então ficou em DESC
        var page = repository.findAll(paginacao).map(DetalharTopicoDTO::new);

        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> detalharTopico(@PathVariable Long id) {

        var topico = repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Tópico não encontrado!"));

        return ResponseEntity.ok(new DetalharTopicoDTO(topico));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<?> atualizarTopico(@PathVariable Long id, @RequestBody @Valid AtualizarTopicoDTO dados) {
        validarAtualizacaoTopico.validar(dados);

        var topico = repository.findById(id).orElseThrow(
                () -> new EntityNotFoundException("Tópico não encontrado!")
        );

        topico.atualizarTopico(dados);
        return ResponseEntity.ok("Tópico atualizado com sucesso!");
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deletarTopico(@PathVariable Long id) {

        if(!repository.existsById(id))
            throw new EntityNotFoundException("Não foi possível excluir: Tópico não encontrado!");

        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
