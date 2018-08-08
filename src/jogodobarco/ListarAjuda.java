package jogodobarco;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ListarAjuda implements Variaveis{

    ListarPont lp = new ListarPont();
    
    public Rectangle voltaButton = new Rectangle(628, 680, 128, 68);
    
    public void render(Graphics g){
        
        Graphics2D g2d = (Graphics2D) g;
        
        g.setColor(Color.black);
        g.setFont(FONT40);
        g2d.draw(voltaButton);
        
        g.drawString("Ajuda", BOARD_LARGURA / 2 - 55, 120);
        
        g.setColor(AREIA_COLOR);
        g.fillRect(628, 680, 128, 68);
        
        g.setFont(FONT30);
        g.setColor(Color.black);
        g.drawString("Volta", voltaButton.x + 28, voltaButton.y + 45);
        
        g.setFont(FONT25);
        lp.drawString(g, AJUDA_TEXT,  BOARD_LARGURA / 6, BOARD_ALTURA / 4);
    }
}
