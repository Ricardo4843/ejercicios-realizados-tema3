package concurrencia;

/**
 * Ejercicio 3.8: simulación del parking con 3 plazas, 2 entradas, 1 salida y el supervisor.
 * Los sensores no paran nunca (while true), así que la simulación se corta a los 21 segundos.
 */
public class Ejercicio8Parking {

    public static void main(String[] args) throws InterruptedException {
        Parking parking = new Parking(3);
        Thread[] hebras = {
            new SensorEntrada("Entrada N", parking),
            new SensorEntrada("Entrada S", parking),
            new SensorSalida("Salida S", parking),
            new Supervisor(parking)
        };
        for (Thread h : hebras) {
            h.setDaemon(true);
            h.start();
        }
        Thread.sleep(21000);
        System.out.println("Fin de la simulación. Ocupadas: " + parking.ocupacion());
    }
}
