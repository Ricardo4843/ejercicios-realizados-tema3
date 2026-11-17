package concurrencia.ej01;

public class Main {
    static final int NUM_LECTORAS = 10;
    static final int NUM_ESCRITORAS = 3;

    public static void main(String[] args) {
        RecursoCompartido recurso = new RecursoCompartido();
        for (int i = 0; i < NUM_LECTORAS; i++) {
            HebraLectora lectora = new HebraLectora(recurso, "L" + i);
            lectora.start();
        }
        for (int i = 0; i < NUM_ESCRITORAS; i++) {
            HebraEscritora escritora = new HebraEscritora(recurso, "E" + i);
            escritora.start();
        }
    }
}
