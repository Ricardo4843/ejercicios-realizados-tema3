package concurrencia.examen2021trenes;

public class Tren {
    private String id;
    private LineaMetro linea;
    private Thread hebra;

    public Tren(String id, LineaMetro linea) {
        this.id = id;
        this.linea = linea;
    }

    public void moverElTren(Estacion destino, boolean ida, int vueltas) {
        Main.empieza(id);
        try {
            Thread.sleep(200 * vueltas + (long) (Math.random() * 200));
        } catch (InterruptedException e) {
        }
        Main.termina(id, destino);
    }

    public Thread arrancaTren(Estacion destino, boolean ida, int vueltas) {
        Thread p = new Thread(() -> {
            linea.entrar();
            moverElTren(destino, ida, vueltas);
            linea.salir();
        });
        p.start();
        hebra = p;
        return hebra;
    }
}
