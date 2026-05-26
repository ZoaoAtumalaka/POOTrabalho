import javax.swing.*;
import java.awt.*;

public class Main {
    private Equipes equipe;
    private CapitaoPatria capitaoPatria = new CapitaoPatria();

    public static void main(String[] args){
        Main app = new Main();
        app.menuPrincipal();
    }

    public void menuPrincipal() {
        JFrame telaMenu = new JFrame("Inicial");
        telaMenu.setSize(1280, 720);
        telaMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaMenu.setLocationRelativeTo(null);

        JPanel painel = new JPanel();
        painel.setLayout(new BoxLayout(painel, BoxLayout.Y_AXIS));

        JButton botaoIniciar = new JButton("Iniciar");
        JButton botaoSair = new JButton("Sair");
        JButton botaoInfo = new JButton("Informações");

        // Ações dos Botões
        botaoIniciar.addActionListener(e -> {
            telaMenu.dispose();
            jogo();
        });

        botaoInfo.addActionListener(e -> {
            telaMenu.dispose();
            ajuda();
        });

        botaoSair.addActionListener(e -> System.exit(0));

        painel.add(botaoIniciar);
        painel.add(botaoInfo);
        painel.add(botaoSair);

        telaMenu.add(painel);
        telaMenu.setVisible(true);
    }

    public void jogo(){
        JFrame telaJogo = new JFrame("Dispatch - Chat com Heróis");
        telaJogo.setSize(1080, 720);
        telaJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaJogo.setLocationRelativeTo(null);

        JPanel painel = new JPanel();

        telaJogo.add(painel);
        telaJogo.setVisible(true);
    }

    public void ajuda(){
        JFrame telaAjuda = new JFrame("Informações do Jogo");
        telaAjuda.setSize(1280, 720);
        telaAjuda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaAjuda.setLocationRelativeTo(null);

        JPanel painelAjuda = new JPanel();
        painelAjuda.setLayout(new BorderLayout());
        painelAjuda.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Margens

        String textoRegras =
                "BEM-VINDO AO DISPATCH: THE BOYS\n\n" +
                        "Você é o mais novo Coordenador de Despacho da Vought International.\n" +
                        "Sua função é crucial: monitorar as crises que acontecem pela cidade e enviar o " +
                        "Super-Herói correto para lidar com a situação.\n\n" +
                        "COMO JOGAR:\n" +
                        "1. Missões: Uma nova crise aparecerá na tela do seu computador.\n" +
                        "2. Tempo Limite: Você tem apenas 30 SEGUNDOS para tomar uma decisão.\n" +
                        "3. Os Sete: Avalie a situação e escolha o herói mais qualificado (ou o que causará menos danos à imagem da empresa).\n" +
                        "4. Consequências: Enviar o Capitão Pátria para resolver um resgate simples de gato em uma árvore pode ter... resultados explosivos.\n\n" +
                        "Mantenha os índices de aprovação da Vought altos, ou o Setor de Recursos Humanos (e talvez o Black Noir) fará uma visita a você. Boa sorte!";

        JTextArea areaTexto = new JTextArea(textoRegras);
        areaTexto.setFont(new Font("Consolas", Font.BOLD, 18));
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setEditable(false);
        areaTexto.setBackground(telaAjuda.getBackground());

        JButton botaoVoltar = new JButton("Voltar ao Menu Principal");
        botaoVoltar.setFont(new Font("Arial", Font.BOLD, 16));

        botaoVoltar.addActionListener(e -> {
            telaAjuda.dispose();
            menuPrincipal();
        });

        painelAjuda.add(areaTexto, BorderLayout.CENTER);
        painelAjuda.add(botaoVoltar, BorderLayout.SOUTH);

        telaAjuda.add(painelAjuda);
        telaAjuda.setVisible(true);
    }

    public void missao() throws InterruptedException{
        // swing da tela de herois disponiveis
        // vai chamar o metodo sortear cenario de dias
        // timer de 30 segundos
        // vai ficar esperando o front atualizar o heroi
        // quando atualizar vai chamar o metodo executar missao passando o cenario randomizado
    }
}