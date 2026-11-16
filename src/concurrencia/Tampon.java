package concurrencia;

/**
 * Ejercicio 3.7: monitor de un tampón con capacidad para un dato.
 */
public class Tampon {

    private int datos = 0;
    private boolean lleno = false;

    public synchronized void guardar(int dato) {
        while (lleno) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        lleno = true;
        datos = dato;
        notifyAll();
    }

    public synchronized int sacar() {
        while (!lleno) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        lleno = false;
        int num = datos;
        datos = 0;
        notifyAll();
        return num;
    }
}
