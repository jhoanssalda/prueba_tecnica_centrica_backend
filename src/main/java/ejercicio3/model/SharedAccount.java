package ejercicio3.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SharedAccount {

    private int saldo = 1000;

    public synchronized boolean retirar(int monto) {
        if (saldo >= monto) {
            saldo -= monto;
            System.out.println("Retiro exitoso: " + monto + " | Saldo restante: " + saldo);
            return true;
        } else {
            System.out.println("Fondos insuficientes");
            return false;
        }
    }

    public int getSaldo() {
        return saldo;
    }
}
