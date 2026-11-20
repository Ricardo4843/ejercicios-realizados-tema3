package concurrencia.examen2017cajon;

public class Muestra {
    private static int contador = 0;
    private final int id;

    public Muestra() {
        synchronized (Muestra.class) {
            id = ++contador;
        }
        System.out.println("Generada muestra " + id);
    }

    public int getId() {
        return id;
    }
}
