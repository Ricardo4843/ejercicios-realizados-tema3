package concurrencia.ej01;

public class HebraEscritora extends Thread {
    private RecursoCompartido recursoCompartido;
    private String nombre;

    public HebraEscritora(RecursoCompartido recursoCompartido, String nombre) {
        this.recursoCompartido = recursoCompartido;
        this.nombre = nombre;
    }

    public void run() {
        recursoCompartido.escribir(nombre);
    }
}
