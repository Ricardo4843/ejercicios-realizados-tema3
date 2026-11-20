package concurrencia.examen2016datos;

public class Main {
    private static int guardando = 0;
    private static int consultando = 0;

    public static synchronized void entra(String nombre, boolean guarda) {
        if (guarda) {
            guardando++;
        } else {
            consultando++;
        }
        System.out.println(nombre + (guarda ? " guarda" : " consulta") + " (guardando: " + guardando + ", consultando: " + consultando + ")");
        if (guardando > 1 || (guardando == 1 && consultando > 0)) {
            System.out.println("ERROR");
        }
    }

    public static synchronized void sale(boolean guarda) {
        if (guarda) {
            guardando--;
        } else {
            consultando--;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Datos datos = new Datos();
        Ingeniero[] ingenieros = new Ingeniero[7];
        for (int i = 0; i < ingenieros.length; i++) {
            boolean guarda = i < 2;
            ingenieros[i] = new Ingeniero(datos, (guarda ? "G" : "C") + i, guarda);
            ingenieros[i].start();
        }
        for (Ingeniero ing : ingenieros) {
            ing.join();
        }
        System.out.println("Fin");
    }
}
