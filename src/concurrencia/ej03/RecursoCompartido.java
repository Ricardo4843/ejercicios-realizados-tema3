package concurrencia.ej03;

// Ejercicio 3: lectores y escritores con prioridades (alta 2, media 1, baja 0; a igual prioridad, lectora primero)
public class RecursoCompartido {
    private int entero;
    private int numLectores = 0;
    private boolean escribiendo = false;
    private int[] lectorasEsperando = new int[3];
    private int[] escritorasEsperando = new int[3];

    private boolean hayMasPrioritarias(int p) {
        for (int i = p + 1; i < 3; i++) {
            if (lectorasEsperando[i] != 0 || escritorasEsperando[i] != 0) {
                return true;
            }
        }
        return false;
    }

    public synchronized void accesoLectura(int p) {
        lectorasEsperando[p]++;
        while (escribiendo || hayMasPrioritarias(p)) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        numLectores++;
        lectorasEsperando[p]--;
    }

    public synchronized void terminaLectura() {
        numLectores--;
        notifyAll();
    }

    public synchronized void accesoEscritura(int p) {
        escritorasEsperando[p]++;
        while (numLectores != 0 || escribiendo || hayMasPrioritarias(p) || lectorasEsperando[p] != 0) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        escribiendo = true;
        escritorasEsperando[p]--;
    }

    public synchronized void terminaEscritura() {
        escribiendo = false;
        notifyAll();
    }

    public void escribir(String nombre, int p) {
        accesoEscritura(p);
        entero = (int) (Math.random() * 10001);
        System.out.println(nombre + " (prioridad " + p + ") escribe " + entero);
        int dormir = (int) (Math.random() * 500);
        try {
            Thread.sleep(dormir);
        } catch (InterruptedException e) {}
        terminaEscritura();
    }

    public void leer(String nombre, int p) {
        accesoLectura(p);
        System.out.println(nombre + " (prioridad " + p + ") lee " + entero);
        int dormir = (int) (Math.random() * 500);
        try {
            Thread.sleep(dormir);
        } catch (InterruptedException e) {}
        terminaLectura();
    }
}
