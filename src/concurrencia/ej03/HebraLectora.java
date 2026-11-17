package concurrencia.ej03;

public class HebraLectora extends Thread {
    private RecursoCompartido recursoCompartido;
    private String nombre;
    private int prioridad;

    public HebraLectora(RecursoCompartido recursoCompartido, String nombre, int prioridad) {
        this.recursoCompartido = recursoCompartido;
        this.nombre = nombre;
        this.prioridad = prioridad;
    }

    public void run() {
        recursoCompartido.leer(nombre, prioridad);
    }
}
