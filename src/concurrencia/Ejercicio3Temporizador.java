package concurrencia;

/**
 * Ejercicio 3.3: segundero que main para con interrupt() a los 5 segundos.
 * Salida: Segundos: 1 ... Segundos: 4 (a veces 5, según quién gane en el segundo 5) y "Hebra interrumpida".
 */
public class Ejercicio3Temporizador {

    static class Segundero extends Thread {
        public void run() {
            int segundos = 0;
            try {
                while (true) {
                    sleep(1000);
                    segundos++;
                    System.out.println("Segundos: " + segundos);
                }
            } catch (InterruptedException e) {
                System.err.println("Hebra interrumpida");
                return;
            }
        }
    }

    public static void main(String args[]) {
        int tiempoEspera = 5;
        Thread segundero = new Segundero();
        segundero.start();
        try {
            Thread.sleep(tiempoEspera * 1000);
            segundero.interrupt();
        } catch (Exception e) {
        }
    }
}
