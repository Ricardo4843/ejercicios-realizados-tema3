package concurrencia;

/**
 * Ejercicio 3.8: hebra que cada 10 segundos imprime las plazas ocupadas.
 */
class Supervisor extends Thread {

    private Parking parking;

    public Supervisor(Parking parking) {
        this.parking = parking;
    }

    public void run() {
        while (true) {
            try {
                sleep(10000);
                System.out.println("SUPERVISOR: plazas ocupadas = " + parking.ocupacion());
            } catch (InterruptedException e) {
            }
        }
    }
}
