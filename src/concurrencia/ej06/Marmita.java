package concurrencia.ej06;

public class Marmita {
    private int raciones;
    private int racionesMax;
    private boolean reservada = false;
    private boolean rellenando;

    public Marmita(int racionesMax) {
        this.racionesMax = racionesMax;
        this.raciones = racionesMax;
        this.rellenando = false;
    }

    public synchronized void comer(String nombre) {
        boolean heAvisado = false;
        while (raciones == 0 || (reservada && !heAvisado)) {
            if (raciones == 0 && !rellenando) {
                rellenando = true;
                heAvisado = true;
                System.out.println(nombre + " encuentra la marmita vacia y avisa al cocinero");
                notifyAll();
            }
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        raciones--;
        System.out.println(nombre + " come (quedan " + raciones + ")" + (heAvisado ? " <- el que aviso" : ""));
        if (heAvisado) {
            reservada = false;
            notifyAll();
        }
    }

    public synchronized void rellenar() {
        while (!rellenando) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        try {
            Thread.sleep((long) (Math.random() * 1000));
        } catch (InterruptedException e) {
        }
        raciones = racionesMax;
        reservada = true;
        rellenando = false;
        System.out.println("Cocinero rellena la marmita (" + raciones + " raciones)");
        notifyAll();
    }
}
