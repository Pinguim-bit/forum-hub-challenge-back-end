package com.pinguimbit.forumHubChallengeBackEnd.domain.curso;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Table(name = "cursos")
@Entity(name = "Curso")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class Curso {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    //String mesmo. Para facilitar a vida
    private String categoria;

    public Curso(CadastrarCursoDTO curso) {
        this.nome = curso.nome();
        this.categoria = curso.categoria();
    }
}
