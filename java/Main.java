import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.components.uiresource.JButtonUIResource;
import com.github.weisj.darklaf.theme.DarculaTheme;

public class Main {

    private Dados dados;

    private int totalMissoesDoDia(int dia) {
        return dados.dias.getDivisaoCenarios()[dia][0]
                + dados.dias.getDivisaoCenarios()[dia][1]
                + dados.dias.getDivisaoCenarios()[dia][2];
    }
    public static void main(String[] args){
        LafManager.install(new DarculaTheme());
        SwingUtilities.invokeLater(() -> {
            Main app = new Main();
            app.dados = new Dados();
            app.menuPrincipal();
        });
    }

    public void menuPrincipal() {
        JFrame telaMenu = new JFrame("Menu Inicial");
        telaMenu.setSize(1920, 1080);
        telaMenu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaMenu.setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(100, 100, 100, 100));

        String titulo = "DISPATCH - GERENCIADOR DE HERÓIS";
        JTextArea areaTexto = new JTextArea(titulo);
        areaTexto.setFont(new Font("Consolas", Font.BOLD, 40));
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setEditable(false);
        areaTexto.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

        JButton botaoIniciar = new JButton("Iniciar");
        botaoIniciar.setFont(new Font("Arial", Font.BOLD, 30));

        boolean temSave = new java.io.File("save.ser").exists();
        JButton botaoContinuar = new JButton("Continuar");
        botaoContinuar.setFont(new Font("Arial", Font.BOLD, 30));
        botaoContinuar.setEnabled(temSave);

        JButton botaoSair = new JButton("Sair");
        botaoSair.setFont(new Font("Arial", Font.BOLD, 30));

        JButton botaoInfo = new JButton("Informações");
        botaoInfo.setFont(new Font("Arial", Font.BOLD, 30));


        botaoIniciar.addActionListener(e -> {
            telaMenu.dispose();
            dialogo(0);
        });

        botaoContinuar.addActionListener(e ->{
            telaMenu.dispose();
            dados = new Dados().recuperar();
            fase(dados.dias.getDiaAtual());
        });
        botaoSair.addActionListener(e -> System.exit(0));

        botaoInfo.addActionListener(e -> {
            telaMenu.dispose();
            ajuda();
        });


        painelBotoes.add(botaoIniciar);
        painelBotoes.add(botaoContinuar);
        painelBotoes.add(botaoInfo);
        painelBotoes.add(botaoSair);

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
        painelAjuda.setBorder(BorderFactory.createEmptyBorder(50, 100, 50, 100));

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

        // imagem começa vazia, trocarImagem() preenche logo abaixo
        JLabel labelImagem = new JLabel();
        labelImagem.setHorizontalAlignment(SwingConstants.CENTER);
        painelPrincipal.add(labelImagem, BorderLayout.CENTER);

        JPanel painelInferior = new JPanel(new BorderLayout());
        painelInferior.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));

        JTextArea areaTexto = new JTextArea();
        areaTexto.setFont(new Font("Consolas", Font.BOLD, 50));
        areaTexto.setLineWrap(true);
        areaTexto.setWrapStyleWord(true);
        areaTexto.setEditable(false);
        areaTexto.setBackground(painelPrincipal.getBackground());

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButtonUIResource botaoPassarTexto = new JButtonUIResource("Próximo");
        botaoPassarTexto.setFont(new Font("Arial", Font.BOLD, 50));
        painelBotoes.add(botaoPassarTexto);

        painelInferior.add(areaTexto, BorderLayout.CENTER);
        painelInferior.add(painelBotoes, BorderLayout.SOUTH);
        painelPrincipal.add(painelInferior, BorderLayout.SOUTH);

        // cada entrada: { "texto do dialogo", "nome_da_imagem.png" }
        String[][][] dialogos = {
                // Dia 1
                {
                        {"Bem-vindo à Vought. Sou Capitão Pátria! líder dos Sete!", "Homelanderfirst.png"},
                        {"Voce é o novo gerenciador da equipe, não é?",              "Homelanderfirst.png"},
                        {"Enfim, a cidade precisa de alguém qualificado",             "cidade.png"},
                        {"Não nos decepcione.",                                       "Homelanderfirst.png"}
                },
                // Dia 2
                {
                        {"Você até que mandou bem. Parabens.",             "Homelanderfirst.png"},
                        {"Bom trabalho no primeiro dia.",                  "Homelanderfirst.png"},
                        {"Mas as missões vão ficar mais difíceis.",        "Homelanderfirst.png"},
                        {"O Homelander está observando seu progresso.",    "Homelanderfirst.png"}
                },
                // Dia 3
                {
                        {"Este é o dia decisivo.",                   "Homelanderfirst.png"},
                        {"O futuro dos Sete está nas suas mãos.",    "Homelanderfirst.png"}
                },
                // Dia 4 (FINAL)
                {
                        {"ACABOU.",           "Homelanderfirst.png"},
                        {"TODOS MORRERAM.",   "Homelanderfirst.png"},
                        {"MUHAHAHHAHAHAHA.",  "Homelanderfirst.png"}
                }
        };

        int[] indice = {0};

        areaTexto.setText(dialogos[dia][0][0]);
        trocarImagem(labelImagem, dialogos[dia][0][1]);

        botaoPassarTexto.addActionListener(e -> {
            indice[0]++;
            if (indice[0] < dialogos[dia].length) {
                areaTexto.setText(dialogos[dia][indice[0]][0]);
                trocarImagem(labelImagem, dialogos[dia][indice[0]][1]);
            } else {
                telaJogo.dispose();
                if (dia >= dialogos.length - 1) {
                    // último diálogo (pós última fase) → tela de créditos
                    telaCreditos();
                } else {
                    fase(dia);
                }
            }
        });

        telaJogo.setVisible(true);
    }

    private void trocarImagem(JLabel labelImagem, String nomeArquivo) {
        java.io.File arquivo = new java.io.File(nomeArquivo);
        if (arquivo.exists()) {
            ImageIcon original = new ImageIcon(nomeArquivo);
            int largOriginal = original.getIconWidth();
            int altOriginal = original.getIconHeight();

            int alturaMax = 600;
            int larguraMax = 600;

            double escala = Math.min((double) larguraMax / largOriginal, (double) alturaMax / altOriginal);
            int novaLargura = (int) (largOriginal * escala);
            int novaAltura = (int) (altOriginal * escala);

            Image img = original.getImage().getScaledInstance(novaLargura, novaAltura, Image.SCALE_SMOOTH);
            labelImagem.setIcon(new ImageIcon(img));
            labelImagem.setText("");
        } else {
            labelImagem.setIcon(null);
            labelImagem.setText("erro: " + nomeArquivo + " não encontrado....");
        }
    }

    public void telaCreditos() {
        JFrame telaCreditos = new JFrame("DISPATCH - FIM DE JOGO");
        telaCreditos.setSize(1920, 1080);
        telaCreditos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaCreditos.setLocationRelativeTo(null);

        JPanel painelPrincipal = new JPanel(new BorderLayout());
        painelPrincipal.setBorder(BorderFactory.createEmptyBorder(100, 150, 100, 150));

        JTextArea areaTitulo = new JTextArea("FIM DE JOGO\n\nDISPATCH: THE BOYS");
        areaTitulo.setFont(new Font("Consolas", Font.BOLD, 50));
        areaTitulo.setEditable(false);
        areaTitulo.setLineWrap(true);
        areaTitulo.setWrapStyleWord(true);
        areaTitulo.setBackground(painelPrincipal.getBackground());
        areaTitulo.setForeground(Color.RED);

        JTextArea areaCreditos = new JTextArea(
                "Obrigado por jogar!\n\n" +
                        "CRÉDITOS\n" +
                        "Desenvolvimento: João Kaudy, Gustavo Gawlak, Arom\n" +
                        "Projeto de Programação Orientada a Objetos\n\n" +
                        "Personagens baseados na série \"The Boys\"."
        );
        areaCreditos.setFont(new Font("Consolas", Font.PLAIN, 24));
        areaCreditos.setEditable(false);
        areaCreditos.setLineWrap(true);
        areaCreditos.setWrapStyleWord(true);
        areaCreditos.setBackground(painelPrincipal.getBackground());

        JPanel painelTextos = new JPanel();
        painelTextos.setLayout(new BoxLayout(painelTextos, BoxLayout.Y_AXIS));
        painelTextos.add(areaTitulo);
        painelTextos.add(Box.createRigidArea(new Dimension(0, 40)));
        painelTextos.add(areaCreditos);

        JButton botaoMenu = new JButton("Voltar ao Menu Principal");
        botaoMenu.setFont(new Font("Arial", Font.BOLD, 30));
        botaoMenu.addActionListener(e -> {
            telaCreditos.dispose();
            // reinicia os dados para uma nova partida
            dados = new Dados();
            menuPrincipal();
        });

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.add(botaoMenu);

        painelPrincipal.add(painelTextos, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        telaCreditos.add(painelPrincipal);
        telaCreditos.setVisible(true);
    }
    public void telaGameOver() {
        JFrame telaGO = new JFrame("GAME OVER");
        telaGO.setSize(1920, 1080);
        telaGO.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaGO.setLocationRelativeTo(null);

        JPanel painel = new JPanel(new BorderLayout());
        painel.setBorder(BorderFactory.createEmptyBorder(100, 150, 100, 150));

        JTextArea titulo = new JTextArea("GAME OVER\n\nVocê falhou em 3 missões.\nO Homelander não está satisfeito...");
        titulo.setFont(new Font("Consolas", Font.BOLD, 50));
        titulo.setEditable(false);
        titulo.setLineWrap(true);
        titulo.setWrapStyleWord(true);
        titulo.setBackground(painel.getBackground());
        titulo.setForeground(Color.RED);

        JButton botaoMenu = new JButton("Voltar ao Menu Principal");
        botaoMenu.setFont(new Font("Arial", Font.BOLD, 30));
        botaoMenu.addActionListener(e -> {
            telaGO.dispose();
            dados = new Dados(); // reseta tudo, incluindo falhas (que começa em 0)
            menuPrincipal();
        });

        JPanel painelBotoes = new JPanel(new FlowLayout(FlowLayout.CENTER));
        painelBotoes.add(botaoMenu);

        painel.add(titulo, BorderLayout.CENTER);
        painel.add(painelBotoes, BorderLayout.SOUTH);

        telaGO.add(painel);
        telaGO.setVisible(true);
    }
    public void fase(int dia) {
        JFrame telaFase = new JFrame("DISPATCH - DIA " + (dia + 1));
        telaFase.setSize(1920, 1080);
        telaFase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaFase.setLocationRelativeTo(null);
        telaFase.setLayout(new BorderLayout());

        // ── PAINEL ESQUERDO: heróis ──
        JPanel painelHerois = new JPanel();
        painelHerois.setLayout(new BoxLayout(painelHerois, BoxLayout.Y_AXIS));
        painelHerois.setBorder(BorderFactory.createTitledBorder("OS SETE"));

        atualizarPainelHerois(painelHerois);

        JScrollPane scrollHerois = new JScrollPane(painelHerois);
        scrollHerois.setPreferredSize(new Dimension(350, 1080));
        scrollHerois.setBorder(BorderFactory.createEmptyBorder());
        scrollHerois.getVerticalScrollBar().setUnitIncrement(16);

        // ── PAINEL DIREITO: cidade com LayeredPane ──
        JLayeredPane painelCidade = new JLayeredPane();
        painelCidade.setPreferredSize(new Dimension(1570, 1080));

        // imagem de fundo da cidade
        JLabel imgCidade = new JLabel(new ImageIcon("cidade.png"));
        imgCidade.setBounds(0, 0, 1570, 1080);
        painelCidade.add(imgCidade, JLayeredPane.DEFAULT_LAYER);

        telaFase.add(scrollHerois, BorderLayout.WEST);
        telaFase.add(painelCidade, BorderLayout.CENTER);
        telaFase.setVisible(true);

        // ── SPAWNER de missões ──
        int[] missoesRestantes = { totalMissoesDoDia(dia) };
        agendarProximaMissao(painelCidade, telaFase, dia, missoesRestantes, painelHerois);
    }

    private void atualizarPainelHerois(JPanel painelHerois) {
        painelHerois.removeAll();

        Herois[] equipe = {
                dados.capitaoPatria,
                dados.luzEstrela,
                dados.rainhaMaeve,
                dados.blackNoir,
                dados.tremBala,
                dados.manaSabia,
                dados.profundo
        };

        for (Herois heroi : equipe) {
            painelHerois.add(criarCartaoHeroi(heroi));
            painelHerois.add(Box.createRigidArea(new Dimension(0, 8)));
        }

        painelHerois.add(Box.createVerticalGlue());

        painelHerois.revalidate();
        painelHerois.repaint();
    }

    private JPanel criarCartaoHeroi(Herois heroi) {
        JPanel cartao = new JPanel();
        cartao.setLayout(new BoxLayout(cartao, BoxLayout.Y_AXIS));
        cartao.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createEmptyBorder(6, 8, 6, 8),
                BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(new Color(90, 90, 90), 1, true),
                        BorderFactory.createEmptyBorder(8, 10, 8, 10)
                )
        ));
        cartao.setAlignmentX(Component.LEFT_ALIGNMENT);
        cartao.setMaximumSize(new Dimension(330, 200));

        boolean morto = heroi.verificarVida();
        boolean acordado = heroi.verificarDescanso();

        // status: vivo/morto e acordado/descansando
        String status;
        Color corStatus;
        if (morto) {
            status = "☠ MORTO";
            corStatus = new Color(220, 60, 60);
        } else if (!acordado) {
            status = "💤 DESCANSANDO";
            corStatus = new Color(220, 180, 60);
        } else {
            status = "✔ DISPONÍVEL";
            corStatus = new Color(80, 200, 120);
        }

        JLabel nome = new JLabel(heroi.getNome());
        nome.setFont(new Font("Arial", Font.BOLD, 18));
        nome.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel statusLabel = new JLabel(status);
        statusLabel.setFont(new Font("Arial", Font.BOLD, 13));
        statusLabel.setForeground(corStatus);
        statusLabel.setAlignmentX(Component.LEFT_ALIGNMENT);

        cartao.add(nome);
        cartao.add(statusLabel);
        cartao.add(Box.createRigidArea(new Dimension(0, 6)));

        // atributos: forca, velocidade, inteligencia, defesa
        double[] atributos = heroi.getAtributos();
        String[] nomesAtributos = {"Força", "Velocidade", "Inteligência", "Defesa"};

        JPanel painelAtributos = new JPanel(new GridLayout(2, 2, 6, 2));
        painelAtributos.setAlignmentX(Component.LEFT_ALIGNMENT);
        painelAtributos.setOpaque(false);
        for (int i = 0; i < atributos.length; i++) {
            JLabel atributoLabel = new JLabel(nomesAtributos[i] + ": " + String.format("%.1f", atributos[i]));
            atributoLabel.setFont(new Font("Consolas", Font.PLAIN, 12));
            painelAtributos.add(atributoLabel);
        }
        cartao.add(painelAtributos);
        cartao.add(Box.createRigidArea(new Dimension(0, 6)));

        // barra de XP
        int xp = heroi.getXp();
        int xpProximo = heroi.getXpProximoNivel();
        int progresso = xpProximo > 0 ? (int) (100.0 * xp / xpProximo) : 0;

        JProgressBar barraXp = new JProgressBar(0, 100);
        barraXp.setValue(progresso);
        barraXp.setStringPainted(true);
        barraXp.setString("XP: " + xp + " / " + xpProximo);
        barraXp.setAlignmentX(Component.LEFT_ALIGNMENT);
        barraXp.setMaximumSize(new Dimension(310, 18));

        cartao.add(barraXp);

        return cartao;
    }

    private void agendarProximaMissao(JLayeredPane painelCidade, JFrame tela, int dia, int[] restantes, JPanel painelHerois) {
        if (restantes[0] <= 0) {
            JOptionPane.showMessageDialog(tela,
                    "Todas as missões do dia foram concluídas!",
                    "DIA CONCLUÍDO",
                    JOptionPane.INFORMATION_MESSAGE);

            tela.dispose();
            dados.dias.passarDia();
            dados.salvar();
            dialogo(dia + 1);
            return;
        }

        int delayAleatorio = 3000 + (int)(Math.random() * 5000); // entre 3s e 8s

        Timer timer = new Timer(delayAleatorio, e -> {
            Cenarios cenario = dados.dias.sortearCenario();
            mostrarPopupMissao(painelCidade, tela, cenario, dia, restantes, painelHerois);
        });
        timer.setRepeats(false);
        timer.start();

    }

    private void mostrarPopupMissao(JLayeredPane painelCidade, JFrame tela, Cenarios cenario, int dia, int[] restantes, JPanel painelHerois) {

        int x = 50 + (int)(Math.random() * 1000);
        int y = 50 + (int)(Math.random() * 700);

        JPanel popup = new JPanel(new BorderLayout(5, 5));
        popup.setBackground(new Color(20, 20, 20, 220));
        popup.setBorder(BorderFactory.createLineBorder(Color.RED, 2));
        popup.setBounds(x, y, 400, 120);

        JLabel titulo = new JLabel("⚠ CRISE DETECTADA!", SwingConstants.CENTER);
        titulo.setForeground(Color.RED);
        titulo.setFont(new Font("Arial", Font.BOLD, 14));

        JLabel desc = new JLabel("<html>" + cenario.getDescricao() + "</html>", SwingConstants.CENTER);
        desc.setForeground(Color.WHITE);
        desc.setFont(new Font("Consolas", Font.PLAIN, 12));

        JButton btnAtender = new JButton("ATENDER");
        btnAtender.setBackground(new Color(255, 0, 0));
        btnAtender.setForeground(Color.WHITE);
        btnAtender.addActionListener(ev -> {
            painelCidade.remove(popup);
            painelCidade.repaint();
            restantes[0]--;
            missao(cenario, tela, painelCidade, dia, restantes, painelHerois);
            agendarProximaMissao(painelCidade, tela, dia, restantes, painelHerois);
        });

        popup.add(titulo, BorderLayout.NORTH);
        popup.add(desc, BorderLayout.CENTER);
        popup.add(btnAtender, BorderLayout.SOUTH);

        painelCidade.add(popup, JLayeredPane.POPUP_LAYER);
        painelCidade.repaint();
    }

    public void missao(Cenarios cenario, JFrame telaFase, JLayeredPane painelCidade, int dia, int[] restantes, JPanel painelHeroisLateral) {
        JFrame telaMissao = new JFrame("Nova Crise Detectada!");
        telaMissao.setSize(1080, 720);
        telaMissao.setLocationRelativeTo(null);
        telaMissao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaMissao.setLayout(new BorderLayout(20, 20));

        JPanel painelSuperior = new JPanel();
        painelSuperior.setLayout(new BoxLayout(painelSuperior, BoxLayout.Y_AXIS));
        painelSuperior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JPanel painelTopo = new JPanel(new BorderLayout());
        JTextArea infoCenario = new JTextArea("ALERTA DE CRISE: " + cenario.getDescricao() +
                "\nTempo para resposta: 30 segundos!");
        infoCenario.setFont(new Font("Consolas", Font.BOLD, 20));
        infoCenario.setEditable(false);

        JLabel labelTempo = new JLabel("30", SwingConstants.CENTER);
        labelTempo.setFont(new Font("Arial", Font.BOLD, 40));
        labelTempo.setForeground(Color.RED);

        painelTopo.add(infoCenario, BorderLayout.CENTER);
        painelTopo.add(labelTempo, BorderLayout.EAST);

        // ── PAINEL DE DETALHES DA MISSÃO ──
        JTextArea infoDetalhes = new JTextArea(
                "Máximo de heróis na equipe: " + cenario.getQuantidadeDeMembros() + "\n" +
                        "Atributos necessários: " + cenario.getAtributosNecessarios()
        );
        infoDetalhes.setFont(new Font("Consolas", Font.PLAIN, 16));
        infoDetalhes.setEditable(false);
        infoDetalhes.setLineWrap(true);
        infoDetalhes.setWrapStyleWord(true);
        infoDetalhes.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createTitledBorder("Informações da Missão"),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));

        painelSuperior.add(painelTopo);
        painelSuperior.add(Box.createRigidArea(new Dimension(0, 10)));
        painelSuperior.add(infoDetalhes);

        telaMissao.add(painelSuperior, BorderLayout.NORTH);

        JPanel painelSelecao = new JPanel(new GridLayout(3, 3, 10, 10));
        painelSelecao.setBorder(BorderFactory.createTitledBorder("Selecione a Equipe"));

        JCheckBox cbCapitao    = new JCheckBox("Capitão Pátria");
        JCheckBox cbLuzEstrela = new JCheckBox("Luz Estrela");
        JCheckBox cbMaeve      = new JCheckBox("Rainha Maeve");
        JCheckBox cbNoir       = new JCheckBox("Black Noir");
        JCheckBox cbTremBala   = new JCheckBox("Trem Bala");
        JCheckBox cbMana       = new JCheckBox("Mana Sábia");
        JCheckBox cbProfundo   = new JCheckBox("Profundo");

        painelSelecao.add(cbCapitao);
        painelSelecao.add(cbLuzEstrela);
        painelSelecao.add(cbMaeve);
        painelSelecao.add(cbNoir);
        painelSelecao.add(cbTremBala);
        painelSelecao.add(cbMana);
        painelSelecao.add(cbProfundo);

        telaMissao.add(painelSelecao, BorderLayout.CENTER);

        JButton btnEnviar = new JButton("ENVIAR EQUIPE");
        btnEnviar.setFont(new Font("Arial", Font.BOLD, 24));
        telaMissao.add(btnEnviar, BorderLayout.SOUTH);

        Timer timer = new Timer(1000, new ActionListener() {
            int tempoRestante = 30;

            @Override
            public void actionPerformed(ActionEvent e) {
                tempoRestante--;
                labelTempo.setText(String.valueOf(tempoRestante));

                if (tempoRestante <= 0) {
                    ((Timer) e.getSource()).stop();
                    telaMissao.dispose();
                    JOptionPane.showMessageDialog(null,
                            "TEMPO ESGOTADO! A missão falhou.",
                            "FALHA", JOptionPane.ERROR_MESSAGE);
                    dados.falhas++;
                    if (dados.falhas >= 3) {
                        telaGameOver();
                        return;
                    }
                    JOptionPane.showMessageDialog(null,
                            "⚠ Falhas acumuladas: " + dados.falhas + "/3",
                            "Atenção!", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        timer.start();

        btnEnviar.addActionListener(e -> {

            ArrayList<Herois> selecionados = new ArrayList<>();
            if (cbCapitao.isSelected())    selecionados.add(dados.capitaoPatria);
            if (cbLuzEstrela.isSelected()) selecionados.add(dados.luzEstrela);
            if (cbMaeve.isSelected())      selecionados.add(dados.rainhaMaeve);
            if (cbNoir.isSelected())       selecionados.add(dados.blackNoir);
            if (cbTremBala.isSelected())   selecionados.add(dados.tremBala);
            if (cbMana.isSelected())       selecionados.add(dados.manaSabia);
            if (cbProfundo.isSelected())   selecionados.add(dados.profundo);

            telaMissao.dispose();

            if (selecionados.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Você não enviou ninguém! A missão falhou.");
                dados.falhas++;
                if (dados.falhas >= 3) {
                    telaGameOver();
                    return;
                }
                JOptionPane.showMessageDialog(null,
                        "⚠ Falhas acumuladas: " + dados.falhas + "/3",
                        "Atenção!", JOptionPane.WARNING_MESSAGE);
            } else {
                Equipes equipeEnviada = new Equipes(selecionados.toArray(new Herois[0]));
                timer.stop();

                try {
                    if (cenario.getQuantidadeDeMembros() < equipeEnviada.getGrupo().size()) {
                        throw new EquipeExcesso();
                    }
                    for (Herois h : equipeEnviada.getGrupo()) {
                        if (h.verificarVida()) {
                            throw new MembroMorto();
                        }
                        if (!h.verificarDescanso()) {
                            throw new MembroDesmaiado();
                        }
                    }
                } catch (MembroMorto | MembroDesmaiado | EquipeExcesso es) {
                    JOptionPane.showMessageDialog(null, es.getMessage());
                    return;
                }finally {
                    if (dados.dias.verificarFalha()){
                        System.out.println("Falou");
                        String culpadoFalha=dados.dias.motivoFalha();
                        switch (culpadoFalha) {
                            case "Capitão Pátria falhou":JOptionPane.showMessageDialog(null, "Capitão Patria falhou!");
                            case "Luz Estrela falhou":JOptionPane.showMessageDialog(null, "Luz Estrela falhou!");
                            case "Rainha Maeve falhou":JOptionPane.showMessageDialog(null, "Rainha Maeve falhou!");
                            case "Black Noir falhou":JOptionPane.showMessageDialog(null, "Black Noir falhou!");
                            case "Trem Bala falhou":JOptionPane.showMessageDialog(null, "Trem Bala falhou!");
                            case "Mana Sábia falhou":JOptionPane.showMessageDialog(null, "Mana Sabia falhou!");
                            case "Profundo falhou":JOptionPane.showMessageDialog(null, "Profundo falhou!");
                        }

                    }
                }

                executarMissaoComPopup(cenario, equipeEnviada, dia, painelHeroisLateral, telaFase);
            }
        });

        telaMissao.setVisible(true);
    }

    private void executarMissaoComPopup(Cenarios cenario, Equipes equipeEnviada, int dia, JPanel painelHeroisLateral, JFrame telaFase) {

        JDialog dialogoEspera = new JDialog(telaFase, "Aguarde", Dialog.ModalityType.APPLICATION_MODAL);
        dialogoEspera.setUndecorated(true);
        dialogoEspera.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);

        JPanel painelEspera = new JPanel();
        painelEspera.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.RED, 2),
                BorderFactory.createEmptyBorder(30, 50, 30, 50)
        ));
        JLabel labelEspera = new JLabel("MISSÃO SENDO REALIZADA...");
        labelEspera.setFont(new Font("Consolas", Font.BOLD, 24));
        painelEspera.add(labelEspera);

        dialogoEspera.getContentPane().add(painelEspera);
        dialogoEspera.pack();
        dialogoEspera.setLocationRelativeTo(telaFase);

        // Guarda se a missão foi bem-sucedida para usar no done()
        boolean[] sucessoMissao = {false};

        SwingWorker<Void, Void> worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() {
                try {
                    dados.dias.executarMissao(cenario, equipeEnviada);
                    // Se chegou aqui sem exceção, verificamos o resultado real:
                    // missão é sucesso se nenhum herói da equipe está morto e culpadoFalha é 9 (falha de atributo) ou não houve falha
                    // culpadoFalha == -1 significa sucesso
                    sucessoMissao[0] = dados.dias.getCulpadoFalha() == -1;
                } catch (InterruptedException es) {
                    System.out.println("Erro de continuação: " + es.getMessage());
                } catch (MembroMorto | MembroDesmaiado | EquipeExcesso es) {
                    System.out.println(es.getMessage());
                }
                return null;
            }

            @Override
            protected void done() {
                dialogoEspera.dispose();

                // Se missão foi bem-sucedida, inicia descanso assíncrono para todos da equipe
                if (sucessoMissao[0]) {
                    for (Herois h : equipeEnviada.getGrupo()) {
                        h.descansarAsync();
                    }
                    // Atualiza a UI periodicamente enquanto heróis descansam
                    iniciarAtualizacaoPeriodica(painelHeroisLateral, equipeEnviada);
                }

                telaResultados(cenario, equipeEnviada, dia, painelHeroisLateral, telaFase, sucessoMissao[0]);
            }
        };

        worker.execute();
        dialogoEspera.setVisible(true);
    }

    private void iniciarAtualizacaoPeriodica(JPanel painelHeroisLateral, Equipes equipeEnviada) {
        Timer atualizador = new Timer(1000, null);
        atualizador.addActionListener(e -> {
            atualizarPainelHerois(painelHeroisLateral);
            // Para o timer quando todos da equipe voltaram a estar disponíveis
            boolean algumDescansando = equipeEnviada.getGrupo().stream()
                    .anyMatch(h -> !h.verificarDescanso() && !h.verificarVida());
            if (!algumDescansando) {
                atualizador.stop();
                atualizarPainelHerois(painelHeroisLateral); // atualização final
            }
        });
        atualizador.start();
    }

    public void telaResultados(Cenarios cenario, Equipes equipeEnviada, int dia, JPanel painelHeroisLateral, JFrame telaFase, boolean sucesso) {
        String mensagemResultado;
        if (sucesso) {
            mensagemResultado = "✅ Missão concluída com sucesso!\n" + cenario.getXpDado() + " XP\n\nOs heróis enviados estão descansando e ficarão\nindisponíveis por alguns segundos.";
        } else {
            boolean algumMorreu = equipeEnviada.getGrupo().stream().anyMatch(Herois::verificarVida);
            if (algumMorreu) {
                mensagemResultado = "❌ A missão falhou!\nMotivo: " + dados.dias.motivoFalha() + "\n\nAlguns heróis morreram!";
            } else {
                mensagemResultado = "❌ A missão falhou!\nMotivo: " + dados.dias.motivoFalha();
            }
        }

        JOptionPane.showMessageDialog(null,
                "Missão: " + cenario.getDescricao() + "\n\n" + mensagemResultado,
                sucesso ? "Missão Concluída" : "Missão Falhou",
                sucesso ? JOptionPane.INFORMATION_MESSAGE : JOptionPane.ERROR_MESSAGE);
        if (!sucesso) {
            dados.falhas++;
            if (dados.falhas >= 3) {
                telaGameOver();
                return;
            }
            JOptionPane.showMessageDialog(null,
                    "⚠ Falhas acumuladas: " + dados.falhas + "/3",
                    "Atenção!", JOptionPane.WARNING_MESSAGE);
        }
        if (sucesso) {
            String[] opcoes = {"Forca", "Velocidade", "Inteligencia", "Defesa"};
            for (Herois h : dados.dias.getHeroisQueEvoluiram()) {
                int escolha = JOptionPane.showOptionDialog(null,
                        h.getNome() + " subiu de nível!\nEscolha um atributo:",
                        "Nível UP!", JOptionPane.DEFAULT_OPTION,
                        JOptionPane.QUESTION_MESSAGE, null, opcoes, opcoes[0]);
                h.evoluir(opcoes[escolha]);
            }
        }

        atualizarPainelHerois(painelHeroisLateral);
        dados.salvar();
    }

}