package concurrencia.examen2014laboratorio;

public class Analista extends Thread {
    private Laboratorio laboratorio;

    public Analista(Laboratorio laboratorio) {
        this.laboratorio = laboratorio;
    }

    public void run() {
        while (true) {
            laboratorio.recogeMuestra();
            System.out.println("    Analista recoge una muestra");
            try {
                sleep(50);
            } catch (InterruptedException e) {
            }
            laboratorio.entregaResultado();
            System.out.println("    Analista entrega el resultado");
        }
    }
}
