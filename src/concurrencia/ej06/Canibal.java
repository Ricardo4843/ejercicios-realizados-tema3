package concurrencia.ej06;

public class Canibal extends Thread {
    private Marmita marmita;
    private String nombre;

    public Canibal(Marmita marmita, String nombre) {
        this.marmita = marmita;
        this.nombre = nombre;
    }

    public void run() {
        for (int i = 0; i < 5; i++) {
            try {
                marmita.comer(nombre);
                long n = (long) (Math.random() * 1000);
                sleep(n);
            } catch (InterruptedException e) {
            }
        }
    }
}
