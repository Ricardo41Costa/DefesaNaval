package jogodobarco;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class Menu implements Variaveis{

    PontManager pm = new PontManager();

    public Rectangle jogoButton = new Rectangle(BOARD_LARGURA / 4 + 110, 250, 160, 50);
    public Rectangle pontButton = new Rectangle(BOARD_LARGURA / 4 + 110, 350, 160, 50);
    public Rectangle helpButton = new Rectangle(BOARD_LARGURA / 4 + 110, 450, 160, 50);
    public Rectangle sairButton = new Rectangle(BOARD_LARGURA / 4 + 110, 550, 160, 50);

    public void render(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g.setColor(AREIA_COLOR);
        g.fillRect(BOARD_LARGURA / 4 + 110, 250, 160, 50);
        g.fillRect(BOARD_LARGURA / 4 + 110, 350, 160, 50);
        g.fillRect(BOARD_LARGURA / 4 + 110, 450, 160, 50);
        g.fillRect(BOARD_LARGURA / 4 + 110, 550, 160, 50);

        g.setColor(Color.BLACK);

        g2d.draw(jogoButton);
        g2d.draw(pontButton);
        g2d.draw(helpButton);
        g2d.draw(sairButton);

        g.setFont(FONT30); 
        g.drawString("Jogar", jogoButton.x + 38, jogoButton.y + 35);
        g.drawString("Pontuação", pontButton.x + 4, pontButton.y + 35); 
        g.drawString("Ajuda", helpButton.x + 38, helpButton.y + 35);
        g.drawString("Sair", sairButton.x + 50, sairButton.y + 35);
        g.drawString("Criado por: Ricardo Costa", 15, BOARD_ALTURA - 10);
        
        g.setFont(FONT50);
        g.drawString("Defesa Naval", BOARD_LARGURA / 5 + 60, 140);

    }
}
