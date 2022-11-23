package br.com.francisco.prod.servico;

import br.com.francisco.prod.enums.UnidadeEnnum;
import br.com.francisco.prod.exception.ObjetoNaoEncontradoException;
import br.com.francisco.prod.modelo.Empresa;
import br.com.francisco.prod.modelo.dto.EmpresaDTO;
import br.com.francisco.prod.repositorio.EmpresaRepositorio;
import br.com.francisco.prod.util.CalculoProdutividadeInterface;
import br.com.francisco.prod.util.CalculoProdutividadeSimples;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepositorio repositorio;

    public Empresa buscarPorId(Long id){
        Optional<Empresa> obj = repositorio.findById(id);
        return obj.orElseThrow( () -> new ObjetoNaoEncontradoException("Empresa não encontrada") );
    }

    public EmpresaDTO buscarProdutividadePorId(Long id, String periodo){
        Optional<Empresa> obj = repositorio.findById(id);

        Empresa empresa = obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Empresa não encontrada"));

        EmpresaDTO dto = new EmpresaDTO(empresa);
        dto.setUnidadeProdutividade(UnidadeEnnum.getUnidadeDadaString(periodo));
        calcularProdutividade(new CalculoProdutividadeSimples(dto), dto);

        return dto;
    }

    public List<EmpresaDTO> buscarTodosComProdutividadeMensalRelatorio(){
        List<EmpresaDTO> empresaDTOList = new ArrayList<>();

        repositorio.findAll().forEach( e -> {
            EmpresaDTO dto = new EmpresaDTO(e);
            dto.setUnidadeProdutividade(UnidadeEnnum.MES);

            calcularProdutividade(new CalculoProdutividadeSimples(dto), dto);
            empresaDTOList.add(dto);
        });

        empresaDTOList.sort( (o1, o2) -> o1.compareTo(o2) );

        return empresaDTOList;
    }

    public List<Empresa> buscarTodos(){
        return repositorio.findAll();
    }

    public Empresa criar(Empresa obj){
        obj.getManufatura().setEmpresa(obj);
        return repositorio.save(obj);
    }

    public Empresa editar(Long id, Empresa obj){
        this.buscarPorId(id);
        obj.setId(id);
        if(Objects.nonNull(obj.getManufatura())){
            obj.getManufatura().setId(id);
        }
        return repositorio.save(obj);
    }

    public void deletar(Long id){
        Empresa obj = this.buscarPorId(id);
        repositorio.delete(obj);
    }

    private void calcularProdutividade(CalculoProdutividadeInterface calculo, EmpresaDTO empresaDTO){
        double prd = calculo.calcularProdutividade();
        empresaDTO.setProdutividade(prd);
    }

}
