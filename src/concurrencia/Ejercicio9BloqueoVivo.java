package concurrencia;

/**
 * Ejercicio 3.9: bloqueo vivo (livelock).
 * Hebra 1 coge el recurso 0 y Hebra 2 el recurso 1; ninguna consigue el segundo,
 * las dos devuelven el suyo y vuelven a empezar a la vez. Están activas pero no avanzan.
 * Soluciones: ir por turnos, coger los recursos en el mismo orden, o esperar un tiempo aleatorio antes de reintentar.
 * Cambio respecto al enunciado: se sincroniza sobre el array (recurso) y no sobre recurso[0] / recurso[1],
 * porque esos son valores del enum que cambian (LIBRE/OCUPADO) y no sirven como cerrojo fijo.
 */
public class Ejercicio9BloqueoVivo {

    enum Estado { LIBRE, OCUPADO }

    static Estado recurso[] = { Estado.LIBRE, Estado.LIBRE };
    static volatile boolean success = false;
    static int intentos1 = 0;
    static int intentos2 = 0;

    // Pausa entre coger el primer recurso y el segundo (como el sleep(150) del Trabajador de la clase 11),
    // para que las dos hebras vayan a la vez y se vea el bloqueo vivo
    static void pausa() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
        }
    }

    static void hebra1() {
        while (!success) {
            intentos1++;
            while (true) {                          // adquirir recurso 0
                synchronized (recurso) {
                    if (recurso[0] == Estado.LIBRE) {
                        recurso[0] = Estado.OCUPADO;
                        break;
                    }
                }
            }
            pausa();
            synchronized (recurso) {                // adquirir recurso 1
                if (recurso[1] == Estado.LIBRE) {
                    recurso[1] = Estado.OCUPADO;
                    success = true;
                }
            }
            if (!success) {                         // devolver recurso 0
                synchronized (recurso) {
                    recurso[0] = Estado.LIBRE;
                }
            }
        }
    }

    static void hebra2() {
        while (!success) {
            intentos2++;
            while (true) {                          // adquirir recurso 1
                synchronized (recurso) {
                    if (recurso[1] == Estado.LIBRE) {
                        recurso[1] = Estado.OCUPADO;
                        break;
                    }
                }
            }
            pausa();
            synchronized (recurso) {                // adquirir recurso 0
                if (recurso[0] == Estado.LIBRE) {
                    recurso[0] = Estado.OCUPADO;
                    success = true;
                }
            }
            if (!success) {                         // devolver recurso 1
                synchronized (recurso) {
                    recurso[1] = Estado.LIBRE;
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread h1 = new Thread(Ejercicio9BloqueoVivo::hebra1);
        Thread h2 = new Thread(Ejercicio9BloqueoVivo::hebra2);
        h1.setDaemon(true);
        h2.setDaemon(true);
        h1.start();
        h2.start();
        h1.join(3000);
        h2.join(3000);
        System.out.println("success = " + success + ". Intentos: hebra 1 = " + intentos1 + ", hebra 2 = " + intentos2);
        System.out.println("Cada intento fallido es una vuelta de bloqueo vivo: las dos sueltan y reintentan.");
    }
}
