package concurrencia.examenordinario2526bandeja;

public class Limpiador extends Thread {
    private Bandeja bandeja;

    public Limpiador(Bandeja bandeja) {
        this.bandeja = bandeja;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                sleep(300);
                bandeja.setLimpiando(true);
                System.out.println("-- Limpiador empieza a limpiar");
                sleep(150);
                System.out.println("-- Limpiador termina");
                bandeja.setLimpiando(false);
            } catch (InterruptedException e) {
            }
        }
    }
}
