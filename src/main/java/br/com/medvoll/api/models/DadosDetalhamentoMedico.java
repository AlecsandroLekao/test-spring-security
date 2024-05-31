package br.com.medvoll.api.models;

public record DadosDetalhamentoMedico(Long id, String nome, Endereco endereco, String telefone) {

    public DadosDetalhamentoMedico(MedicoAtualizacao medico) {
        this(medico.id(), medico.nome(), medico.endereco(),medico.telefone());
    }
}
