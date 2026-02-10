package ejercicio2.service;

import ejercicio2.model.dto.FacturaDTO;
import ejercicio2.model.enums.EstadoFactura;
import ejercicio2.repository.FacturaRepository;
import org.springframework.stereotype.Service;

@Service
public class FacturaService {

    private final FacturaRepository repo;

    public FacturaService(FacturaRepository repo) {
        this.repo = repo;
    }

    public EstadoFactura procesar(FacturaDTO factura) {

        repo.save(factura.getId(), EstadoFactura.PENDIENTE);

        int intentos = 3;

        for (int i = 0; i < intentos; i++) {
            try {
                llamadaExterna();
                repo.save(factura.getId(), EstadoFactura.ENVIADA);
                return EstadoFactura.ENVIADA;
            } catch (Exception ex) {
                System.out.println("Reintento fallido: " + (i + 1));
            }
        }

        throw new RuntimeException("Servicio externo no disponible (503)");
    }

    private void llamadaExterna() throws Exception {
        Thread.sleep(500);

        if (Math.random() < 0.2) {
            throw new Exception("Fallo en la DIAN/Hacienda");
        }
    }
}