package br.com.medvoll.api.models;

import jakarta.validation.constraints.NotNull;

public record MedicoDetalhes(Long id, String nome, String telefone, Endereco endereco, Especialidade especialidade) {

    public MedicoDetalhes(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getTelefone(), medico.getEndereco(),medico.getEspecialidade());
    }
}
