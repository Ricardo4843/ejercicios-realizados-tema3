package concurrencia.examen2014laboratorio;

public class Laboratorio {
    private final int N;
    private final int M;
    private int ocupados = 0;
    private int muestras = 0;
    private boolean resultadoListo = false;

    public Laboratorio(int n, int m) {
        this.N = n;
        this.M = m;
    }

    public synchronized boolean entra() {
        if (ocupados < N) {
            ocupados++;
            return true;
        }
        return false;
    }

    public synchronized void entregaMuestra() {
        while (muestras >= M) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        muestras++;
        notifyAll();
    }

    public synchronized void esperaResultado() {
        while (!resultadoListo) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        resultadoListo = false;
    }

    public synchronized void sale() {
        ocupados--;
    }

    public synchronized void recogeMuestra() {
        while (muestras <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        muestras--;
        notifyAll();
    }

    public synchronized void entregaResultado() {
        resultadoListo = true;
        notifyAll();
    }
}
