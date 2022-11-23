package br.com.francisco.prod.repositorio;

import br.com.francisco.prod.modelo.Manufatura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufaturaRepositorio extends JpaRepository<Manufatura, Long> {
}
