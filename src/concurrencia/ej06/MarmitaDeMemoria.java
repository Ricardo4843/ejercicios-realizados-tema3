package concurrencia.ej06;

/**
 * Ejercicio 6 (caníbales) repetido de memoria: monitor Marmita con comer() y rellenar().
 */
public class MarmitaDeMemoria {
    private int raciones;
    private final int racionesMax;
    private boolean rellenando = false;
    private boolean reservada = false;

    public MarmitaDeMemoria(int raciones) {
        this.raciones = raciones;
        this.racionesMax = raciones;
    }

    public synchronized void comer() {
        boolean heReservado = false;
        while (raciones <= 0 || (reservada && !heReservado)) {
            if (!rellenando && raciones <= 0) {
                heReservado = true;
                rellenando = true;
                notifyAll();
            }
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        if (heReservado) {
            reservada = false;
            notifyAll();
        }
        raciones--;
    }

    public synchronized void rellenar() {
        while (!rellenando) {
            try {
                wait();
            } catch (InterruptedException e) {}
        }
        reservada = true;
        raciones = racionesMax;
        rellenando = false;
        notifyAll();
    }

    public static void main(String[] args) throws InterruptedException {
        MarmitaDeMemoria marmita = new MarmitaDeMemoria(3);
        Thread cocinero = new Thread(() -> {
            while (true) marmita.rellenar();
        });
        cocinero.setDaemon(true);
        cocinero.start();
        Thread[] canibales = new Thread[4];
        for (int i = 0; i < canibales.length; i++) {
            int id = i;
            canibales[i] = new Thread(() -> {
                for (int k = 0; k < 5; k++) {
                    marmita.comer();
                    System.out.println("Canibal " + id + " come");
                }
            });
            canibales[i].start();
        }
        for (Thread c : canibales) c.join();
        System.out.println("Todos han comido 5 veces: sin interbloqueo");
    }
}
