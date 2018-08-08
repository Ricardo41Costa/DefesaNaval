package jogodobarco;

import java.awt.Color;
import java.awt.Font;

public interface Variaveis {
    
    public static final int BOARD_LARGURA = 758;
    public static final int BOARD_ALTURA = 750;
    public static final int COSTA = 650;
    public static final int BOMBA_ALTURA = 10;
    public static final int BARCO_ALTURA = 51;
    public static final int BARCO_LARGURA = 25;
    public static final int CAIXA_ALTURA = 20;
    public static final int CAIXA_LARGURA = 20;
    public static final int BOSS_ALTURA = 180;
    public static final int BOSS_LARGURA = 80;
    public static final int LIMITE_DIREITA = 30;
    public static final int LIMITE_ESQUERDA = 5;
    public static final int PARA_BAIXO = 25;
    public static final int NUMERO_DE_BARCOS_PARA_DESTROIR_NL1 = 24;
    public static final int NUMERO_DE_BARCOS_PARA_DESTROIR_NL2 = 32;
    public static final int CHANCE = 1;
    public static final int CHANCE_CAIXA = 20;
    public static final int ATRASO = 17;
    public static final int JOGADOR_LARGURA = 46;
    public static final int JOGADOR_ALTURA = 25;
    public static final int NOME_ALTURA = 250;
    public static final int NOME_LARGURA = 500;
    public static final int BARCO_INICIA_X = 150;
    public static final int BARCO_INICIA_Y = 5;
    public static final int BOSS_INICIA_Y = 28;
    public static final int BOSS_INICIA_X = 321;
    public static final String AJUDA_TEXT = "Objetivo do jogo é defender a costa de uma\ninvasão de navios piratas.\nUsa as setas ou 'A' e 'D' para movimentar\no barco e 'ESPAÇO' para disparar contra\nos piratas.\nBoa Sorte!";
    public static final String MENU_TEXT = "Clique em espaço para voltar ao menu";
    public static final String EXIT_TEXT = "Clique em 'ESC' para sair do jogo";
    public static final String CONT_TEXT = "Clique em espaço para começar a proxima invasão";
    public static final Color AREIA_COLOR = new Color(255, 255, 106);
    public static final Color MAR_COLOR = new Color(11, 209, 239);
    public static final Color VIDA_COLOR = new Color(9, 229, 23);
    public static final Font FONT20 = new Font("Helvetica", Font.BOLD, 20);
    public static final Font FONT25 = new Font("Helvetica", Font.BOLD, 25);
    public static final Font FONT30 = new Font("Helvetica", Font.BOLD, 30);
    public static final Font FONT40 = new Font("Helvetica", Font.BOLD, 40);
    public static final Font FONT50 = new Font("Helvetica", Font.BOLD, 50);
    
}
