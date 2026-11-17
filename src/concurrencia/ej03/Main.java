package concurrencia.ej03;

public class Main {
    static final int NUM_LECTORAS = 10;
    static final int NUM_ESCRITORAS = 5;

    public static void main(String[] args) {
        RecursoCompartido recurso = new RecursoCompartido();
        for (int i = 0; i < NUM_LECTORAS; i++) {
            HebraLectora lectora = new HebraLectora(recurso, "L" + i, (int) (Math.random() * 3));
            lectora.start();
        }
        for (int i = 0; i < NUM_ESCRITORAS; i++) {
            HebraEscritora escritora = new HebraEscritora(recurso, "E" + i, (int) (Math.random() * 3));
            escritora.start();
        }
    }
}
