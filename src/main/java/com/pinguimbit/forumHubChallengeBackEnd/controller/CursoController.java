package com.pinguimbit.forumHubChallengeBackEnd.controller;

import com.pinguimbit.forumHubChallengeBackEnd.domain.curso.CadastrarCursoDTO;
import com.pinguimbit.forumHubChallengeBackEnd.domain.curso.Curso;
import com.pinguimbit.forumHubChallengeBackEnd.domain.curso.CursoRepository;
import com.pinguimbit.forumHubChallengeBackEnd.domain.curso.ListarCursosDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        var rawCursos = repository.findAll();
        List<ListarCursosDTO> listaCursos = new ArrayList<>();
        for (Curso curso : rawCursos) {
            listaCursos.add(new ListarCursosDTO(curso));
        }
        String mensagem = "Lista de cursos disponíveis:\n" +  listaCursos;

        return ResponseEntity.ok(mensagem);
    }
}
