package br.com.francisco.prod.repositorio;

import br.com.francisco.prod.modelo.Empresa;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmpresaRepositorio extends JpaRepository<Empresa, Long> {
}
