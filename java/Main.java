import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import com.github.weisj.darklaf.LafManager;
import com.github.weisj.darklaf.components.uiresource.JButtonUIResource;
import com.github.weisj.darklaf.theme.DarculaTheme;

public class Main {

    private Equipes equipe;
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
            app.equipe = new Equipes(new Herois[0]);
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
                fase(dia);
            }
        });

        telaJogo.setVisible(true);
    }

    private void trocarImagem(JLabel labelImagem, String nomeArquivo) {
        java.io.File arquivo = new java.io.File(nomeArquivo);
        if (arquivo.exists()) {
            Image img = new ImageIcon(nomeArquivo).getImage().getScaledInstance(600, 600, Image.SCALE_SMOOTH);
            labelImagem.setIcon(new ImageIcon(img));
            labelImagem.setText("");
        } else {
            labelImagem.setIcon(null);
            labelImagem.setText("erro: " + nomeArquivo + " não encontrado....");
        }
    }

    public void fase(int dia) {
        JFrame telaFase = new JFrame("DISPATCH - DIA " + (dia + 1));
        telaFase.setSize(1920, 1080);
        telaFase.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        telaFase.setLocationRelativeTo(null);
        telaFase.setLayout(new BorderLayout());

        // ── PAINEL ESQUERDO: heróis ──
        JPanel painelHerois = new JPanel();
        painelHerois.setPreferredSize(new Dimension(350, 1080));
        painelHerois.setLayout(new BoxLayout(painelHerois, BoxLayout.Y_AXIS));
        painelHerois.setBorder(BorderFactory.createTitledBorder("OS SETE"));

        // ── PAINEL DIREITO: cidade com LayeredPane ──
        JLayeredPane painelCidade = new JLayeredPane();
        painelCidade.setPreferredSize(new Dimension(1570, 1080));

        // imagem de fundo da cidade
        JLabel imgCidade = new JLabel(new ImageIcon("cidade.png"));
        imgCidade.setBounds(0, 0, 1570, 1080);
        painelCidade.add(imgCidade, JLayeredPane.DEFAULT_LAYER);

        telaFase.add(painelHerois, BorderLayout.WEST);
        telaFase.add(painelCidade, BorderLayout.CENTER);
        telaFase.setVisible(true);

        // ── SPAWNER de missões ──
        int[] missoesRestantes = { totalMissoesDoDia(dia) };
        agendarProximaMissao(painelCidade, telaFase, dia, missoesRestantes);
    }

    private void agendarProximaMissao(JLayeredPane painelCidade, JFrame tela, int dia, int[] restantes) {
        if (restantes[0] <= 0) {
            // todas as missões do dia concluídas → próximo dia ou tela final
            tela.dispose();
            dados.dias.passarDia();
            dados.salvar();
            dialogo(dia + 1);
            return;
        }

        int delayAleatorio = 3000 + (int)(Math.random() * 5000); // entre 3s e 8s

        Timer timer = new Timer(delayAleatorio, e -> {
            Cenarios cenario = dados.dias.sortearCenario();
            mostrarPopupMissao(painelCidade, tela, cenario, dia, restantes);
        });
        timer.setRepeats(false);
        timer.start();

    }

    private void mostrarPopupMissao(JLayeredPane painelCidade, JFrame tela, Cenarios cenario, int dia, int[] restantes) {

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
        btnAtender.setBackground(new Color(212, 80, 80));
        btnAtender.setForeground(Color.WHITE);
        btnAtender.addActionListener(ev -> {
            painelCidade.remove(popup);
            painelCidade.repaint();
            restantes[0]--;
            missao(cenario, tela, painelCidade, dia, restantes);
            agendarProximaMissao(painelCidade, tela, dia, restantes);
        });

        popup.add(titulo, BorderLayout.NORTH);
        popup.add(desc, BorderLayout.CENTER);
        popup.add(btnAtender, BorderLayout.SOUTH);

        painelCidade.add(popup, JLayeredPane.POPUP_LAYER);
        painelCidade.repaint();
    }

    public void missao(Cenarios cenario, JFrame telaFase, JLayeredPane painelCidade, int dia, int[] restantes) {
        JFrame telaMissao = new JFrame("Nova Crise Detectada!");
        telaMissao.setSize(1080, 720);
        telaMissao.setLocationRelativeTo(null);
        telaMissao.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaMissao.setLayout(new BorderLayout(20, 20));

        JPanel painelSuperior = new JPanel(new BorderLayout());
        JTextArea infoCenario = new JTextArea("ALERTA DE CRISE: " + cenario.getDescricao() +
                "\nTempo para resposta: 30 segundos!");
        infoCenario.setFont(new Font("Consolas", Font.BOLD, 20));
        infoCenario.setEditable(false);

        JLabel labelTempo = new JLabel("30", SwingConstants.CENTER);
        labelTempo.setFont(new Font("Arial", Font.BOLD, 40));
        labelTempo.setForeground(Color.RED);

        painelSuperior.add(infoCenario, BorderLayout.CENTER);
        painelSuperior.add(labelTempo, BorderLayout.EAST);
        telaMissao.add(painelSuperior, BorderLayout.NORTH);

        JPanel painelHerois = new JPanel(new GridLayout(3, 3, 10, 10));
        painelHerois.setBorder(BorderFactory.createTitledBorder("Selecione a Equipe"));

        JCheckBox cbCapitao    = new JCheckBox("Capitão Pátria");
        JCheckBox cbLuzEstrela = new JCheckBox("Luz Estrela");
        JCheckBox cbMaeve      = new JCheckBox("Rainha Maeve");
        JCheckBox cbNoir       = new JCheckBox("Black Noir");
        JCheckBox cbTremBala   = new JCheckBox("Trem Bala");
        JCheckBox cbMana       = new JCheckBox("Mana Sábia");
        JCheckBox cbProfundo   = new JCheckBox("Profundo");

        painelHerois.add(cbCapitao);
        painelHerois.add(cbLuzEstrela);
        painelHerois.add(cbMaeve);
        painelHerois.add(cbNoir);
        painelHerois.add(cbTremBala);
        painelHerois.add(cbMana);
        painelHerois.add(cbProfundo);

        telaMissao.add(painelHerois, BorderLayout.CENTER);

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
                }
            }
        });
        timer.start();

        btnEnviar.addActionListener(e -> {
            timer.stop();

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
            } else {
                Equipes equipeEnviada = new Equipes(selecionados.toArray(new Herois[0]));
                telaResultados(cenario, equipeEnviada, dia);
            }
        });

        telaMissao.setVisible(true);
    }

    public void telaResultados(Cenarios cenario, Equipes equipeEnviada, int dia) {
        JOptionPane.showMessageDialog(null,
                "Equipe enviada para: " + cenario.getDescricao() + "\nAguarde o resultado...",
                "Missão em andamento",
                JOptionPane.INFORMATION_MESSAGE);


        dados.salvar();
    }

}