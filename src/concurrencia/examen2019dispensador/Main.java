package concurrencia.examen2019dispensador;

public class Main {
    static final int NUM_ANALISTAS = 5;
    static final int DOSIS = 3;

    public static void main(String[] args) throws InterruptedException {
        Dispensador dispensador = new Dispensador(DOSIS);
        Ayudante ayudante = new Ayudante(dispensador);
        ayudante.setDaemon(true);
        ayudante.start();
        Analista[] analistas = new Analista[NUM_ANALISTAS];
        for (int i = 0; i < NUM_ANALISTAS; i++) {
            analistas[i] = new Analista(dispensador, "A" + i);
            analistas[i].start();
        }
        for (Analista a : analistas) {
            a.join();
        }
        System.out.println("Fin: 20 dosis retiradas");
    }
}
