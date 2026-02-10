package ejercicio3.test;

import ejercicio3.model.SharedAccount;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CarreraTest {

    static void main() {

        SharedAccount cuenta = new SharedAccount();
        try(ExecutorService executor = Executors.newFixedThreadPool(10)){

            for (int i = 0; i < 10; i++) {
                executor.submit(() -> cuenta.retirar(100));
            }

            executor.shutdown();
            if (!executor.awaitTermination(5, TimeUnit.SECONDS)) {
                System.err.println("No todos los hilos terminaron en el tiempo esperado");
            }

            System.out.println("Saldo final = " + cuenta.getSaldo());

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new RuntimeException(e);
        }

    }
}
