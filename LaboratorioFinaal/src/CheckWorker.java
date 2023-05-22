import java.awt.Color;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class CheckWorker implements Runnable {

    private JLabel label;
    private JTextField credito;
    private JTextField apuesta;
    private JButton boton;
    private PlayWorker playWorkerA;
    private PlayWorker playWorkerB;
    private PlayWorker playWorkerC;

    /**
     * Constructor de clase
     */
    CheckWorker(PlayWorker pwA, PlayWorker pwB, PlayWorker pwC, JLabel lbEstado, JTextField lbCredito, JTextField lbApuesta, JButton lbBoton) {
        this.playWorkerA = pwA;
        this.playWorkerB = pwB;
        this.playWorkerC = pwC;
        label = lbEstado;
        credito = lbCredito;
        apuesta = lbApuesta;
        boton = lbBoton;
    }

    @Override
    public void run() {
        int val1 = playWorkerA.getValue();
        int val2 = playWorkerB.getValue();
        int val3 = playWorkerC.getValue();
        if (val1 == val2 && val2 == val3) {
            credito.setText(String.valueOf(Integer.parseInt(credito.getText()) + Integer.parseInt(apuesta.getText())));
            label.setForeground(Color.yellow);
            label.setText("¡GANASTE!");
        } else {
            credito.setText(String.valueOf(Integer.parseInt(credito.getText()) - Integer.parseInt(apuesta.getText())));
            label.setForeground(Color.red);
            label.setText("¡PERDISTE!");
        }
        boton.setEnabled(true);
    }

}
