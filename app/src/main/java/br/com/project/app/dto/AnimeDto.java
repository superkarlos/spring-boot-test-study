package br.com.project.app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AnimeDto(
    @NotNull
    Integer id,
    @NotBlank
    @NotNull(message = "nome não deve ser nulo")
    String nome
) {
    
}
