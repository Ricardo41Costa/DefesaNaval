package jogodobarco;

import java.awt.EventQueue;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;

public class JogoDoBarco extends JFrame implements Variaveis {

    static JogoDoBarco ex = new JogoDoBarco();

    public JogoDoBarco() {

        iniciarJogo();
    }

    private void iniciarJogo() {
        
        add(new Jogo());
        setTitle("Defesa Naval v10_9");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(BOARD_LARGURA, BOARD_ALTURA);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setResizable(false);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
        ex.setVisible(true);
     });
        
        }
}
