package jogodobarco;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class ListarPont implements Variaveis{
    
    PontManager pont = new PontManager();
    
    public Rectangle voltaButton = new Rectangle(628, 680, 128, 68);
    
    public void render(Graphics g){
        Graphics2D g2d = (Graphics2D) g;
        
        g.setColor(Color.black);
        g.setFont(FONT20);
        drawString( g, pont.getPontuacaoMaximaString(),BOARD_LARGURA / 4 + 75, 180);
        
        g.setFont(FONT40);
        g.drawString("Quadro de Pontuacão Máxima", BOARD_LARGURA / 7 - 10, 120);
        
        g2d.draw(voltaButton);
        
        g.setColor(AREIA_COLOR);
        g.fillRect(628, 680, 128, 68);
        
        g.setFont(FONT30);
        g.setColor(Color.black); 
        g.drawString("Volta", voltaButton.x + 28, voltaButton.y + 45);
        
    }
    
        public void drawString(Graphics g, String text, int x, int y) {
        for (String line : text.split("\n"))
            g.drawString(line, x, y += g.getFontMetrics().getHeight());
    }
    
}
