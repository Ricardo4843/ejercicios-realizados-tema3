package concurrencia.examen2015laboratorio;

public class Investigador extends Thread {
    private Laboratorio laboratorio;
    private String nombre;

    public Investigador(Laboratorio laboratorio, String nombre) {
        this.laboratorio = laboratorio;
        this.nombre = nombre;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                sleep((long) (Math.random() * 200));
                laboratorio.toma();
                Main.usa(+1, nombre + " toma un instrumento");
                sleep((long) (Math.random() * 150));
                Main.usa(-1, "    " + nombre + " lo devuelve");
                laboratorio.devuelve();
            } catch (InterruptedException e) {
            }
        }
    }
}
