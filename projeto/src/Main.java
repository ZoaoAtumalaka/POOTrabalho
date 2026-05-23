import javax.swing.*;

public class Main {

    public static void main(String[] args){
//        Introdução da tela inicial, terminei a classe de equipes
        JFrame tela = new JFrame("Dispatch");
        tela.setSize(600,300);
        tela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        tela.setVisible(true);
        JButton botao =new JButton("iniciar");

        JButton botao1 =new JButton("Sair");
        JButton botao2=new JButton("Informações");

        JPanel painel= new JPanel();
        painel.setBounds(600,300,100,100);
        painel.add(botao);
        painel.add(botao2);
        painel.add(botao1);
        tela.add(painel);
    }
}