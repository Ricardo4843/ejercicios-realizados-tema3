package concurrencia.examenextraordinario2526jefe;

public class JefeFinal {
    private int salud = 10000;

    public synchronized void recibirDano(int cantidad) {
        int saludActual = salud;
        salud = saludActual - cantidad;
        notifyAll();
    }

    public synchronized void autoResurreccion() {
        while (salud > 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        salud = 10000;
    }

    public synchronized int getSalud() {
        return salud;
    }
}
