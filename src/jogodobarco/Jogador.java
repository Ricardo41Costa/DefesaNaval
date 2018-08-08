package jogodobarco;

import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;

/**
 *Esta classe é responsavel pela inicialização do jogador e das suas propriedades.
 * 
 * @author Ricardo Costa
 */
public class Jogador extends Sprite implements Variaveis {

    private final int START_Y = 620;
    private final int START_X = 358;

    private int width;

    /**
     *Este método inicializa o jogador.
     */
    public Jogador() {

        iniciar_Jogador();
    }

    private void iniciar_Jogador() {
        
        ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource("imagens/jogador.png"));

        width = ii.getImage().getWidth(null);

        setImage(ii.getImage());
        setX(START_X);
        setY(START_Y);
    }

    /**
     *Este método define a direção que o jogador navega.
     */
    public void agir() {
        
        x += dx;
        
        if (x <= 2) {
            x = 2;
        }
        
        if (x >= BOARD_LARGURA - 50) {
            x = BOARD_LARGURA - 50;
        }
    }

    public void keyPressed(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
        
            dx = -3;
        }

        if (key == KeyEvent.VK_RIGHT  || key == KeyEvent.VK_D) {
        
            dx = 3;
        }
    }
    
    public void keyReleased(KeyEvent e) {
        
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {
        
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT  || key == KeyEvent.VK_D) {
        
            dx = 0;
        }
    }
}