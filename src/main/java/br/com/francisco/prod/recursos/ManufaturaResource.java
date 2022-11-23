package br.com.francisco.prod.recursos;


import br.com.francisco.prod.modelo.Manufatura;
import br.com.francisco.prod.servico.ManufaturaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/manufatura")
public class ManufaturaResource {

    private static final String ID = "/{id}";

    @Autowired
    private ManufaturaService servico;

    @GetMapping
    public ResponseEntity<List<Manufatura>> buscarTodos(){
        return ResponseEntity.ok().body(servico.buscarTodos());
    }

    @GetMapping(value = ID)
    public ResponseEntity<Manufatura> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(servico.buscarPorId(id));
    }

}
