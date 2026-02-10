package ejercicio2.repository;

import ejercicio2.model.enums.EstadoFactura;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class FacturaRepository {

    private final Map<Integer, EstadoFactura> storage = new ConcurrentHashMap<>();

    public void save(Integer id, EstadoFactura estado) {
        try {
            Thread.sleep(100); // Retardo simulado
        } catch (InterruptedException ignored) {}
        storage.put(id, estado);
    }

    public EstadoFactura find(Integer id) {
        return storage.get(id);
    }
}