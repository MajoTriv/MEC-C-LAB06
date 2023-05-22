import java.util.Random;
import javax.swing.SwingUtilities;

public class PlayWorker implements Runnable {

    private JFigura jfigura;
    private int value = 0;
    private int wait = 6; // milisegundos

    /**
     * Constructor de clase
     */
    PlayWorker(JFigura jc) {
        jfigura = jc;
    }

    @Override
    public void run() {
        int giros = 0;
        Random random = new Random();
        int n = random.nextInt(3) + 1;
        int rotate = random.nextInt(7) + 4;
        while (giros < rotate) // rotacion
        {
            giros++;
            value = 0;
            for (int i = 0; i < (n * 10); i++) {
                value -= 10;
                jfigura.updateY(value);
                try {
                    Thread.sleep(wait);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            wait += 2;

            // Actualiza la interfaz después de cada paso del bucle de rotación
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    jfigura.repaint();
                }
            });
        }
    }

    public int getValue() {
        return value;
    }
}

