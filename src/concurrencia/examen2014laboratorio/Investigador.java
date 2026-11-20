package concurrencia.examen2014laboratorio;

public class Investigador extends Thread {
    private Laboratorio laboratorio;
    private String nombre;

    public Investigador(Laboratorio laboratorio, String nombre) {
        this.laboratorio = laboratorio;
        this.nombre = nombre;
    }

    public void run() {
        try {
            while (!laboratorio.entra()) {
                System.out.println(nombre + " no encuentra puesto y vuelve luego");
                sleep(200);
            }
            System.out.println(nombre + " ocupa un puesto");
            laboratorio.entregaMuestra();
            System.out.println(nombre + " entrega su muestra");
            laboratorio.esperaResultado();
            System.out.println(nombre + " recibe un resultado y sale");
            laboratorio.sale();
        } catch (InterruptedException e) {
        }
    }
}
