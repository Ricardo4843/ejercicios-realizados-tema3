package concurrencia.examen2014laboratorio;

public class Main {
    static final int PUESTOS = 3;
    static final int MAX_PENDIENTES = 2;
    static final int NUM_INVESTIGADORES = 6;

    public static void main(String[] args) throws InterruptedException {
        Laboratorio laboratorio = new Laboratorio(PUESTOS, MAX_PENDIENTES);
        Analista analista = new Analista(laboratorio);
        analista.setDaemon(true);
        analista.start();
        Investigador[] investigadores = new Investigador[NUM_INVESTIGADORES];
        for (int i = 0; i < NUM_INVESTIGADORES; i++) {
            investigadores[i] = new Investigador(laboratorio, "I" + i);
            investigadores[i].start();
        }
        for (Investigador inv : investigadores) {
            inv.join();
        }
        System.out.println("Fin: todos han salido");
    }
}
