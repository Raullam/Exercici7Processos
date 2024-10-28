package Ex7;

public class Main {
    public static void main(String[] args)  {
        int num = 10;

        CompteEnrrere compteEnrrere = new CompteEnrrere(num);
        ThreadGroup GrupDeFils = new ThreadGroup("GrupDeFils");

        Thread fil1 = new Thread(GrupDeFils, () -> {
            for (int i = 1; i <= num; i++) {
                try {
                    compteEnrrere.comprova();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        fil1.start();

        Thread fil2 = new Thread(GrupDeFils, () -> {
            for (int i = 1; i <= num; i++) {
                try {
                    compteEnrrere.resta();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        fil2.start();

        while (GrupDeFils.activeCount()!= 0){
            try {
                Thread.sleep(50);
                //Este pequeño retraso evita que el while se ejecute constantemente y ahorre recursos del procesador mientras espera.
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("TIMEOUT");
    }
}