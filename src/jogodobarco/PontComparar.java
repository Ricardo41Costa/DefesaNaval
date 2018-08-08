package jogodobarco;

import java.util.Comparator;

public class PontComparar implements Comparator<Pontuacao>{

    @Override
    public int compare(Pontuacao ponto1, Pontuacao ponto2) {
    
        int pont1 = ponto1.getPontuacao();
        int pont2 = ponto2.getPontuacao();
        
        if(pont1 > pont2){
            return -1;
        } else if (pont1 < pont2){
            return 1;
        } else {
            return 0;
        }
    }
}
