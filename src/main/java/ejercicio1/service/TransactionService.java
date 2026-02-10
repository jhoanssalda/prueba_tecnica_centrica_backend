package ejercicio1.service;

import ejercicio1.model.TransactionRecord;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

public class TransactionService {

    private static final Logger logger = Logger.getLogger(TransactionService.class.getName());

    public List<Map.Entry<String, BigDecimal>> procesarCSV (Path csvPath, Integer users) throws IOException {

        try (BufferedReader br = Files.newBufferedReader(csvPath)){

            return br.lines()
                    .skip(1)
                    .map(this::parseLineSafe)
                    .filter(Objects::nonNull)
                    .filter(t -> t.moneda().equals("USD") && t.monto().compareTo(BigDecimal.valueOf(100)) > 0)
                    .collect(Collectors.groupingBy(
                            TransactionRecord::usuarioID,
                            Collectors.reducing(BigDecimal.ZERO, TransactionRecord::monto, BigDecimal::add)

                    ))
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.<String, BigDecimal>comparingByValue().reversed())
                    .limit(users)
                    .collect(Collectors.toList());
        }
    }

    private TransactionRecord parseLineSafe(String line) {
        try {
            String[] p = line.split(",");
            return new TransactionRecord(
                    p[0],
                    LocalDate.parse(p[1]),
                    new BigDecimal(p[2]),
                    p[3],
                    p[4]
            );
        } catch (Exception ex) {
            addErrorsLog("Línea corrupta, se ignora: " + line);
            return null;
        }
    }

    private void addErrorsLog(String error) {
        try {
            FileHandler fh = new FileHandler("ejercicio1.log", true);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);

            logger.info(error);
            fh.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
