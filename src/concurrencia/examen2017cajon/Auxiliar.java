package concurrencia.examen2017cajon;

public class Auxiliar extends Thread {
    private Cajon cajon;

    public Auxiliar(Cajon cajon) {
        this.cajon = cajon;
    }

    public void run() {
        while (true) {
            try {
                int n = 60000 + (int) (Math.random() * 540000);
                sleep(n / Main.ESCALA);
            } catch (InterruptedException e) {
            }
            Muestra muestra = new Muestra();
            cajon.coloca(muestra);
        }
    }
}
