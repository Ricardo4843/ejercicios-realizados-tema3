package concurrencia.ej01;

public class HebraLectora extends Thread {
    private RecursoCompartido recursoCompartido;
    private String nombre;

    public HebraLectora(RecursoCompartido recursoCompartido, String nombre) {
        this.recursoCompartido = recursoCompartido;
        this.nombre = nombre;
    }

    public void run() {
        recursoCompartido.leer(nombre);
    }
}
