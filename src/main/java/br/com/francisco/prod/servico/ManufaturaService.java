package br.com.francisco.prod.servico;

import br.com.francisco.prod.exception.ObjetoNaoEncontradoException;
import br.com.francisco.prod.modelo.Manufatura;
import br.com.francisco.prod.repositorio.ManufaturaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufaturaService {

    @Autowired
    private ManufaturaRepositorio repositorio;

    public Manufatura buscarPorId(Long id){
        Optional<Manufatura> obj = repositorio.findById(id);
        return obj.orElseThrow( () -> new ObjetoNaoEncontradoException("Manufatura não encontrada") );
    }

    public List<Manufatura> buscarTodos(){
        return repositorio.findAll();
    }

}
