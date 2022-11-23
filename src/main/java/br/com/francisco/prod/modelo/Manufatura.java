package br.com.francisco.prod.modelo;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "MANUFATURA")
public class Manufatura {

    @Id
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME_FANTASIA")
    private String nomeProduto;

    @Column(name = "PRODUCAO_DIARIA")
    private Integer producaoDiaria;

    @OneToOne
    @MapsId
    @JsonIgnore
    @JoinColumn(name = "EMPRESA_ID")
    private Empresa empresa;

    public Manufatura() {
    }

    public Manufatura(Long id, String nomeProduto, Integer producaoDiaria, Empresa empresa) {
        this.id = id;
        this.nomeProduto = nomeProduto;
        this.producaoDiaria = producaoDiaria;
        this.empresa = empresa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Integer getProducaoDiaria() {
        return producaoDiaria;
    }

    public void setProducaoDiaria(Integer producaoDiaria) {
        this.producaoDiaria = producaoDiaria;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }
}
