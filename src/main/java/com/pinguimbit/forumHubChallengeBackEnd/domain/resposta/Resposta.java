package com.pinguimbit.forumHubChallengeBackEnd.domain.resposta;

import com.pinguimbit.forumHubChallengeBackEnd.domain.usuario.Perfil;
import com.pinguimbit.forumHubChallengeBackEnd.domain.topico.Topico;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Table(name = "respostas")
@Entity(name = "Resposta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Resposta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    String mensagem;

    @ManyToOne
    @JoinColumn(name = "topico_id")
    private Topico topico;

    LocalDateTime dataCriacao = LocalDateTime.now();

    @ManyToOne
    @JoinColumn(name = "perfil_id")
    private Perfil autor;

    private String solucao;
}
