package concurrencia;

/**
 * Ejercicio 3.7: prueba del Tampon con 2 productores y 2 consumidores.
 */
public class Ejercicio7Tampon {

    public static void main(String[] args) throws InterruptedException {
        Tampon tampon = new Tampon();
        Thread[] hebras = new Thread[4];

        for (int p = 0; p < 2; p++) {
            int base = (p + 1) * 100;
            hebras[p] = new Thread(() -> {
                for (int i = 1; i <= 5; i++) {
                    tampon.guardar(base + i);
                    System.out.println("Guardado " + (base + i));
                }
            });
        }
        for (int c = 2; c < 4; c++) {
            hebras[c] = new Thread(() -> {
                for (int i = 0; i < 5; i++) {
                    System.out.println("    Sacado " + tampon.sacar());
                }
            });
        }

        for (Thread h : hebras) {
            h.start();
        }
        for (Thread h : hebras) {
            h.join();
        }
        System.out.println("Fin: 10 datos guardados y 10 sacados");
    }
}
