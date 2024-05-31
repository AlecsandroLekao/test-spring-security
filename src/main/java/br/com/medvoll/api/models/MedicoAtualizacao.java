package br.com.medvoll.api.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record MedicoAtualizacao(@NotNull Long id, String nome, String telefone, Endereco endereco) {
}
