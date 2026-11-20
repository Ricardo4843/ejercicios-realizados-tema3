package concurrencia.examen2015laboratorio;

public class Laboratorio {
    private int instrumentos;
    private int todos;
    private boolean revisando = false;

    public Laboratorio(int instrumentos) {
        this.instrumentos = instrumentos;
        this.todos = instrumentos;
    }

    public synchronized void toma() {
        while (instrumentos == 0 || revisando) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        instrumentos--;
    }

    public synchronized void devuelve() {
        instrumentos++;
        notifyAll();
    }

    public synchronized void revisar() {
        revisando = true;
        while (instrumentos != todos) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
    }

    public synchronized void terminar() {
        revisando = false;
        notifyAll();
    }
}
