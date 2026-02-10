package ejercicio2.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum EstadoFactura {
    ENVIADA("ENVIADA", 10),
    PENDIENTE("PENDIENTE ENVÍO", 11);
    private final String descripcion;
    private final int maxLength;

}
