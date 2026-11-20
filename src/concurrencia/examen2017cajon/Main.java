package concurrencia.examen2017cajon;

public class Main {
    static final int NUM_AUXILIARES = 4;
    static final int ESCALA = 1000; // 1 minuto del enunciado = 60 ms

    public static void main(String[] args) throws InterruptedException {
        Cajon cajon = new Cajon();
        Analista analista = new Analista(cajon);
        analista.setDaemon(true);
        analista.start();
        for (int i = 0; i < NUM_AUXILIARES; i++) {
            Auxiliar auxiliar = new Auxiliar(cajon);
            auxiliar.setDaemon(true);
            auxiliar.start();
        }
        Thread.sleep(5000);
    }
}
