package concurrencia.examenextraordinario2526jefe;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        JefeFinal jefe = new JefeFinal();
        Thread[] jugadores = new Thread[4];
        for (int i = 0; i < jugadores.length; i++) {
            jugadores[i] = new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    jefe.recibirDano(100);
                }
            });
            jugadores[i].start();
        }
        for (Thread t : jugadores) {
            t.join();
        }
        System.out.println("Salud tras 80 golpes de 100: " + jefe.getSalud() + " (deberia ser 2000)");

        Thread resurreccion = new Thread(() -> {
            jefe.autoResurreccion();
            System.out.println("El jefe resucita");
        });
        resurreccion.start();
        Thread.sleep(200);
        System.out.println("Con salud " + jefe.getSalud() + " sigue esperando: " + resurreccion.isAlive());
        for (int j = 0; j < 20; j++) {
            jefe.recibirDano(100);
        }
        resurreccion.join(2000);
        System.out.println("Salud final: " + jefe.getSalud() + " (deberia ser 10000)");
    }
}
