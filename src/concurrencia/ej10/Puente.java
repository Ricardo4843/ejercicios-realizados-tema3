package concurrencia.ej10;

public class Puente {
    private int pesoTotal = 15000;
    private int maxCoches = 10;
    private int pesoActual = 0;
    private int coches = 0;
    private int ambulanciasEsperando = 0;

    public synchronized void entrarPuente(int peso, boolean ambulancia) {
        if (ambulancia) {
            ambulanciasEsperando++;
            while ((peso + pesoActual) > pesoTotal || coches >= maxCoches) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
            ambulanciasEsperando--;
            notifyAll();
        } else {
            while ((peso + pesoActual) > pesoTotal || coches >= maxCoches || ambulanciasEsperando > 0) {
                try {
                    wait();
                } catch (InterruptedException e) {
                }
            }
        }
        pesoActual += peso;
        coches++;
    }

    public synchronized void salirPuente(int peso) {
        coches--;
        pesoActual -= peso;
        notifyAll();
    }
}
