package concurrencia.examen2015laboratorio;

public class Tecnico extends Thread {
    private Laboratorio laboratorio;

    public Tecnico(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public void run() {
        for (int i = 0; i < 3; i++) {
            try {
                sleep(250);
                laboratorio.revisar();
                Main.revision(true);
                sleep(100);
                Main.revision(false);
                laboratorio.terminar();
            } catch (InterruptedException e) {
            }
        }
    }
}
