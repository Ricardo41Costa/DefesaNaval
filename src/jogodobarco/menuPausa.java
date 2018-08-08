package jogodobarco;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import static jogodobarco.Jogo.PONTOS;

public class menuPausa implements Variaveis{
    
    public Rectangle ContButton = new Rectangle(BOARD_LARGURA / 4 + 110, 350, 160, 50);
    public Rectangle sairButton = new Rectangle(BOARD_LARGURA / 4 + 110, 425, 160, 50);
    
     public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        
        g.setColor(AREIA_COLOR);
        
        g.fillRect(BOARD_LARGURA / 4 + 110, 350, 160, 50);
        g.fillRect(BOARD_LARGURA / 4 + 110, 425, 160, 50);
        
        g.setColor(Color.BLACK);
        
        g2d.draw(ContButton);
        g2d.draw(sairButton);
     
        g.setFont(FONT20);
        g.drawString("Pontuação: " + PONTOS, BOARD_LARGURA / 3 + 48, BOARD_LARGURA / 2 - 46);
     
        
        g.setFont(FONT30); 
        g.drawString("Continuar", ContButton.x + 12, ContButton.y + 35);
        g.drawString("Sair", sairButton.x + 50, sairButton.y + 35);

        g.setFont(FONT50);
        g.drawString("Pausa", BOARD_LARGURA / 3 + 50, BOARD_LARGURA / 2 - 80);
     }
    
}
