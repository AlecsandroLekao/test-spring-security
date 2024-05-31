package br.com.medvoll.api.models;

import jakarta.persistence.Embeddable;


@Embeddable
public class Endereco {

  private  String logradouro;
  private  String bairro;
  private  String cep;
  private  String cidade;
  private  String uf;
  private  String complemento;
  private  String numero;

  public String getLogradouro() {
    return logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public String getCidade() {
    return cidade;
  }

  public void setCidade(String cidade) {
    this.cidade = cidade;
  }

  public String getUf() {
    return uf;
  }

  public void setUf(String uf) {
    this.uf = uf;
  }


  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public Endereco() {
  }

  public Endereco(EnderecoDTO endereco ){
    this.logradouro = endereco.logradouro();
    this.bairro = endereco.bairro();
    this.cep = endereco.cep();
    this.cidade = endereco.cidade();
    this.uf = endereco.uf();
    this.complemento = endereco.complemento();
    this.numero = endereco.numero();
  }

}
