package jogodobarco;

import javax.swing.ImageIcon;

/**
 *Esta classe é responsavel pela inicialização do Boss final, as suas propriedades e das suas bombas.
 * 
 * @author Ricardo Costa
 */
public class Boss extends Sprite {
    
    private Bombas bombas1;
    private Bombas bombas2;
    private Bombas bombas3;

    /**
     *Este método é para inicializar o boss recolhendo as variáveis x e y para definir a posição do barco.
     * @param x Variável do tipo inteiro com função de definir a posição do boss no eixo da abcissa.
     * @param y Variável do tipo inteiro com função de definir a posição do boss no eixo da ordenada.
     */
    public Boss(int x, int y) {

        iniciar_Boss(x, y);
    }
    
    private void iniciar_Boss(int x, int y) {

        this.x = x;
        this.y = y;

        bombas1 = new Bombas(x, y);
        bombas2 = new Bombas(x, y);
        bombas3 = new Bombas(x, y);
        ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource("imagens/boss.png"));
        setImage(ii.getImage());
    }
    
    /**
      *Este método define a direção que o boss navega, que recolhe a variável direcao.
     * @param direcao Variável do tipo inteiro com função de definir a direção em que o boss navega.
     */
    public void agir(int direcao) {
        
        this.x += direcao;
    }
    
    public Bombas getBombas1() {
        
        return bombas1;
    }
    
    public Bombas getBombas2() {
        
        return bombas2;
    }
    
    public Bombas getBombas3() {
        
        return bombas3;
    }
    
   /**
     *Está Subclasse, que pertence a classe Boss, é responsável pela inicialização das bombas e as suas propriedades.
     */
    public class Bombas extends Sprite {

        private boolean destroido;

       /**
         *Este método é para inicializar a classe Bomba recolhendo as variáveis x e y para definir a posição da bomba.
         *@param x Variável do tipo inteiro com função de definir a posição da bomba no eixo da abcissa.
         *@param y Variável do tipo inteiro com função de definir a posição da bomba no eixo da ordenada.
         */
        public Bombas(int x, int y) {

            iniciar_Bombas(x, y);
        }
        
        private void iniciar_Bombas(int x, int y) {

            setDestroyed(true);
            this.x = x;
            this.y = y;
            ImageIcon ii = new ImageIcon(getClass().getClassLoader().getResource("imagens/bala.png"));
            setImage(ii.getImage());

        }
        
         /**
         *Este método define a bomba foi destroída recolhendo a variável destroido.
         * @param destroido Variável do tipo booleano com função de destroir a bomba.
         */
        public void setDestroyed(boolean destroido) {
        
            this.destroido = destroido;
        }

          /**
         *Este método verifica se a bomba foi destroída devolvendo a o estado da bomba.
         * @return 
         */
        public boolean isDestroyed() {
        
            return destroido;
        }     
    }
}
