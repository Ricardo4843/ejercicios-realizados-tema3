package concurrencia;

/**
 * Ejercicio 3.8: hebra del sensor de salida.
 */
public class SensorSalida extends Thread {

    private String id;
    private Parking parking;

    public SensorSalida(String id, Parking parking) {
        this.id = id;
        this.parking = parking;
    }

    public void run() {
        while (true) {
            if (detectaVehiculo(id))
                parking.sale(id);
        }
    }

    // Dado por programado en el enunciado: aquí simula que sale un coche de vez en cuando
    boolean detectaVehiculo(String id) {
        try {
            Thread.sleep((long) (Math.random() * 600));
        } catch (InterruptedException e) {
        }
        return Math.random() < 0.5;
    }
}
