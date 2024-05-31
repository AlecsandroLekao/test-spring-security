package br.com.medvoll.api.models;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record MedicoDTO(@NotBlank String nome, @Email String email,@NotBlank String crm, Especialidade especialidade, EnderecoDTO endereco, String telefone) {
}
