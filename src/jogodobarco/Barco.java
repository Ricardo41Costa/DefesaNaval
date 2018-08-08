package jogodobarco;

import javax.swing.ImageIcon;

/**
 * Esta classe é responsavel pela inicialização dos barcos inimigos, as suas
 * propriedades e das suas bombas.
 *
 * @author Ricardo Costa
 */
public class Barco extends Sprite {

    private Bomba bomba;

    /**
     * Este método é para inicializar o barco recolhendo as variáveis x e y para
     * definir a posição do barco.
     *
     * @param x Variável do tipo inteiro com função de definir a posição do
     * barco no eixo da abcissa.
     * @param y Variável do tipo inteiro com função de definir a posição do
     * barco no eixo da ordenada.
     */
    public Barco(int x, int y) {

        iniciar_Barco(x, y);
    }

    private void iniciar_Barco(int x, int y) {

        this.x = x;
        this.y = y;

        bomba = new Bomba(x, y);
        ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource("imagens/barcoinimigo.png"));
        setImage(ii.getImage());
    }

    /**
     * Este método define a direção que os barcos navegam, que recolhe a
     * variável direcao.
     *
     * @param direcao Variável do tipo inteiro com função de definir a direção
     * em que o barco navega.
     */
    public void agir(int direcao) {

        this.x += direcao;
    }

    public Bomba getBomba() {

        return bomba;
    }

    /**
     * Está Subclasse, que pertence a classe Barco, é responsável pela
     * inicialização das bombas inimigas e as suas propriedades.
     */
    public class Bomba extends Sprite {

        private boolean destroido;

        /**
         * Este método é para inicializar a classe Bomba recolhendo as variáveis
         * x e y para definir a posição da bomba.
         *
         * @param x Variável do tipo inteiro com função de definir a posição da
         * bomba no eixo da abcissa.
         * @param y Variável do tipo inteiro com função de definir a posição da
         * bomba no eixo da ordenada.
         */
        public Bomba(int x, int y) {

            iniciar_Bomba(x, y);
        }

        private void iniciar_Bomba(int x, int y) {

            setDestroyed(true);
            this.x = x;
            this.y = y;
            ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource("imagens/bala.png"));
            setImage(ii.getImage());

        }

        /**
         * Este método define a bomba foi destroída recolhendo a variável
         * destroido.
         *
         * @param destroido Variável do tipo booleano com função de destroir a
         * bomba.
         */
        public void setDestroyed(boolean destroido) {

            this.destroido = destroido;
        }

        /**
         * Este método verifica se a bomba foi destroída devolvendo a variável
         * bomba.
         *
         * @return
         */
        public boolean isDestroyed() {

            return destroido;
        }
    }
}
