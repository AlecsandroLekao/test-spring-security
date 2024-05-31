package br.com.medvoll.api.models;

public record DadosRetornoMedico(Long id, String nome, String telefone, String crm, Especialidade especialidade, Status status) {

    public DadosRetornoMedico {
    }

    public DadosRetornoMedico(Medico medico) {
        this(medico.getId(), medico.getNome(), medico.getTelefone(), medico.getCrm(),medico.getEspecialidade(),medico.getStatus());
    }
}
