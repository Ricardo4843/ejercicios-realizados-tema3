package concurrencia.ej03;

public class HebraEscritora extends Thread {
    private RecursoCompartido recursoCompartido;
    private String nombre;
    private int prioridad;

    public HebraEscritora(RecursoCompartido recursoCompartido, String nombre, int prioridad) {
        this.recursoCompartido = recursoCompartido;
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    public void run() {
        recursoCompartido.escribir(nombre, prioridad);
    }
}
