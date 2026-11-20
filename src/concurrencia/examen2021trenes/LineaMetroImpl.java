package concurrencia.examen2021trenes;

public class LineaMetroImpl implements LineaMetro {
    private final int N = 3;
    private int trenes = 0;

    public synchronized void entrar() {
        while (trenes >= N) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        trenes++;
    }

    public synchronized void salir() {
        trenes--;
        notifyAll();
    }
}
