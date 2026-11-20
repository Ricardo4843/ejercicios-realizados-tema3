package concurrencia.examen2021trenes;

public class Main {
    static final int NUM_TRENES = 8;
    private static int moviendose = 0;

    public static synchronized void empieza(String id) {
        moviendose++;
        System.out.println(id + " empieza a moverse (en la linea: " + moviendose + ")");
        if (moviendose > 3) {
            System.out.println("ERROR: mas de 3 trenes moviendose");
        }
    }

    public static synchronized void termina(String id, Estacion destino) {
        moviendose--;
        System.out.println("    " + id + " llega a " + destino.getNombre());
    }

    public static void main(String[] args) throws InterruptedException {
        LineaMetro linea = new LineaMetroImpl();
        Estacion destino = new Estacion("Sol");
        Thread[] hebras = new Thread[NUM_TRENES];
        for (int i = 0; i < NUM_TRENES; i++) {
            Tren tren = new Tren("Tren" + i, linea);
            hebras[i] = tren.arrancaTren(destino, true, 1);
        }
        for (Thread h : hebras) {
            h.join();
        }
        System.out.println("Fin");
    }
}
