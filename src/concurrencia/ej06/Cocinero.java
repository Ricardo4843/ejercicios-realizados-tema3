package concurrencia.ej06;

public class Cocinero extends Thread {
    private Marmita marmita;

    public Cocinero(Marmita marmita) {
        this.marmita = marmita;
    }

    public void run() {
        while (true) {
            marmita.rellenar();
        }
    }
}
