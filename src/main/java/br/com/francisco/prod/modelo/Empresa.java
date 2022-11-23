package br.com.francisco.prod.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "EMPRESA")
public class Empresa {

    @Id
    @GeneratedValue(generator = "sq_empresa")
    @SequenceGenerator(name = "sq_empresa", sequenceName = "sq_empresa")
    @Column(name = "ID")
    private Long id;

    @Column(name = "NOME_FANTASIA")
    private String nomeFantasia;

    @Column(name = "FATURAMENTO")
    private BigDecimal faturamento;

    @Column(name = "LUCRO")
    private BigDecimal lucro;

    @Column(name = "QTD_FUNCIONARIOS")
    private Integer numeroDeFuncionarios;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "empresa")
    private Manufatura manufatura;

    public Empresa() {
    }

    public Empresa(Long id, String nomeFantasia, BigDecimal faturamento, BigDecimal lucro, Integer numeroDeFuncionarios) {
        this.id = id;
        this.nomeFantasia = nomeFantasia;
        this.faturamento = faturamento;
        this.lucro = lucro;
        this.numeroDeFuncionarios = numeroDeFuncionarios;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomeFantasia() {
        return nomeFantasia;
    }

    public void setNomeFantasia(String nomeFantasia) {
        this.nomeFantasia = nomeFantasia;
    }

    public BigDecimal getFaturamento() {
        return faturamento;
    }

    public void setFaturamento(BigDecimal faturamento) {
        this.faturamento = faturamento;
    }

    public BigDecimal getLucro() {
        return lucro;
    }

    public void setLucro(BigDecimal lucro) {
        this.lucro = lucro;
    }

    public Integer getNumeroDeFuncionarios() {
        return numeroDeFuncionarios;
    }

    public void setNumeroDeFuncionarios(Integer numeroDeFuncionarios) {
        this.numeroDeFuncionarios = numeroDeFuncionarios;
    }

    public Manufatura getManufatura() {
        return manufatura;
    }

    public void setManufatura(Manufatura manufatura) {
        this.manufatura = manufatura;
    }
}
