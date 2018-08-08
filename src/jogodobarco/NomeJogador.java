package jogodobarco;

import java.awt.Font;
import java.awt.event.*;
import javax.swing.*;
import static jogodobarco.Jogo.PONTOS;

public class NomeJogador extends JFrame implements Variaveis {

    private String nome;
    PontManager pm = new PontManager();

    Font nomefont = new Font("Helvetica", Font.PLAIN, 20);

    public NomeJogador() {
    
        setLayout(null);

        JTextField nomeText = new JTextField(15);
        JButton Cont_btn = new JButton("Continuar");
        JButton Clos_btn = new JButton("Fechar");
        JLabel nomeLabel = new JLabel("Introduza o seu nome: ");

        Cont_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                if(nomeText.getText().equals("")){
                    
                    JOptionPane.showMessageDialog(null, "Para adicionar a sua pontuação é necessário um nome.", "[ADD]Erro encontrado", JOptionPane.ERROR_MESSAGE);                    
                } else {
                nome = nomeText.getText();

                pm.addPontuacao(nome, PONTOS);

                nomeText.setText("");
                setVisible(false);
                }
            }
        });
        
        Clos_btn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {

                nomeText.setText("");
                setVisible(false);
            }
        });

        nomeText.setFont(nomefont);
        nomeLabel.setBounds(NOME_LARGURA / 3 - 10, NOME_ALTURA / 2 - 80, 200, 35);
        nomeText.setBounds(NOME_LARGURA / 3 - 10, NOME_ALTURA / 2 - 50, 200, 35);
        Cont_btn.setBounds(NOME_LARGURA / 2 - 95, NOME_ALTURA / 2 + 30, 100, 40);
        Clos_btn.setBounds(NOME_LARGURA / 2 + 5, NOME_ALTURA / 2 + 30, 100, 40);
        add(nomeText);
        add(Cont_btn);
        add(Clos_btn);
        add(nomeLabel);
    }
}
