package com.pinguimbit.forumHubChallengeBackEnd.domain.topico;

import com.pinguimbit.forumHubChallengeBackEnd.domain.curso.Curso;
import com.pinguimbit.forumHubChallengeBackEnd.domain.usuario.Perfil;
import com.pinguimbit.forumHubChallengeBackEnd.domain.resposta.Resposta;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Table(name = "topicos")
@Entity(name = "Topico")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Topico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String mensagem;
    private LocalDateTime dataCriacao = LocalDateTime.now();

    @Enumerated(EnumType.STRING)
    private Estatus status = Estatus.ABERTO;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="autor_id")
    private Perfil autor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="curso_id")
    private Curso curso;

    @OneToMany(mappedBy = "topico", cascade = CascadeType.ALL)
    private List<Resposta> respostas;

    public Topico(@Valid CadastroTopicoDTO dados, Perfil autor, Curso curso) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
        this.autor = autor;
        this.curso = curso;
    }

    public void atualizarTopico(@Valid AtualizarTopicoDTO dados) {
        this.titulo = dados.titulo();
        this.mensagem = dados.mensagem();
    }
}
