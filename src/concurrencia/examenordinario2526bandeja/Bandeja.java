package concurrencia.examenordinario2526bandeja;

public class Bandeja {
    private final int N = 5;
    private int numHam = N;
    private boolean limpiando = false;

    public synchronized void ponerHamburguesa() {
        while (numHam >= N || limpiando) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        numHam++;
        notifyAll();
    }

    public synchronized void sacarHamburguesa() {
        while (numHam <= 0 || limpiando) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        numHam--;
        notifyAll();
    }

    public synchronized void setLimpiando(boolean limpiando) {
        this.limpiando = limpiando;
        notifyAll();
    }

    public synchronized boolean getLimpiando() {
        return limpiando;
    }
}
