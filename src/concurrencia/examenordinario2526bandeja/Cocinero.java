package concurrencia.examenordinario2526bandeja;

public class Cocinero extends Thread {
    private Bandeja bandeja;
    private String nombre;

    public Cocinero(Bandeja bandeja, String nombre) {
        this.bandeja = bandeja;
        this.nombre = nombre;
    }

    public void run() {
        for (int i = 0; i < 8; i++) {
            try {
                sleep((long) (Math.random() * 200));
            } catch (InterruptedException e) {
            }
            synchronized (bandeja) {
                bandeja.ponerHamburguesa();
                Main.cambia(+1, nombre + " pone una hamburguesa");
            }
        }
    }
}
