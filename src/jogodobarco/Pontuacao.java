package jogodobarco;

import java.io.Serializable;

public class Pontuacao implements Serializable{
    
     private int pontuacao;
    private String nome;

    public int getPontuacao() {
        
        return pontuacao;
    }

    public String getNome() {
        
        return nome;
    }

    public Pontuacao(int ponto, String nome) {
        
        this.pontuacao = ponto;
        this.nome = nome;
    }

}
    


    

