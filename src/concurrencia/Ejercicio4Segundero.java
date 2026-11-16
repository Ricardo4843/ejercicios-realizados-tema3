package concurrencia;

/**
 * Ejercicio 3.4: segundero que se para con un indicador volatile en vez de con interrupt().
 */
public class Ejercicio4Segundero extends Thread {

    private int contador = 0;
    volatile boolean indicador = true;

    public void parar() {
        indicador = false;
    }

    public void run() {
        while (indicador) {
            try {
                sleep(1000);
                contador++;
                System.out.println(contador);
            } catch (InterruptedException e) {
            }
        }
    }

    public static void main(String[] args) {
        Ejercicio4Segundero contador = new Ejercicio4Segundero();
        int segundos = 7;
        contador.start();
        try {
            Thread.sleep(segundos * 1000);
            contador.parar();
        } catch (InterruptedException e) {
        }
    }
}
