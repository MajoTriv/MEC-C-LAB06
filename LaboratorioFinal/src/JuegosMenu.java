import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MajoArcadeMenu extends JFrame {

    public MajoArcadeMenu() {
        setTitle("MAJO ARCADE");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        JLabel titleLabel = new JLabel("MAJO ARCADE");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Press Start 2P", Font.BOLD, 48));
        titleLabel.setBounds(200, 100, 400, 100);
        panel.add(titleLabel);

        JButton diceButton = new JButton("Juego de Dados");
        diceButton.setBounds(300, 250, 200, 50);
        diceButton.setFont(new Font("Press Start 2P", Font.PLAIN, 18));
        diceButton.setFocusPainted(false);
        diceButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DadosJuego dadosJuego = new DadosJuego();
                dadosJuego.setVisible(true);
            }
        });
        panel.add(diceButton);

        JButton slotsButton = new JButton("Juego de Tragamonedas");
        slotsButton.setBounds(250, 350, 300, 50);
        slotsButton.setFont(new Font("Press Start 2P", Font.PLAIN, 18));
        slotsButton.setFocusPainted(false);
        slotsButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                TragamonedasJuego tragamonedasJuego = new TragamonedasJuego();
                tragamonedasJuego.setVisible(true);
            }
        });
        panel.add(slotsButton);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MajoArcadeMenu menu = new MajoArcadeMenu();
                menu.setVisible(true);
            }
        });
    }
}

// Clase para el juego de dados
class DadosJuego extends JFrame {

    private JLabel dice1Label;
    private JLabel dice2Label;
    private JButton rollButton;

    public DadosJuego() {
        setTitle("Juego de Dados");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(800, 600));

        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(Color.BLACK);

        dice1Label = new JLabel();
        dice1Label.setBounds(200, 200, 200, 200);
        panel.add(dice1Label);

        dice2Label = new JLabel();
        dice2Label.setBounds(400, 200, 200, 200);
        panel.add(dice2Label);

        rollButton = new JButton("Lanzar Dados");
        rollButton.setBounds(300, 450, 200, 50);
        rollButton.setFont(new Font("Arial", Font.PLAIN, 18));
        rollButton.setFocusPainted(false);
        rollButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                rollDice();
            }
        });
        panel.add(rollButton);

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void rollDice() {
        rollButton.setEnabled(false);
        DiceRollingThread dice1Thread = new DiceRollingThread(dice1Label);
        DiceRollingThread dice2Thread = new DiceRollingThread(dice2Label);
        dice1Thread.start();
        dice2Thread.start();

        new Thread(new Runnable() {
            public void run() {
                try {
                    dice1Thread.join();
                    dice2Thread.join();
                    int dice1Value = dice1Thread.getDiceValue();
                    int dice2Value = dice2Thread.getDiceValue();
                    int sum = dice1Value + dice2Value;
                    JOptionPane.showMessageDialog(DadosJuego.this, "Los dados suman: " + sum);
                    rollButton.setEnabled(true);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                DadosJuego dadosJuego = new DadosJuego();
                dadosJuego.setVisible(true);
            }
        });
    }
}

// Clase para el movimiento de los dados
class DiceRollingThread extends Thread {

    private static final String[] diceImages = {
        // Rutas de las imágenes de los dados
        // Puedes reemplazar las rutas con las imágenes correspondientes
        "C:/Users/maria/OneDrive/Documentos/NetBeansProjects/LaboratorioFinal/src/Imagenes/d1.png",
        "C:/Users/maria/OneDrive/Documentos/NetBeansProjects/LaboratorioFinal/src/Imagenes/d2.png",
        "C:/Users/maria/OneDrive/Documentos/NetBeansProjects/LaboratorioFinal/src/Imagenes/d3.png",
        "C:/Users/maria/OneDrive/Documentos/NetBeansProjects/LaboratorioFinal/src/Imagenes/d4.png",
        "C:/Users/maria/OneDrive/Documentos/NetBeansProjects/LaboratorioFinal/src/Imagenes/d5.png",
        "C:/Users/maria/OneDrive/Documentos/NetBeansProjects/LaboratorioFinal/src/Imagenes/d6.png",
  
    };

    private JLabel diceLabel;
    private int diceValue;

    public DiceRollingThread(JLabel diceLabel) {
        this.diceLabel = diceLabel;
    }

    public void run() {
        int totalFrames = 30;
        for (int i = 0; i < totalFrames; i++) {
            int randomValue = (int) (Math.random() * 6);
            diceValue = randomValue + 1;
            ImageIcon diceImage = new ImageIcon(getClass().getResource(diceImages[randomValue]));
            diceLabel.setIcon(diceImage);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public int getDiceValue() {
        return diceValue;
    }
}

