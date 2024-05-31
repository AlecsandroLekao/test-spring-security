package br.com.medvoll.api.models;

public record EnderecoDTO(String logradouro, String bairro, String cep, String cidade, String uf, String complemento, String numero) {
}
