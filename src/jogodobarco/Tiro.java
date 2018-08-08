package jogodobarco;

import javax.swing.ImageIcon;

public class Tiro extends Sprite {

    private final int H_SPACE = 6;
    private final int V_SPACE = 1;

    public Tiro() {
    }

    public Tiro(int x, int y) {

        iniciar_Tiro(x, y);
    }

    private void iniciar_Tiro(int x, int y) {

        ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource("imagens/bala.png"));
        setImage(ii.getImage());
        
        setX(x + H_SPACE);
        setY(y - V_SPACE);
    }
}
