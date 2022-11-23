package br.com.francisco.prod.enums;

public enum UnidadeEnnum {

    HORA (1, "hora", (1.0/24.0)),
    DIA (2, "dia", 1.0),
    MES (3, "mes", 30.0),
    ANO (4, "ano", 365.0);

    private Integer codigo;
    private String periodo;
    private Double unidade;

    private UnidadeEnnum(Integer codigo, String periodo, Double unidade) {
        this.codigo = codigo;
        this.periodo = periodo;
        this.unidade = unidade;
    }

    public Integer getCodigo() {
        return codigo;
    }

    public Double getUnidade() {
        return unidade;
    }

    public String getPeriodo() {
        return periodo;
    }

    public static UnidadeEnnum getUnidadeDadaString(String periodo){
        for(UnidadeEnnum e : UnidadeEnnum.values()){
            if(e.periodo.equalsIgnoreCase(periodo)){
                return e;
            }
        }
        return null;
    }
}
