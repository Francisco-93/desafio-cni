package br.com.francisco.prod.config;

import br.com.francisco.prod.modelo.Empresa;
import br.com.francisco.prod.modelo.Manufatura;
import br.com.francisco.prod.repositorio.EmpresaRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.math.BigDecimal;
import java.util.Arrays;

@Configuration
@Profile("test")
public class CargaInicialConfig implements CommandLineRunner {

    public static final String NOME_PRODUTO = "Carro";

    @Autowired
    private EmpresaRepositorio repositorio;

    @Override
    public void run(String... args) throws Exception {

        Empresa empresa1 = new Empresa(null, "VolksWagen", new BigDecimal(18_977_998.09), new BigDecimal(12_443_973.78), 452);
        Empresa empresa2 = new Empresa(null, "Fiat", new BigDecimal(3_572_958.29), new BigDecimal(443_964.21), 367);
        Empresa empresa3 = new Empresa(null, "Chevrolet", new BigDecimal(12_374_932.76), new BigDecimal(9_243_975.28), 520);
        Empresa empresa4 = new Empresa(null, "Honda", new BigDecimal(5_327_998.09), new BigDecimal(1_443_473.99), 234);

        Manufatura manufatura1 = new Manufatura(null, NOME_PRODUTO, 220, empresa1);
        Manufatura manufatura2 = new Manufatura(null, NOME_PRODUTO, 124, empresa2);
        Manufatura manufatura3 = new Manufatura(null, NOME_PRODUTO, 232, empresa3);
        Manufatura manufatura4 = new Manufatura(null, NOME_PRODUTO, 89, empresa4);

        empresa1.setManufatura(manufatura1);
        empresa2.setManufatura(manufatura2);
        empresa3.setManufatura(manufatura3);
        empresa4.setManufatura(manufatura4);

        repositorio.saveAll(Arrays.asList(empresa1, empresa2, empresa3, empresa4));
    }
}
