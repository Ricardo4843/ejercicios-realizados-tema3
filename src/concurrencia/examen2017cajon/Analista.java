package concurrencia.examen2017cajon;

public class Analista extends Thread {
    private Cajon cajon;

    public Analista(Cajon cajon) {
        this.cajon = cajon;
    }

    public void run() {
        while (true) {
            Muestra muestra = cajon.extrae();
            System.out.println("    Analista analiza la muestra " + muestra.getId());
            try {
                sleep((180000 + (long) (Math.random() * 120000)) / Main.ESCALA);
            } catch (InterruptedException e) {
            }
        }
    }
}
