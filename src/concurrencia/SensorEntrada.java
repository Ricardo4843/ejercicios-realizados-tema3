package concurrencia;

/**
 * Ejercicio 3.8: hebra del sensor de entrada.
 */
public class SensorEntrada extends Thread {

    private String id;
    private Parking parking;

    public SensorEntrada(String id, Parking parking) {
        this.id = id;
        this.parking = parking;
    }

    public void run() {
        while (true) {
            if (detectaVehiculo(id))
                parking.entra(id);
        }
    }

    // Dado por programado en el enunciado: aquí simula que llega un coche de vez en cuando
    boolean detectaVehiculo(String id) {
        try {
            Thread.sleep((long) (Math.random() * 300));
        } catch (InterruptedException e) {
        }
        return Math.random() < 0.5;
    }
}
