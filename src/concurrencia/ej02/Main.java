package concurrencia.ej02;

public class Main {
    static final int NUM_LECTORAS = 10;
    static final int NUM_ESCRITORAS = 3;

    public static void main(String[] args) {
        RecursoCompartido recurso1 = new RecursoCompartido();
        RecursoCompartido recurso2 = new RecursoCompartido();
        for (int i = 0; i < NUM_LECTORAS; i++) {
            HebraLectora lectora = new HebraLectora(recurso1, recurso2, "L" + i);
            lectora.start();
        }
        for (int i = 0; i < NUM_ESCRITORAS; i++) {
            HebraEscritora escritora = new HebraEscritora(recurso1, recurso2, "E" + i);
            escritora.start();
        }
    }
}
