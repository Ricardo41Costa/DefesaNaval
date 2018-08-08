package jogodobarco;

import java.util.*;
import java.io.*;
import javax.swing.JOptionPane;

public class PontManager implements Variaveis {

    private ArrayList<Pontuacao> pontos;

    private static final String PONTUACAO_TXT = "pontuacao.txt";

    ObjectOutputStream outputStream = null;
    ObjectInputStream inputStream = null;

    public PontManager() {

        pontos = new ArrayList<Pontuacao>();
    }

    public ArrayList<Pontuacao> getPontos() {

        loadPontuacaoFile();
        ordenar();

        return pontos;
    }

    private void ordenar() {

        PontComparar comparar = new PontComparar();
        Collections.sort(pontos, comparar);
    }

    public void addPontuacao(String nome, int ponto) {

        loadPontuacaoFile();
        pontos.add(new Pontuacao(ponto, nome));
        updatePontuacaoFile();
    }

    public void loadPontuacaoFile() {

        try {

            inputStream = new ObjectInputStream(new FileInputStream(PONTUACAO_TXT));
            pontos = (ArrayList<Pontuacao>) inputStream.readObject();

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "[LOAD]Erro encontrado", JOptionPane.ERROR_MESSAGE);

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "[LOAD]Erro encontrado", JOptionPane.ERROR_MESSAGE);

        } catch (ClassNotFoundException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "[LOAD]Erro encontrado", JOptionPane.ERROR_MESSAGE);

        } finally {

            try {

                if (outputStream != null) {

                    outputStream.flush();
                    outputStream.close();
                }

            } catch (IOException e) {

                JOptionPane.showMessageDialog(null, e.getMessage(), "[LOAD]Erro encontrado", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    private void updatePontuacaoFile() {

        try {

            outputStream = new ObjectOutputStream(new FileOutputStream(PONTUACAO_TXT));
            outputStream.writeObject(pontos);

        } catch (FileNotFoundException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "[UPDATE]Erro encontrado", JOptionPane.ERROR_MESSAGE);

        } catch (IOException e) {

            JOptionPane.showMessageDialog(null, e.getMessage(), "[UPDATE]Erro encontrado", JOptionPane.ERROR_MESSAGE);

        } finally {

            try {

                if (outputStream != null) {

                    outputStream.flush();
                    outputStream.close();
                }

            } catch (IOException e) {

                JOptionPane.showMessageDialog(null, e.getMessage(), "[UPDATE]Erro encontrado", JOptionPane.ERROR_MESSAGE);

            }
        }
    }

    public String getPontuacaoMaximaString() {

        String pontoMaximo = "";
        int max = 10;

        ArrayList<Pontuacao> pontos;
        pontos = getPontos();

        int i = 0;
        int x = pontos.size();
        if (x > max) {

            x = max;

        }

        while (i < x) {

            pontoMaximo += (i + 1) + ".\t" + pontos.get(i).getNome() + " - " + pontos.get(i).getPontuacao() + "\n\n";
            i++;

        }
        return pontoMaximo;
    }
}
