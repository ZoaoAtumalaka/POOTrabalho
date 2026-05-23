import javax.swing.*;
import java.awt.Dimension;
import java.awt.event.ActionListener;

public class Janela {
    JFrame janela = new JFrame("Gerenciador de The Boys");
    janela.setSize(1920, 1080);
    janela.setVisible(true);

    JLabel label = new JLabel("Capitão Pátria");
    JPanel painel = new JPanel(null);
    JButton botao1 = new JButton("Fechar");

    painel.add(label);
    janela.add(painel);

    int larguraBotao = 80;
    int alturaBotao = 20;
    int x = (janela.getWidth() - larguraBotao) / 2;
    int y = (janela.getHeight() - alturaBotao) / 2;

    botao1.setBounds(
            x, y, larguraBotao, alturaBotao
    );

    janela.add(botao1);

    botao1.AddActionListener(new ActionListener() {
       @Override
               public void actionPerformed(ActionEvent e){
            System.out.println("Botão Pressionado!");
        }
    });

    public void fecharAba(){
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
