import javax.swing.*;

public class Main {
    public static void main(String[] args){
    //SWING
        JFrame tela = new JFrame("Dispatch");
        tela.setSize(1280, 720);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel painel= new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JButton botao =new JButton("iniciar");
        JButton botao1 =new JButton("Sair");
        JButton botao2=new JButton("Informações");

        painel.setBounds(600,300,100,100);
        painel.add(botao);
        painel.add(botao2);
        painel.add(botao1);
        tela.add(painel);

        tela.setLocationRelativeTo(null);
        tela.setVisible(true);
    }

}