package concurrencia.ej10;

public class Vehiculo extends Thread {
    private Puente puente;
    private String nombre;
    private int peso;
    private boolean ambulancia;

    public Vehiculo(Puente puente, String nombre, int peso, boolean ambulancia) {
        this.puente = puente;
        this.nombre = nombre;
        this.peso = peso;
        this.ambulancia = ambulancia;
    }

    public void run() {
        try {
            sleep((long) (Math.random() * 500));
            puente.entrarPuente(peso, ambulancia);
            Main.entra(nombre, peso);
            sleep(200 + (long) (Math.random() * 300));
            Main.sale(nombre, peso);
            puente.salirPuente(peso);
        } catch (InterruptedException e) {
        }
    }
}
