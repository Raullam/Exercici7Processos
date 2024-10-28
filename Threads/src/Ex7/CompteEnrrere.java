package Ex7;

public class CompteEnrrere
{
    private final int TEMPS = 200;

    private int valInicial;
    private int numero;
    private boolean tacWait = true;

    public CompteEnrrere(int numero)
    {
        this.numero = numero;
        valInicial = numero;
    }

    public synchronized void resta() throws InterruptedException {
        while (tacWait) {
            wait();
        }
        System.out.println(numero--);
        Thread.sleep(TEMPS);
        tacWait = true;
        this.notify();
    }

    public synchronized void comprova() throws InterruptedException {
        while (!tacWait) {
            wait();
        }

        if (numero == valInicial * 3/4) {
            System.out.println("Queden 3/4 parts del compte enrrere");
        }
        if (numero == valInicial / 2) {
            System.out.println("Queda la mitat del compte enrrere");
        }
        if (numero == valInicial / 4) {
            System.out.println("Queda 1/4 part del compte enrrere");
        }

        Thread.sleep(TEMPS);
        tacWait = false;
        // Notify the producer that the item has been consumed
        this.notify();
    }
}