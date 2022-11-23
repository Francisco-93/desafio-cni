package br.com.francisco.prod.recursos;

import br.com.francisco.prod.modelo.Empresa;
import br.com.francisco.prod.modelo.dto.EmpresaDTO;
import br.com.francisco.prod.servico.EmpresaService;
import br.com.francisco.prod.servico.RelatorioService;
import net.sf.jasperreports.engine.JRException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaResource {

    private static final String ID = "/{id}";

    @Autowired
    private EmpresaService servico;

    @Autowired
    private RelatorioService relatorioService;

    @GetMapping
    public ResponseEntity<List<Empresa>> buscarTodos(){
        return ResponseEntity.ok().body(servico.buscarTodos());
    }

    @GetMapping(value = ID)
    public ResponseEntity<Empresa> buscarPorId(@PathVariable Long id){
        return ResponseEntity.ok().body(servico.buscarPorId(id));
    }

    @GetMapping(value ="/produtividade" + ID)
    public ResponseEntity<EmpresaDTO> buscarPorIdProdutividade(@PathVariable Long id, @RequestParam(name = "periodo", defaultValue = "mes") String periodo){
        return ResponseEntity.ok().body(servico.buscarProdutividadePorId(id, periodo));
    }

    @GetMapping(value ="/produtividade-lista")
    public ResponseEntity<List<EmpresaDTO>> listaProdutividade(){
        return ResponseEntity.ok().body(servico.buscarTodosComProdutividadeMensalRelatorio());
    }

    @GetMapping(value ="/produtividade-relatorio")
    public ResponseEntity<ByteArrayResource> relatorioProdutividade() throws JRException, IOException {
        byte[] pdf = relatorioService.gerarRelatorio();

        ByteArrayResource bar = new ByteArrayResource(pdf);

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=Relatorio_de_Produtividade.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .body(bar);
    }

    @PostMapping
    public ResponseEntity<Empresa> criar(@RequestBody Empresa obj){
        Empresa newObj = servico.criar(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path(ID).buildAndExpand(newObj.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = ID)
    public ResponseEntity<Empresa> editar(@PathVariable Long id, @RequestBody Empresa obj){
        Empresa newObj = servico.editar(id, obj);
        return ResponseEntity.ok().body(newObj);
    }

    @DeleteMapping(value = ID)
    public ResponseEntity<Empresa> deletar(@PathVariable Long id){
        servico.deletar(id);
        return ResponseEntity.noContent().build();
    }

}
