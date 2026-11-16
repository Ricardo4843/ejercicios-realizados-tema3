package concurrencia;

/**
 * Ejercicio 3.5: dos hebras incrementan 10000 veces el mismo contador sin synchronized.
 * Imprime un valor menor que 20000 y distinto en cada ejecución (condición de carrera),
 * y además main espera con sleep(1000) en vez de con join().
 */
public class Ejercicio5Contador {

    static class Contador {
        private long cuenta;

        public Contador(long valorInicial) {
            cuenta = valorInicial;
        }

        public void incrementar() {
            cuenta++;
        }

        public long valor() {
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
        Thread.sleep(1000);
        System.out.println(contador.valor());
    }
}
