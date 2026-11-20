package concurrencia.examen2019dispensador;

public class Analista extends Thread {
    private Dispensador dispensador;
    private String nombre;

    public Analista(Dispensador dispensador, String nombre) {
        this.dispensador = dispensador;
        this.nombre = nombre;
    }

    public void run() {
        for (int i = 0; i < 4; i++) {
            try {
                sleep((long) (Math.random() * 300));
            } catch (InterruptedException e) {
            }
            dispensador.retirar();
            System.out.println(nombre + " retira una dosis");
        }
    }
}
