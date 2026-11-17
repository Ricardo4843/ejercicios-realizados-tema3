package concurrencia.ej02;

public class HebraEscritora extends Thread {
    private RecursoCompartido recursoCompartido1;
    private RecursoCompartido recursoCompartido2;
    private String nombre;

    public HebraEscritora(RecursoCompartido recursoCompartido1, RecursoCompartido recursoCompartido2, String nombre) {
        this.recursoCompartido1 = recursoCompartido1;
        this.recursoCompartido2 = recursoCompartido2;
        this.nombre = nombre;
    }

    public void run() {
        if (Math.random() < 0.5) {
            recursoCompartido1.escribir(nombre);
        } else {
            recursoCompartido2.escribir(nombre);
        }
    }
}
