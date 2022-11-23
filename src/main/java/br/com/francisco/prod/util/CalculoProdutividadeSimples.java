package br.com.francisco.prod.util;

import br.com.francisco.prod.modelo.dto.EmpresaDTO;

public class CalculoProdutividadeSimples implements CalculoProdutividadeInterface {

    private EmpresaDTO empresaDTO;

    public CalculoProdutividadeSimples(EmpresaDTO empresaDTO) {
        this.empresaDTO = empresaDTO;
    }

    @Override
    public double calcularProdutividade(Object... varargs) {
        double producao = empresaDTO.getProducaoDiaria().doubleValue();
        double producaoPeriodo = producao * empresaDTO.getUnidadeEnum().getUnidade();

        return producaoPeriodo / empresaDTO.getNumeroDeFuncionarios();
    }
}
