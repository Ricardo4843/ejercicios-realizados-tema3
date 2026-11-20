package concurrencia.examen2016datos;

public class Datos {
    private int consultando = 0;
    private boolean guardando = false;

    public synchronized void guardar(Clave clave) {
        while (guardando || consultando > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        guardando = true;
    }

    public synchronized void consultar(Clave clave) {
        while (guardando) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        consultando++;
    }

    public synchronized void terminar(Clave clave) {
        if (guardando) {
            guardando = false;
        } else {
            consultando--;
        }
        notifyAll();
    }
}
