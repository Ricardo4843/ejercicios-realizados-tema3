package concurrencia.ej10;

public class Main {
    static final int NUM_VEHICULOS = 30;
    private static int coches = 0;
    private static int peso = 0;

    public static synchronized void entra(String nombre, int p) {
        coches++;
        peso += p;
        System.out.println(nombre + " entra (" + p + " kg) -> en el puente: " + coches + " vehiculos, " + peso + " kg");
        if (coches > 10 || peso > 15000) {
            System.out.println("ERROR: se ha superado el limite");
        }
    }

    public static synchronized void sale(String nombre, int p) {
        coches--;
        peso -= p;
        System.out.println(nombre + " sale");
    }

    public static void main(String[] args) {
        Puente puente = new Puente();
        for (int i = 0; i < NUM_VEHICULOS; i++) {
            boolean ambulancia = i % 6 == 0;
            int p = ambulancia ? 3000 : 800 + (int) (Math.random() * 4000);
            String nombre = (ambulancia ? "Ambulancia" : "Coche") + i;
            new Vehiculo(puente, nombre, p, ambulancia).start();
        }
    }
}
