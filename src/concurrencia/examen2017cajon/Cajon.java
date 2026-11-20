package concurrencia.examen2017cajon;

import java.util.*;

public class Cajon {
    private final int N = 10;
    private List<Muestra> cajon = new ArrayList<Muestra>();

    public synchronized void coloca(Muestra muestra) {
        while (cajon.size() >= N) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        cajon.add(muestra);
        notifyAll();
    }

    public synchronized Muestra extrae() {
        while (cajon.size() <= 0) {
            try {
                wait();
            } catch (InterruptedException e) {
            }
        }
        notifyAll();
        return cajon.remove(0);
    }
}
