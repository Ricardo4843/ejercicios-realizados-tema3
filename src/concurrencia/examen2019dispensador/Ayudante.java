package concurrencia.examen2019dispensador;

public class Ayudante extends Thread {
    private Dispensador dispensador;

    public Ayudante(Dispensador dispensador) {
        this.dispensador = dispensador;
    }

    public void run() {
        while (true) {
            dispensador.reponer();
            System.out.println("-- Ayudante repone las dosis");
        }
    }
}
