package concurrencia.examen2019dispensador;

public class Dispensador {
    private final int N;
    private int muestras;
    private boolean aviso = false;
    private boolean reservado = false;

    public Dispensador(int n) {
        this.N = n;
        this.muestras = n;
    }

    public synchronized void retirar() {
        boolean heavisado = false;
        while (muestras <= 0 || (reservado && !heavisado)) {
            if (muestras <= 0 && !aviso) {
                aviso = true;
                heavisado = true;
                notifyAll();
            }
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        if (heavisado) {
            reservado = false;
            notifyAll();
        }
        muestras--;
    }

    public synchronized void reponer() {
        while (!aviso) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        aviso = false;
        reservado = true;
        muestras = N;
        notifyAll();
    }
}
