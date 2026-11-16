package concurrencia;

/**
 * Ejercicio 3.6: el mismo contador con los métodos synchronized y main esperando con join().
 * Imprime siempre 20000.
 */
public class Ejercicio6ContadorSincronizado {

    static class Contador {
        private long cuenta;

        public Contador(long valorInicial) {
            cuenta = valorInicial;
        }

        public synchronized void incrementar() {
            cuenta++;
        }

        public synchronized long valor() {
            return cuenta;
        }
    }

    static class Incrementa extends Thread {
        Contador contador;

        public Incrementa(Contador c) {
            contador = c;
        }

        public void run() {
            for (int i = 0; i < 10000; i++)
                contador.incrementar();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Contador contador = new Contador(0);
        Thread hebra1 = new Incrementa(contador);
        Thread hebra2 = new Incrementa(contador);
        hebra1.start();
        hebra2.start();
        hebra1.join();
        hebra2.join();
        System.out.println(contador.valor());
    }
}
