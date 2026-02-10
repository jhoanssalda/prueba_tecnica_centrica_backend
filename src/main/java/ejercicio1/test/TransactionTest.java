package ejercicio1.test;

import ejercicio1.service.TransactionService;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

public class TransactionTest {

    static void main() throws IOException {
        TransactionService service = new TransactionService();

        int numUsers = 3;
        List<Map.Entry<String, BigDecimal>> users = service.procesarCSV(Paths.get("una_ruta"), numUsers);

        System.out.println("Top "+numUsers+" usuarios con mayor gasto");
        users.forEach(entry ->
                System.out.println("Usuario: " +entry.getKey()+ "tiene un total de: " +entry.getKey()));
    }
}
