package br.com.francisco.prod.servico;

import br.com.francisco.prod.modelo.dto.EmpresaDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

@Service
public class RelatorioService implements Serializable {

    @Autowired
    private EmpresaService service;

    private static final String PATH_RELATORIO = "relatorios/cni-report-list.jasper";
    private static final String PATH_IMAGEM_LOGO = "relatorios/imagens/cni.png";

    public byte[] gerarRelatorio() throws IOException, JRException {

        List<EmpresaDTO> empresaDTOS = service.buscarTodosComProdutividadeMensalRelatorio();
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(empresaDTOS);

        InputStream stream = getClass().getClassLoader().getResourceAsStream(PATH_RELATORIO);
        InputStream streamLogo = getClass().getClassLoader().getResourceAsStream(PATH_IMAGEM_LOGO);

        Map<String, Object> params = new HashMap<>();
        params.put(JRParameter.REPORT_LOCALE, new Locale("pt", "BR"));
        params.put("imagemLogo", streamLogo);

        JasperPrint jasperPrint = JasperFillManager.fillReport(stream, params, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }

}
