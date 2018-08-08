package jogodobarco;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Toolkit;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import javax.swing.JPanel;

public class Jogo extends JPanel implements Runnable, Variaveis {

    private Dimension d;
    private ArrayList<Barco> barcos;
    private Jogador jogador;
    private Tiro tiro;
    private Boss boss;

    private int direction = -1;
    private int direction_boss = -2;
    private int mortes = 0;
    private int boss_vida = 180;
    private int jogador_vida = 150;

    private boolean ingame = true;
    private boolean fim = false;
    private boolean nl1 = true;
    private boolean nl2 = false;
    private boolean nl3 = false;

    private String message = "Game Over ";
    private String pontuacao = "Pontuação: ";

    private Menu menu;
    private ListarPont lp;
    private NomeJogador nj;
    private MouseInput mi;
    private ListarAjuda la;
    private menuPausa mp;

    public static int PONTOS;

    public enum ESTADO {

        MENU, JOGO, QUAD, HELP, SAIR, PAUSA;
    }

    public static ESTADO estado;

    private Thread animator;

    public Jogo() {

        iniciar_Tela();
    }

    private void iniciar_Tela() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        d = new Dimension(BOARD_LARGURA, BOARD_ALTURA);
        setBackground(MAR_COLOR);
        menu = new Menu();
        nj = new NomeJogador();
        lp = new ListarPont();
        mi = new MouseInput();
        la = new ListarAjuda();
        mp = new menuPausa();
        PONTOS = 0;
        estado = ESTADO.MENU;

        iniciar_Jogo();
        setDoubleBuffered(true);
    }

    public void iniciar_Jogo() {

        iniciar_List_pirata();
        this.addMouseListener(mi);
        jogador = new Jogador();
        tiro = new Tiro();
        boss = new Boss(BOSS_INICIA_X, BOSS_INICIA_Y);

        if (animator == null || !ingame) {

            animator = new Thread(this);
            animator.start();
        }
    }

    public void iniciar_List_pirata() {

        barcos = new ArrayList<>();
        int n_hori = 6;

        if (nl1 || nl2) {
            if (nl1) {

                n_hori = 6;
            } else if (nl2) {

                n_hori = 8;
            }
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < n_hori; j++) {

                    Barco barco = new Barco(BARCO_INICIA_X + 60 * j, BARCO_INICIA_Y + 50 * i);
                    barcos.add(barco);
                }
            }
        }
    }

    public void drawBarcos(Graphics g) {

        if (nl1 || nl2) {

            for (Barco barco : barcos) {

                if (barco.isVisible()) {

                    g.drawImage(barco.getImage(), barco.getX(), barco.getY(), this);
                }

                if (barco.isDying()) {

                    barco.die();
                }
            }
        } else {

            if (boss.isVisible()) {

                g.drawImage(boss.getImage(), boss.getX(), boss.getY(), this);
            }

            if (boss.isDying()) {

                ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource("imagens/Explosion_boss.png"));
                boss.setImage(ii.getImage());
                boss.die();
            }
        }
    }

    public void drawJogador(Graphics g) {

        if (jogador.isVisible()) {

            g.drawImage(jogador.getImage(), jogador.getX(), jogador.getY(), this);
        }

        if (jogador.isDying()) {

            jogador.die();
            ingame = false;
            fim = true;
            message = "Game Over ";
            System.out.println("morreste");
        }
    }

    public void drawTiro(Graphics g) {

        if (tiro.isVisible()) {

            g.drawImage(tiro.getImage(), tiro.getX(), tiro.getY(), this);
        }
    }

    public void drawBomba(Graphics g) {

        if (nl1 || nl2) {

            for (Barco a : barcos) {

                Barco.Bomba bomba = a.getBomba();

                if (!bomba.isDestroyed()) {

                    g.drawImage(bomba.getImage(), bomba.getX(), bomba.getY(), this);
                }
            }
        } else {

            Boss.Bombas bomba1 = boss.getBombas1();
            Boss.Bombas bomba2 = boss.getBombas2();
            Boss.Bombas bomba3 = boss.getBombas3();
            if (!bomba1.isDestroyed()) {

                g.drawImage(bomba1.getImage(), bomba1.getX(), bomba1.getY(), this);
            }
            
            if (!bomba2.isDestroyed()) {

                g.drawImage(bomba2.getImage(), bomba2.getX(), bomba2.getY(), this);
            }
            
            if (!bomba3.isDestroyed()) {

                g.drawImage(bomba3.getImage(), bomba3.getX(), bomba3.getY(), this);
            }
        }
    }

    public void drawHud(Graphics g) {

        g.setFont(FONT20);
        g.setColor(Color.BLACK);
        g.drawString(pontuacao + PONTOS, BOARD_LARGURA - 190, BOARD_ALTURA - 60);
        g.drawString("Jogador:", 10, BOARD_ALTURA - 60);

        if (nl1) {

            g.drawString("Nível: 1", BOARD_LARGURA - 190, BOARD_ALTURA - 30);
        } else if (nl2) {

            g.drawString("Nível: 2", BOARD_LARGURA - 190, BOARD_ALTURA - 30);
        } else if (nl3) {
            
            g.drawString("Boss:", 10, BOARD_ALTURA - 30);
            g.drawString("Nível: 3 Boss", BOARD_LARGURA - 190, BOARD_ALTURA - 30);
            g.setColor(Color.RED);
            g.fillRect(70, BOARD_ALTURA - 45, 180, 20);
            g.setColor(VIDA_COLOR);
            g.fillRect(70, BOARD_ALTURA - 45, boss_vida, 20);
        }

        g.setColor(Color.RED);
        g.fillRect(100, BOARD_ALTURA - 75, 150, 20);
        g.setColor(VIDA_COLOR);
        g.fillRect(100, BOARD_ALTURA - 75, jogador_vida, 20);
    }
    
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (ingame) {
            switch (estado) {
                case JOGO:
                    removeMouseListener(mi);
                    g.setColor(AREIA_COLOR);
                    g.fillRect(0, COSTA, BOARD_LARGURA, COSTA);
                    drawBarcos(g);
                    drawJogador(g);
                    drawTiro(g);
                    drawBomba(g);
                    drawHud(g);
                    break;
                case MENU:
                    menu.render(g);
                    break;
                case QUAD:
                    lp.render(g);
                    break;
                case HELP:
                    la.render(g);
                    break;
                case SAIR:
                    System.exit(0);
                    break;
                case PAUSA:
                    addMouseListener(mi);
                    mp.render(g);
                    break;
                default:
                    break;
            }
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
    }

    public void gameOver() {

        System.out.println("acabou o jogo");

        Graphics g = this.getGraphics();

        g.setColor(MAR_COLOR);
        g.fillRect(0, 0, BOARD_LARGURA, BOARD_ALTURA);

        g.setColor(AREIA_COLOR);
        g.fillRect(50, BOARD_LARGURA / 2 - 30, BOARD_LARGURA - 100, 50);
        g.setColor(Color.black);
        g.drawRect(50, BOARD_LARGURA / 2 - 30, BOARD_LARGURA - 100, 50);

        FontMetrics metr = this.getFontMetrics(FONT20);

        g.setColor(Color.black);
        g.setFont(FONT20);
        if (fim) {
            g.drawString(message + pontuacao + PONTOS, (BOARD_LARGURA - metr.stringWidth(message + pontuacao + PONTOS)) / 2,
                    BOARD_LARGURA / 2);

            g.drawString(MENU_TEXT, BOARD_LARGURA / 3 - 50, BOARD_LARGURA / 2 + 50);
            g.drawString(EXIT_TEXT, BOARD_LARGURA / 3 - 25, BOARD_LARGURA / 2 + 80);

        } else {

            g.drawString(message, (BOARD_LARGURA - metr.stringWidth(message)) / 2,
                    BOARD_LARGURA / 2);

            g.drawString(CONT_TEXT, BOARD_LARGURA / 3 - 120, BOARD_LARGURA / 2 + 50);
        }

        g.dispose();
    }

    public void animationCycle() {

        if (nl1) {
            if (mortes == NUMERO_DE_BARCOS_PARA_DESTROIR_NL1) {

                ingame = false;
                nl1 = false;
                nl2 = true;
                message = "Parabéns sobreviveste! Prepara - te para segunda invasão!";
            }
            
        } else if (nl2) {
            if (mortes == NUMERO_DE_BARCOS_PARA_DESTROIR_NL2) {

                ingame = false;
                nl2 = false;
                nl3 = true;
                message = "Parabéns sobreviveste! Prepara - te para Boss";
            }

        } else if (nl3) {
            if (boss_vida == 0) {
                
                PONTOS += 1000;
                ingame = false;
                fim = true;
                message = "GANHASTE! ";
            }
        }

        if (jogador_vida == 0) {

            ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource("imagens/Explosion.png"));
            jogador.setImage(ii.getImage());
            jogador.setDying(true);
        }

        if (PONTOS < 0) {

            PONTOS = 0;
        }

        // jogador
        jogador.agir();

        // tiro
        if (tiro.isVisible()) {

            int tiroX = tiro.getX();
            int tiroY = tiro.getY();

            for (Barco barco : barcos) {

                int barcoX = barco.getX();
                int barcoY = barco.getY();

                if (barco.isVisible() && tiro.isVisible()) {
                    if (tiroX >= (barcoX)
                            && tiroX <= (barcoX + BARCO_LARGURA)
                            && tiroY >= (barcoY)
                            && tiroY <= (barcoY + BARCO_ALTURA)) {
                        ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource("imagens/Explosion.png"));
                        barco.setImage(ii.getImage());
                        barco.setDying(true);
                        mortes++;
                        tiro.die();
                        PONTOS += 100;
                    }
                }
            }

            if (nl3) {

                int bossX = boss.getX();
                int bossY = boss.getY();

                if (boss.isVisible() && tiro.isVisible()) {
                    if (tiroX >= (bossX)
                            && tiroX <= (bossX + BOSS_LARGURA)
                            && tiroY >= (bossY)
                            && tiroY <= (bossY + BOSS_ALTURA)) {
                        ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource("imagens/Explosion.png"));
                        tiro.setImage(ii.getImage());
                        boss_vida -= 6;
                        tiro.die();
                        PONTOS += 10;
                    }
                }
            }

            int y = tiro.getY();
            y -= 8;

            if (y < 0) {
                tiro.die();
                PONTOS -= 50;
            } else {
                tiro.setY(y);
            }
        }
       
        // barco
        for (Barco barco : barcos) {

            int x = barco.getX();

            if (x >= BOARD_LARGURA - LIMITE_DIREITA && direction != -1) {

                direction = -1;
                Iterator i1 = barcos.iterator();

                while (i1.hasNext()) {

                    Barco a2 = (Barco) i1.next();
                    a2.setY(a2.getY() + PARA_BAIXO);
                }
            }

            if (x <= LIMITE_ESQUERDA && direction != 1) {

                direction = 1;

                Iterator i2 = barcos.iterator();

                while (i2.hasNext()) {

                    Barco a = (Barco) i2.next();
                    a.setY(a.getY() + PARA_BAIXO);
                }
            }
        }

        Iterator it = barcos.iterator();

        while (it.hasNext()) {

            Barco barco = (Barco) it.next();

            if (barco.isVisible()) {

                int y = barco.getY();

                if (y > COSTA - BARCO_ALTURA) {
                    ingame = false;
                    fim = true;
                    message = "Invasão! ";
                }
                barco.agir(direction);
            }
        }

        // barco_bomba
        Random generator = new Random();

        for (Barco barco : barcos) {

            int shot = generator.nextInt(45);
            Barco.Bomba b = barco.getBomba();

            if (shot == CHANCE && barco.isVisible() && b.isDestroyed()) {

                b.setDestroyed(false);
                b.setX(barco.getX());
                b.setY(barco.getY());
            }

            int bombX = b.getX();
            int bombY = b.getY();
            int playerX = jogador.getX();
            int playerY = jogador.getY();

            if (jogador.isVisible() && !b.isDestroyed()) {

                if (bombX >= (playerX)
                        && bombX <= (playerX + JOGADOR_LARGURA)
                        && bombY >= (playerY)
                        && bombY <= (playerY + JOGADOR_ALTURA)) {
                    jogador_vida -= 30;
                    PONTOS -= 50;
                    b.setDestroyed(true);
                }
            }

            if (!b.isDestroyed()) {

                b.setY(b.getY() + 5);

                if (b.getY() >= COSTA - BOMBA_ALTURA) {
                    b.setDestroyed(true);
                }
            }
        }

        if (nl3) {

            //Boss
            int x = boss.getX();

            if (x >= 700 - LIMITE_DIREITA && direction_boss != -2) {

                direction_boss = -2;
                boss.setY(boss.getY() + PARA_BAIXO);
            }

            if (x <= LIMITE_ESQUERDA && direction_boss != 2) {

                direction_boss = 2;
                boss.setY(boss.getY() + PARA_BAIXO);
            }

            if (boss.isVisible()) {

                int y = boss.getY();

                if (y > COSTA - BOSS_ALTURA) {
                    ingame = false;
                    fim = true;
                    message = "Invasão! ";
                }
                boss.agir(direction_boss);
            }

            //boss_bombas
            Random generator_boss = new Random();

            int bomba = generator_boss.nextInt(5);
            Boss.Bombas b1 = boss.getBombas1();
            Boss.Bombas b2 = boss.getBombas2();
            Boss.Bombas b3 = boss.getBombas3();

            if (bomba == CHANCE && boss.isVisible() && b1.isDestroyed()) {

                b1.setDestroyed(false);
                b1.setX(boss.getX() + 45);
                b1.setY(boss.getY() + 190);
                b2.setDestroyed(false);
                b2.setX(boss.getX() + 45);
                b2.setY(boss.getY() + 190);
                b3.setDestroyed(false);
                b3.setX(boss.getX() + 45);
                b3.setY(boss.getY() + 190);
            }

            int bomba1X = b1.getX();
            int bomba1Y = b1.getY();
            int bomba2X = b2.getX();
            int bomba2Y = b2.getY();
            int bomba3X = b3.getX();
            int bomba3Y = b3.getY();
            int jogadorX = jogador.getX();
            int jogadorY = jogador.getY();

            if (jogador.isVisible() && !b1.isDestroyed()) {

                if (bomba1X >= (jogadorX)
                        && bomba1X <= (jogadorX + JOGADOR_LARGURA)
                        && bomba1Y >= (jogadorY)
                        && bomba1Y <= (jogadorY + JOGADOR_ALTURA)) {
                    jogador_vida -= 30;
                    PONTOS -= 50;
                    b1.setDestroyed(true);
                }
                
                if (bomba2X >= (jogadorX)
                        && bomba2X <= (jogadorX + JOGADOR_LARGURA)
                        && bomba2Y >= (jogadorY)
                        && bomba2Y <= (jogadorY + JOGADOR_ALTURA)) {
                    jogador_vida -= 30;
                    PONTOS -= 50;
                    b1.setDestroyed(true);
                }
                
                if (bomba3X >= (jogadorX)
                        && bomba3X <= (jogadorX + JOGADOR_LARGURA)
                        && bomba3Y >= (jogadorY)
                        && bomba3Y <= (jogadorY + JOGADOR_ALTURA)) {
                    jogador_vida -= 30;
                    PONTOS -= 50;
                    b1.setDestroyed(true);
                }
            }

            if (!b1.isDestroyed() || !b2.isDestroyed() || !b3.isDestroyed()) {

                b1.setY(b1.getY() + 7);
                
                b2.setY(b2.getY() + 7);
                b2.setX(b2.getX() + 2);
                
                b3.setY(b3.getY() + 7);
                b3.setX(b3.getX() - 2);

                if (b1.getY() >= COSTA - BOMBA_ALTURA || b2.getY() >= COSTA - BOMBA_ALTURA || b3.getY() >= COSTA - BOMBA_ALTURA) {

                    b1.setDestroyed(true);
                    b2.setDestroyed(true);
                    b3.setDestroyed(true);
                }
            }
        }
    }

    @Override
    public void run() {

        long beforeTime, timeDiff, sleep;

        beforeTime = System.currentTimeMillis();

        ingame = true;

        while (ingame) {

            repaint();
            if (estado == ESTADO.JOGO) {

                animationCycle();
                removeMouseListener(mi);
            }

            timeDiff = System.currentTimeMillis() - beforeTime;
            sleep = ATRASO - timeDiff;

            if (sleep < 0) {
                sleep = 2;
            }

            try {
                Thread.sleep(sleep);
            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(null, e, "[THREAD]Erro encontrado", JOptionPane.ERROR_MESSAGE);
            }
            beforeTime = System.currentTimeMillis();
        }
        if (fim) {

            addNome();
        } else {

            gameOver();
        }

    }

    public void resetJogo() {

        direction = -1;
        this.addMouseListener(mi);
        barcos.clear();
        estado = ESTADO.MENU;
        PONTOS = 0;
        mortes = 0;
        jogador_vida = 150;
        boss_vida = 1500;
        fim = false;
        nl3 = false;
        nl2 = false;
        nl1 = true;

        iniciar_Jogo();
    }

    public void addNome() {

        nj.setTitle("Adicionar ao Quadro de Pontuação");
        nj.setSize(NOME_LARGURA, NOME_ALTURA);
        nj.setLocationRelativeTo(null);
        nj.setResizable(false);
        nj.setVisible(true);

        gameOver();
    }

    public void mudarNivel() {

        System.out.println("proximo nivel");
        direction = -1;
        mortes = 0;
        jogador_vida = 150;
        barcos.clear();
        iniciar_Jogo();
    }

    private class TAdapter extends KeyAdapter {

        @Override
        public void keyReleased(KeyEvent e) {

            jogador.keyReleased(e);
        }

        @Override
        public void keyPressed(KeyEvent e) {

            if (estado == ESTADO.JOGO) {
                jogador.keyPressed(e);

                int x = jogador.getX();
                int y = jogador.getY();

                int key = e.getKeyCode();

                if (key == KeyEvent.VK_SPACE) {

                    if (ingame) {
                        if (!tiro.isVisible()) {
                            tiro = new Tiro(x, y);
                        }
                    } else {
                        if (fim) {

                            resetJogo();
                        } else {

                            mudarNivel();
                        }
                    }
                }
                
                if (key == KeyEvent.VK_1){
                
                    if (ingame) {
                        if(nl2 == true || nl3 == true){
                    
                        ingame = false;
                        nl2 = false;
                        nl3 = false;
                        nl1 = true;
                         message = "Saltaste para o nivel 1";
                        }
                    }
                }
                
                if (key == KeyEvent.VK_2){
                
                    if (ingame) {
                        if(nl1 == true || nl3 == true){
                    
                        ingame = false;
                        nl2 = true;
                        nl3 = false;
                        nl1 = false;
                        message = "Saltaste para o nivel 2";
                        }
                    }
                }
                
                if (key == KeyEvent.VK_3){
                
                    if (ingame) {
                        if(nl1 == true || nl2 == true){
                    
                        ingame = false;
                        nl2 = false;
                        nl3 = true;
                        nl1 = false;
                        message = "Saltaste para o nivel 3";
                        }
                    }
                }
                
                if (key == KeyEvent.VK_PLUS){
                
                    if (ingame) {
                        
                        PONTOS += 1000;
                    }
                }

                if (key == KeyEvent.VK_ESCAPE) {

                    if (ingame) {
                        estado = ESTADO.PAUSA;
                    } else {
                        System.exit(0);
                    }
                }
            }
        }
    }
}