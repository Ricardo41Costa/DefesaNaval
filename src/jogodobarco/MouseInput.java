package jogodobarco;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import jogodobarco.Jogo.ESTADO;
import static jogodobarco.Jogo.estado;

public class MouseInput implements Variaveis, MouseListener {

    @Override
    public void mousePressed(MouseEvent me) {

        int mx = me.getX();
        int my = me.getY();
        
        if (estado == Jogo.ESTADO.MENU) {

            if (mx >= BOARD_LARGURA / 4 + 110 && mx <= BOARD_LARGURA / 4 + 270) {

                //playButton
                if (my >= 250 && my <= 300) {

                    Jogo.estado = Jogo.ESTADO.JOGO;
                    System.out.println("jogocomeçou");
                }

                //pontButton
                if (my >= 350 && my <= 400) {

                    Jogo.estado = Jogo.ESTADO.QUAD;
                    System.out.println("Pontuação");
                }

                //helpButton
                if (my >= 450 && my <= 500) {
                    
                    Jogo.estado = Jogo.ESTADO.HELP;
                    System.out.println("HELP!");
                }

                //SairButton 
                if (my >= 550 && my <= 600) {

                    Jogo.estado = Jogo.ESTADO.SAIR;
                    System.out.println("Adeus!");
                }
            }

        }
        if (estado == ESTADO.QUAD || estado == ESTADO.HELP) {
            if (mx >= 630 && mx <= BOARD_LARGURA) {

                if (my >= 680 && my <= BOARD_ALTURA) {

                    System.out.println("voltar ao menu");
                    Jogo.estado = Jogo.ESTADO.MENU;
                }
            }
        }
        
        if (estado == Jogo.ESTADO.PAUSA) {
            
            if (mx >= BOARD_LARGURA / 4 + 110 && mx <= BOARD_LARGURA / 4 + 270) {
                
                 //ContButton
                if (my >= 350 && my <= 400) {

                    Jogo.estado = Jogo.ESTADO.JOGO;
                    System.out.println("jogo recomeçou");
                }
                
                //SairButton
                if (my >= 425 && my <= 475) {

                    Jogo.estado = Jogo.ESTADO.SAIR;
                    System.out.println("Adeus!");
                }
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent me) {}

    @Override
    public void mouseReleased(MouseEvent me) {}

    @Override
    public void mouseEntered(MouseEvent me) {}

    @Override
    public void mouseExited(MouseEvent me) {}
}
