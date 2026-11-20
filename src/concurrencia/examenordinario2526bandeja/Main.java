package concurrencia.examenordinario2526bandeja;

public class Main {
    private static int hamburguesas = 5;

    public static synchronized void cambia(int d, String texto) {
        hamburguesas += d;
        System.out.println(texto + " (quedan " + hamburguesas + ")");
        if (hamburguesas < 0 || hamburguesas > 5) {
            System.out.println("ERROR: fuera de la capacidad");
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Bandeja bandeja = new Bandeja();
        Thread[] hebras = new Thread[7];
        for (int i = 0; i < 3; i++) {
            hebras[i] = new Cocinero(bandeja, "Cocinero" + i);
            hebras[i + 3] = new Cliente(bandeja, "Cliente" + i);
        }
        hebras[6] = new Limpiador(bandeja);
        for (Thread h : hebras) {
            h.start();
        }
        for (Thread h : hebras) {
            h.join();
        }
        System.out.println("Fin");
    }
}
