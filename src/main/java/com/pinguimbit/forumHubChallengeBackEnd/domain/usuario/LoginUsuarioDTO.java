package com.pinguimbit.forumHubChallengeBackEnd.domain.usuario;

import jakarta.validation.constraints.NotBlank;

public record LoginUsuarioDTO(
        @NotBlank String email,
        @NotBlank String senha
) {
}
