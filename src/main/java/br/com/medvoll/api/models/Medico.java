package br.com.medvoll.api.models;

import jakarta.persistence.*;

import java.util.Objects;


@Table(name = "medicos")
@Entity(name = "Medico")
public class Medico {

    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String email;
    private String crm;
    private String telefone;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Embedded
    private Endereco endereco;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Medico() {
    }

    public Medico(MedicoDTO medicoDTO) {
        this.nome = medicoDTO.nome();
        this.email = medicoDTO.email();
        this.crm = medicoDTO.crm();
        this.telefone = medicoDTO.telefone();
        this.especialidade = medicoDTO.especialidade();
        this.endereco = new Endereco(medicoDTO.endereco());
    }

    public void atualizaInformacoes(MedicoAtualizacao medicoAtualizacao){
        if (!Objects.isNull(medicoAtualizacao.telefone())){
           this.telefone = medicoAtualizacao.telefone();
        }
        if (!Objects.isNull(medicoAtualizacao.nome())){
            this.nome = medicoAtualizacao.nome();
        }
        if (!Objects.isNull(medicoAtualizacao.endereco())){
          this.endereco = medicoAtualizacao.endereco();
        }
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Medico(MedicoDetalhes medicoDetalhes) {
        this.nome = medicoDetalhes.nome();
        this.telefone = medicoDetalhes.telefone();
        this.endereco = medicoDetalhes.endereco();
    }
}
