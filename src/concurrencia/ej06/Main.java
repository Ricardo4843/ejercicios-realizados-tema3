package concurrencia.ej06;

public class Main {
    static final int NUM_CANIBALES = 6;
    static final int RACIONES = 4;

    public static void main(String[] args) {
        Marmita marmita = new Marmita(RACIONES);
        Cocinero cocinero = new Cocinero(marmita);
        cocinero.setDaemon(true);
        cocinero.start();
        for (int i = 0; i < NUM_CANIBALES; i++) {
            Canibal canibal = new Canibal(marmita, "C" + i);
            canibal.start();
        }
    }
}
