package concurrencia.examen2016datos;

public class Ingeniero extends Thread {
    private Datos datos;
    private String nombre;
    private boolean guarda;

    public Ingeniero(Datos datos, String nombre, boolean guarda) {
        this.datos = datos;
        this.nombre = nombre;
        this.guarda = guarda;
    }

    public void run() {
        Clave clave = new Clave();
        for (int i = 0; i < 4; i++) {
            try {
                sleep((long) (Math.random() * 200));
                if (guarda) {
                    datos.guardar(clave);
                } else {
                    datos.consultar(clave);
                }
                Main.entra(nombre, guarda);
                sleep((long) (Math.random() * 100));
                Main.sale(guarda);
                datos.terminar(clave);
            } catch (InterruptedException e) {
            }
        }
    }
}
