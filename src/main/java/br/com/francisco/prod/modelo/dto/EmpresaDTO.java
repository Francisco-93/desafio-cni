package br.com.francisco.prod.modelo.dto;

import br.com.francisco.prod.enums.UnidadeEnnum;
import br.com.francisco.prod.modelo.Empresa;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.Objects;

public class EmpresaDTO implements Comparable<EmpresaDTO>{

    private Long id;
    private String nomeFantasia;
    private BigDecimal faturamento;
    private BigDecimal lucro;
    private Integer numeroDeFuncionarios;
    private Integer producaoDiaria;
    private UnidadeEnnum unidadeProdutividade = UnidadeEnnum.DIA;
    private Double produtividade;

    public EmpresaDTO(Empresa obj) {
        this.id = obj.getId();
        this.nomeFantasia = obj.getNomeFantasia();
        this.faturamento = obj.getFaturamento();
        this.lucro = obj.getLucro();
        this.numeroDeFuncionarios = obj.getNumeroDeFuncionarios();

        if(Objects.nonNull(obj.getManufatura())){
            this.producaoDiaria = obj.getManufatura().getProducaoDiaria();
        }
        else{
            this.producaoDiaria = 0;
        }
    }

    public Long getId() {
        return id;
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

    public Integer getProducaoDiaria() {
        return producaoDiaria;
    }

    public void setProducaoDiaria(Integer producaoDiaria) {
        this.producaoDiaria = producaoDiaria;
    }

    public UnidadeEnnum getUnidadeEnum() {
        return unidadeProdutividade;
    }

    public void setUnidadeProdutividade(UnidadeEnnum unidadeProdutividade) {
        this.unidadeProdutividade = unidadeProdutividade;
    }

    public double getProdutividade() {
        DecimalFormat df = new DecimalFormat("0.00");
        df.setRoundingMode(RoundingMode.HALF_EVEN);
        return Double.valueOf(df.format(produtividade));
    }

    public void setProdutividade(double produtividade) {
        this.produtividade = produtividade;
    }

    @Override
    public int compareTo(EmpresaDTO o) {
        if(this.produtividade > o.produtividade){
            return -1;
        }
        else if(this.produtividade < o.produtividade){
            return 1;
        }
        else{
            return 0;
        }
    }
}
