import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Dias implements Serializable {
    // DIAS = fases

    private ArrayList<Cenarios> cenariosFaceis;
    private ArrayList<Cenarios> cenariosMedios;
    private ArrayList<Cenarios> cenariosDificeis;
    private int quantidadeMissoesExecutadas;
    private Equipes equipeEnviada;
    private int culpadoFalha;
    private int missaoAtual;
    private int[][] divisaoCenarios = {
            {4, 2, 0},   // dia 0 (fase 1): 4 fáceis + 2 médias
            {0, 4, 2},   // dia 1 (fase 2): 4 médias + 3 difíceis
            {0, 0, 3}    // dia 2 (fase 3): 4 difíceis
    };
    private int diaAtual;

    // CONSTRUTOR
    public Dias() {
        this.cenariosFaceis = new ArrayList<Cenarios>();
        this.cenariosMedios = new ArrayList<Cenarios>();
        this.cenariosDificeis = new ArrayList<Cenarios>();
        this.culpadoFalha = 0;
        this.missaoAtual = 0;
        this.diaAtual = 0;

// CENARIOS FACEIS
        this.cenariosFaceis.add(new Cenarios("Resgatar gatinho cibernético na árvore", 1, 2, 1, 1, 2, 3, 2, 1, 100));
        this.cenariosFaceis.add(new Cenarios("Ajudar Velho Pucrson: O sumiço da dentadura", 1, 1, 3, 1, 3, 4, 3, 1, 100));
        this.cenariosFaceis.add(new Cenarios("Recuperar Wi-Fi do bar local", 1, 1, 2, 1, 1, 2, 1, 1, 100));
        this.cenariosFaceis.add(new Cenarios("Espantar pombos mutantes da praça", 2, 2, 1, 2, 2, 3, 2, 1, 100));
        this.cenariosFaceis.add(new Cenarios("Entregar pizza fria na casa branca", 1, 4, 1, 1, 4, 1, 4, 1, 100));
        this.cenariosFaceis.add(new Cenarios("Desativar despertador barulhento da senhora de idade", 1, 2, 2, 1, 1, 2, 1, 1, 100));
        this.cenariosFaceis.add(new Cenarios("Encontrar os óculos do prefeito", 1, 1, 3, 1, 2, 4, 2, 1, 100));
        this.cenariosFaceis.add(new Cenarios("Limpar gosma verde dos esgotos", 3, 1, 1, 3, 3, 5, 3, 1, 100));
        this.cenariosFaceis.add(new Cenarios("Capturar animais do zoológico que sumiram", 2, 3, 2, 2, 4, 4, 4, 1, 100));
        this.cenariosFaceis.add(new Cenarios("Escoltar carrinho de feira da vovó", 2, 1, 1, 3, 3, 4, 3, 1, 100));

// CENARIOS MÉDIOS
        this.cenariosMedios.add(new Cenarios("Conter surto de IA em uma empresa", 2, 3, 5, 2, 5, 8, 5, 2, 200));
        this.cenariosMedios.add(new Cenarios("Ajudar Velho Pucrson: A conspiração do asilo", 3, 2, 4, 3, 4, 6, 4, 1, 200));
        this.cenariosMedios.add(new Cenarios("Para briga em bar de vilões", 5, 3, 1, 4, 2, 5, 2, 2, 200));
        this.cenariosMedios.add(new Cenarios("Escoltar cantor famoso dentro do homelander music show", 4, 4, 3, 4, 6, 10, 6, 2, 200));
        this.cenariosMedios.add(new Cenarios("Rastrear contrabandistas de composto V falso", 3, 4, 5, 3, 5, 7, 5, 1, 200));
        this.cenariosMedios.add(new Cenarios("Interromper ritual de ceita", 4, 3, 4, 4, 4, 6, 4, 2, 200));
        this.cenariosMedios.add(new Cenarios("Impedir the boys dentro do hospício", 5, 5, 3, 5, 5, 9, 5, 2, 200));

// CENARIOS DIFÍCEIS
        this.cenariosDificeis.add(new Cenarios("Desarmar ogiva nuclear", 4, 6, 8, 5, 10, 15, 10, 2, 400));
        this.cenariosDificeis.add(new Cenarios("Ajudar Velho Pucrson: O confronto final no .env", 6, 6, 7, 6, 8, 12, 8, 3, 450));
        this.cenariosDificeis.add(new Cenarios("Derrotar o Bruto louco com composto V", 9, 6, 4, 8, 6, 14, 6, 3, 500));
        this.cenariosDificeis.add(new Cenarios("Impedir guerra entre supers", 8, 8, 8, 8, 12, 20, 12, 4, 600));
        this.cenariosDificeis.add(new Cenarios("Impedir vulcao em erupcao", 9, 5, 5, 9, 15, 18, 15, 2, 500));
    }

    // ESPECIAIS
    public void passarDia() {
        for (int i = 0; i < cenariosFaceis.size(); i++) {
            this.cenariosFaceis.get(i).aumentardificuldade();
        }
        for (int i = 0; i < cenariosMedios.size(); i++) {
            this.cenariosMedios.get(i).aumentardificuldade();
        }
        for (int i = 0; i < cenariosDificeis.size(); i++) {
            this.cenariosDificeis.get(i).aumentardificuldade();
        }
        if (this.diaAtual < this.divisaoCenarios.length - 1) {
            this.diaAtual++;
        }
        this.missaoAtual = 0;
    }

    public void darXp(Equipes equipeEnviada, int xp) {
        for (int i = 0; i < equipeEnviada.getGrupo().size(); i++) {
            equipeEnviada.getGrupo().get(i).darXp(xp);
        }
    }

    public void executarMissao(Cenarios cenario, Equipes equipeEnviada) throws InterruptedException,EquipeExcesso,MembroDesmaiado,MembroMorto {

        if (equipeEnviada.getGrupo().isEmpty()) {
            this.culpadoFalha = 0;
            return;

        } else {
            if (cenario.getQuantidadeDeMembros()<equipeEnviada.getGrupo().size()){
                throw new EquipeExcesso();
            }
            for (int i = 0; i < equipeEnviada.getGrupo().size(); i++) {
                if (equipeEnviada.getGrupo().get(i).verificarVida()) {
                    throw new MembroMorto();
                }
                if (!equipeEnviada.getGrupo().get(i).verificarDescanso()){
                    throw new MembroDesmaiado();
                }
            }

            // TIMER DE IDA MANEIRO!!!!!
            Thread.sleep(cenario.getTempoDeIda() * 1000L);

            // TIMER DE EXECUÇÃO LEGAL!!!!
            Thread.sleep(cenario.getTempoDeExecucao() * 1000L);

            double[] resultado = equipeEnviada.executarMissao();
            if (resultado.length == 1) {
                this.culpadoFalha = (int) resultado[0];
                equipeEnviada.descansarPorCodigo(this.culpadoFalha);
            } else {
                // MÉDIA DOS ATRIBUTOS DO CENÁRIO (só atributos exigidos, ou seja, > 0)
                double[] atributos = cenario.getAtributos();
                int somaAtributosC = 0;
                int quantidadeAtributosC = 0;
                for (int i = 0; i < atributos.length; i++) {
                    if (atributos[i] > 0) {
                        somaAtributosC += atributos[i];
                        quantidadeAtributosC++;
                    }
                }

                double mediaAtributosC = (quantidadeAtributosC > 0) ? (double) somaAtributosC / quantidadeAtributosC : 0;

                // MÉDIA DOS ATRIBUTOS DOS HERÓIS — usando APENAS os índices que a missão exige
                // Índices: 0=velocidade, 1=inteligencia, 2=defesa, 3=forca (ordem de executarMissao em Herois)
                // Cenário:  0=forca,      1=velocidade,   2=inteligencia, 3=defesa (ordem de getAtributos em Cenarios)
                // Mapeamento: cenario[0](forca) -> heroi[3], cenario[1](vel) -> heroi[0], cenario[2](int) -> heroi[1], cenario[3](def) -> heroi[2]
                int[] mapCenarioParaHeroi = {3, 0, 1, 2};

                double somaAtributosH = 0;
                int quantidadeAtributosH = 0;
                for (int i = 0; i < atributos.length; i++) {
                    if (atributos[i] > 0) {
                        somaAtributosH += resultado[mapCenarioParaHeroi[i]];
                        quantidadeAtributosH++;
                    }
                }

                double mediaAtributosH = (quantidadeAtributosH > 0) ? somaAtributosH / quantidadeAtributosH : 0;

                Random random = new Random();
                double numRandom = random.nextDouble() * mediaAtributosC;

                if (mediaAtributosH >= numRandom) {
                    System.out.println("A Missão foi concluída com Sucesso!");
                    darXp(equipeEnviada, cenario.getXpDado());
                    this.culpadoFalha = -1; // -1 = sucesso, sem culpado
                } else {
                    equipeEnviada.falha();
                    this.culpadoFalha = 9;
                }

                // TIMER DE VOLTA
                Thread.sleep(cenario.getTempoDeVolta() * 1000L);
            }
        }
    }

    public Cenarios sortearCenario() {
        Random random = new Random();

        int qtdFaceis= divisaoCenarios[diaAtual][0];
        int qtdMedias= divisaoCenarios[diaAtual][1];
        int qtdDificeis= divisaoCenarios[diaAtual][2];

        int limFacil = qtdFaceis; // 0 até limFacil-1
        int limMedio= qtdFaceis + qtdMedias; // limFacil até limMedio-1
        int limDificil= qtdFaceis + qtdMedias + qtdDificeis; // limMedio até limDificil-1

        if (missaoAtual < limFacil) {
            return cenariosFaceis.get(random.nextInt(cenariosFaceis.size()));
        } else if (missaoAtual < limMedio) {
            return cenariosMedios.get(random.nextInt(cenariosMedios.size()));
        } else if (missaoAtual < limDificil) {
            return cenariosDificeis.get(random.nextInt(cenariosDificeis.size()));
        }
        if (qtdDificeis > 0)  return cenariosDificeis.get(random.nextInt(cenariosDificeis.size()));
        if (qtdMedias   > 0)  return cenariosMedios.get(random.nextInt(cenariosMedios.size()));
        return cenariosFaceis.get(random.nextInt(cenariosFaceis.size()));
    }

    public int getCulpadoFalha() {
        return culpadoFalha;
    }

    public String motivoFalha() {
        switch (culpadoFalha) {
            case -1: return "Missão concluída com sucesso";
            case 0: return "Falta de herois";
            case 1: return "Capitão Pátria falhou";
            case 2: return "Luz Estrela falhou";
            case 3: return "Rainha Maeve falhou";
            case 4: return "Black Noir falhou";
            case 5: return "Trem Bala falhou";
            case 6: return "Mana Sábia falhou";
            case 7: return "Profundo falhou";
            case 8:
            case 9: return "Falha em um teste de Atributo";
            default: return "Culpado desconhecido";
        }
    }

    // GETTERS

    public int getMissaoAtual() {
        return missaoAtual;
    }

    public int getDiaAtual() {
        return diaAtual;
    }

    public void resetarMissaoAtual() {
        this.missaoAtual = 0;
    }

    public void avancarMissao() {
        this.missaoAtual++;
    }

    public int totalMissoesDoDia() {
        return divisaoCenarios[diaAtual][0]
                + divisaoCenarios[diaAtual][1]
                + divisaoCenarios[diaAtual][2];
    }

    public int[][] getDivisaoCenarios() {
        return this.divisaoCenarios;
    }
}