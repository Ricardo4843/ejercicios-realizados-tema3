package concurrencia.ej01;

// Ejercicio 1: lectores y escritores (varias lectoras a la vez, escritora en exclusiva)
public class RecursoCompartido {
    private int entero;
    private int numLectores = 0;
    private boolean escribiendo = false;

    public synchronized void accesoLectura() {
        while (escribiendo) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        numLectores++;
    }

    public synchronized void terminaLectura() {
        numLectores--;
        notifyAll();
    }

    public synchronized void accesoEscritura() {
        while (numLectores != 0 || escribiendo) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        escribiendo = true;
    }

    public synchronized void terminaEscritura() {
        escribiendo = false;
        notifyAll();
    }

    public void escribir(String nombre) {
        accesoEscritura();
        entero = (int) (Math.random() * 10001);
        System.out.println(nombre + " escribe " + entero);
        int dormir = (int) (Math.random() * 500);
        try {
            Thread.sleep(dormir);
        } catch (InterruptedException e) {}
        terminaEscritura();
    }

    public void leer(String nombre) {
        accesoLectura();
        System.out.println(nombre + " lee " + entero);
        int dormir = (int) (Math.random() * 500);
        try {
            Thread.sleep(dormir);
        } catch (InterruptedException e) {}
        terminaLectura();
    }
}
