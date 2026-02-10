package ejercicio1.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public record TransactionRecord(
        String id,
        LocalDate fecha,
        BigDecimal monto,
        String moneda,
        String usuarioID
) {  }

