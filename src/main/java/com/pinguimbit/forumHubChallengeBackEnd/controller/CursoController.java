package com.pinguimbit.forumHubChallengeBackEnd.controller;

import com.pinguimbit.forumHubChallengeBackEnd.domain.curso.CadastrarCursoDTO;
import com.pinguimbit.forumHubChallengeBackEnd.domain.curso.Curso;
import com.pinguimbit.forumHubChallengeBackEnd.domain.curso.CursoRepository;
import com.pinguimbit.forumHubChallengeBackEnd.domain.curso.ListarCursosDTO;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@SecurityRequirement(name = "bearer-key")
@RestController
@RequestMapping("/cursos")
public class CursoController {
    @Autowired
    private CursoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity<?> cadastrarCurso(@RequestBody CadastrarCursoDTO curso) {
        repository.save(new Curso(curso));

        return ResponseEntity.ok("Curso cadastrado com sucesso");
    }

    @GetMapping
    public ResponseEntity<?> listarCurso() {
        var cursos = repository.findAll();
        return ResponseEntity.ok(cursos.stream().map(ListarCursosDTO::new));
    }
}
