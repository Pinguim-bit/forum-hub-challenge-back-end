package com.pinguimbit.forumHubChallengeBackEnd.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PerfilRepository extends JpaRepository<Perfil, Long> {
    void deleteByUsuarioId(Long id);
}
