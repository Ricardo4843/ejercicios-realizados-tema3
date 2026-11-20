package concurrencia.examen2015laboratorio;

public class Main {
    static final int INSTRUMENTOS = 3;
    static final int NUM_INVESTIGADORES = 6;
    private static int enUso = 0;
    private static boolean revisando = false;

    public static synchronized void usa(int d, String texto) {
        enUso += d;
        System.out.println(texto + " (en uso: " + enUso + ")");
        if (enUso > INSTRUMENTOS || (revisando && d > 0)) {
            System.out.println("ERROR");
        }
    }

    public static synchronized void revision(boolean empieza) {
        revisando = empieza;
        System.out.println(empieza ? "-- Tecnico empieza a revisar (en uso: " + enUso + ")" : "-- Tecnico termina");
        if (empieza && enUso != 0) {
            System.out.println("ERROR: revisa con instrumentos en uso");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Laboratorio laboratorio = new Laboratorio(INSTRUMENTOS);
        Tecnico tecnico = new Tecnico(laboratorio);
        tecnico.start();
        Investigador[] investigadores = new Investigador[NUM_INVESTIGADORES];
        for (int i = 0; i < NUM_INVESTIGADORES; i++) {
            investigadores[i] = new Investigador(laboratorio, "I" + i);
            investigadores[i].start();
        }
        for (Investigador inv : investigadores) {
            inv.join();
        }
        tecnico.join();
        System.out.println("Fin");
    }
}
