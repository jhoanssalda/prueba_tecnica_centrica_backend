package ejercicio2.controller;

import ejercicio2.exception.FacturaException;
import ejercicio2.model.dto.FacturaDTO;
import ejercicio2.model.enums.EstadoFactura;
import ejercicio2.service.FacturaService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/facturas")
public class FacturaController {

    private final FacturaService service;
    private final FacturaException exception;

    public FacturaController(FacturaException exception, FacturaService service) {
        this.exception = exception;
        this.service = service;
    }

    @PostMapping
    public EstadoFactura recibirFactura(@Valid @RequestBody FacturaDTO factura) {
        try {
            return service.procesar(factura);
        } catch (Exception e) {
            throw new ServiceUnavailableException("El servicio externo no está disponible");

        }
    }

    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
    private static class ServiceUnavailableException extends RuntimeException {
        public ServiceUnavailableException(String msg) { super(msg); }
    }
}
