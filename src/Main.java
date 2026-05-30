import javax.swing.*;
import java.awt.*;
import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.components.uiresource.JButtonUIResource;
import com.github.weisj.darklaf.theme.DarculaTheme;
import javax.swing.SwingUtilities;

public class Main {

    private Equipes equipe;

    private CapitaoPatria capitaoPatria = new CapitaoPatria();
    private LuzEstrela luzEstrela = new LuzEstrela();
    private RainhaMaeve rainhaMaeve = new RainhaMaeve();
    private BlackNoir blackNoir = new BlackNoir();
    private TremBala tremBala = new TremBala();
    private ManaSabia manaSabia = new ManaSabia();
    private Profundo profundo = new Profundo();

    public static void main(String[] args){

        LafManager.install(new DarculaTheme());
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.equipe = new Equipes(app.capitaoPatria, app.luzEstrela, app.rainhaMaeve, app.blackNoir, app.tremBala, app.manaSabia, app.profundo);
            app.menuPrincipal();
        });

    }

    public void menuPrincipal() {

        JFrame telaMenu = new JFrame("Menu Inicial");
        telaMenu.setSize(1920, 1080);
        telaMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaMenu.setLocationRelativeTo(null);

        // Definindo BorderLayout para o painel principal conseguir organizar o texto e botões
        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        String titulo = "DISPATCH - GERENCIADOR DE HERÓIS";
        JTextArea areaTexto = new JTextArea(titulo);
        areaTexto.setFont(new Font("Consolas", Font.BOLD, 40));
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setEditable(false);
        areaTexto.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Painel exclusivo para os botões ficarem alinhados lado a lado embaixo
        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton botaoIniciar = new JButton("Iniciar");
        JButton botaoSair = new JButton("Sair");
        JButton botaoInfo = new JButton("Informações");
        botaoIniciar.setFont(new Font("Arial", Font.BOLD, 30));
        botaoInfo.setFont(new Font("Arial", Font.BOLD, 30));
        botaoSair.setFont(new Font("Arial", Font.BOLD, 30));

        // Ações dos Botões
        botaoIniciar.addActionListener(e -> {
            telaMenu.dispose();
            dialogo(0);
        });

        botaoInfo.addActionListener(e -> {
            telaMenu.dispose();
            ajuda();
        });

        botaoSair.addActionListener(e -> System.exit(0));

        painelBotoes.add(botaoIniciar);
        painelBotoes.add(botaoInfo);
        painelBotoes.add(botaoSair);

        // Adicionando os elementos respeitando o BorderLayout
        painelPrincipal.add(areaTexto, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        telaMenu.add(painelPrincipal);

        telaMenu.setVisible(true);

    }

    public void ajuda(){
        JFrame telaAjuda = new JFrame("Informações do Jogo");
        telaAjuda.setSize(1920, 1080);
        telaAjuda.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaAjuda.setLocationRelativeTo(null);

        JPanel painelAjuda = new JPanel();
        painelAjuda.setLayout(new BorderLayout());
        painelAjuda.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100)); // Margens

        String textoRegras =
                "BEM-VINDO AO DISPATCH: THE BOYS\n\n" +
                        "Você é o mais novo Coordenador da equipe de heróis da Vought International.\n" +
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

    public void dialogo(int dia) {

        JFrame telaJogo = new JFrame("DISPATCH - GERENCIADOR DE HERÓIS");
        telaJogo.setSize(1920, 1080);
        telaJogo.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaJogo.setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(30, 30, 30, 30));
        telaJogo.add(painelPrincipal);

        // --- SISTEMA DE VERIFICAÇÃO DA IMAGEM ---
        JLabel labelImagem;
        java.io.File arquivoImg = new java.io.File("Homelanderfirst.png");

        if (arquivoImg.exists()) {
            ImageIcon iconeOriginal = new ImageIcon("Homelanderfirst.png");
            Image imagemRedimensionada = iconeOriginal.getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
            ImageIcon iconeFinal = new ImageIcon(imagemRedimensionada);
            labelImagem = new JLabel(iconeFinal);
        } else {
            // Se não existir, cria um texto de aviso para você saber que o caminho está errado
            labelImagem = new JLabel("ERRO: Imagem 'Homelanderfirst.png' não encontrada na raiz do projeto!");
            labelImagem.setFont(new Font("Arial", Font.BOLD, 20));
            labelImagem.setForeground(Color.RED);
            labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
        }

        painelPrincipal.add(labelImagem, BorderLayout.CENTER);

        // --- PAINEL INFERIOR ---
        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        String textoDialogo1 = "TESTE TESTE TESTE TESTE - Uma crise foi detectada no centro de Nova York!";
        JTextArea areaTexto = new JTextArea(textoDialogo1);
        areaTexto.setFont(new Font("Consolas", Font.BOLD, 24));
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setEditable(false);
        areaTexto.setBackground(painelPrincipal.getBackground());

        painelInferior.add(areaTexto, BorderLayout.CENTER);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButtonUIResource botaoPassarTexto = new JButtonUIResource("Próximo");
        botaoPassarTexto.setFont(new Font("Arial", Font.BOLD, 26));
        painelBotoes.add(botaoPassarTexto);

        painelInferior.add(painelBotoes, BorderLayout.SOUTH);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        String[][] dialogos = {
                // Dia 1
                {
                        "a",
                        "Bem-vindo à Vought. Sou Madelyn Stillwell.",
                        "Os Sete precisam de um gerenciador de missões.",
                        "Você foi selecionado. Não nos decepcione."
                },
                // Dia 2
                {
                        "Homelanderfirst.png",
                        "Bom trabalho no primeiro dia.",
                        "Mas as missões vão ficar mais difíceis.",
                        "O Homelander está observando seu progresso."
                },
                // Dia 3
                {
                        "Homelanderfirst.png",
                        "Este é o dia decisivo.",
                        "O futuro dos Sete está nas suas mãos."
                },
                // Dia 4 (FINAL)
                {
                        "Homelanderfirst.png",
                        "ACABOU.",
                        "TODOS MORRERAM.",
                        "MUHAHAHHAHAHAHA."
                }
        };

        int[] indice = {0};

        areaTexto.setText(dialogos[dia][indice[0]]);

        botaoPassarTexto.addActionListener(e -> {
            indice[0]++;
            if (indice[0] < dialogos[dia].length) {
                areaTexto.setText(dialogos[dia][indice[0]]);
            } else {
                telaJogo.dispose();
                try { missao(); } catch (InterruptedException ex) { ex.printStackTrace(); }
            }
        });

        telaJogo.setVisible(true);

    }


    public void missao() throws InterruptedException{

        // swing da tela de herois disponiveis
        // vai chamar o metodo sortear cenario de dias
        // timer de 30 segundos
        // vai ficar esperando o front atualizar o heroi
        // quando atualizar vai chamar o metodo executar missao passando o cenario randomizado
    }
}