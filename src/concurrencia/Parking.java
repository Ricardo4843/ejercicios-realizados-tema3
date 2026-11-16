package concurrencia;

/**
 * Ejercicio 3.8: monitor del parking.
 */
public class Parking {

    private int plazas;
    private int ocupado = 0;

    public Parking(int plazas) {
        this.plazas = plazas;
    }

    public synchronized void entra(String id) {
        while (ocupado >= plazas) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        ocupado++;
        abrirBarrera(id);
    }

    public synchronized void sale(String id) {
        if (ocupado > 0) {
            ocupado--;
            abrirBarrera(id);
            notifyAll();
        }
    }

    public synchronized int ocupacion() {
        return ocupado;
    }

    // Dado por programado en el enunciado: aquí solo lo simula
    void abrirBarrera(String id) {
        System.out.println("Barrera " + id + " abierta. Ocupadas: " + ocupado + "/" + plazas);
    }
}
