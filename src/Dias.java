import java.util.ArrayList;
import java.util.Random;

public class Dias {
    // DIAS = fases
    private ArrayList<Cenarios> cenariosFaceis;
    private ArrayList<Cenarios> cenariosMedios;
    private ArrayList<Cenarios> cenariosDificeis;
    private int quantidadeMissoesExecutadas;
    private int[] quantidadeMissoesPorDia;
    private Equipes equipeEnviada;
    private int culpadoFalha;
    private int missaoAtual;

    // CONSTRUTOR
    public Dias() {
        this.cenariosFaceis = new ArrayList<Cenarios>();
        this.cenariosMedios = new ArrayList<Cenarios>();
        this.cenariosDificeis = new ArrayList<Cenarios>();
        this.culpadoFalha = 0;
        this.missaoAtual = 0;

// CENARIOS FACEIS
        this.cenariosFaceis.add(new Cenarios("Resgatar gatinho cibernético na árvore", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosFaceis.add(new Cenarios("Ajudar Velho Pucrson: O sumiço da dentadura", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosFaceis.add(new Cenarios("Recuperar Wi-Fi do bar local", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosFaceis.add(new Cenarios("Espantar pombos mutantes da praça", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosFaceis.add(new Cenarios("Entregar pizza fria na casa branca", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosFaceis.add(new Cenarios("Desativar despertador barulhento da senhora de idade ", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosFaceis.add(new Cenarios("Encontrar os óculos do prefeito", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosFaceis.add(new Cenarios("Limpar gosma verde dos esgotos", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosFaceis.add(new Cenarios("Capturar animais do zoologico que sumiram", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosFaceis.add(new Cenarios("Escoltar carrinho de feira da vovó", 5, 5, 5, 5, 10, 5, 10, 1, 10));

// CENARIOS MÉDIOS
        this.cenariosMedios.add(new Cenarios("Conter surto de IA em uma empresa", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosMedios.add(new Cenarios("Ajudar Velho Pucrson: A conspiração do asilo", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosMedios.add(new Cenarios("Para briga em bar de vilões", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosMedios.add(new Cenarios("Escoltar cantor famoso dentro do homelander music show", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosMedios.add(new Cenarios("Rastrear contrabandistas de composto V falso", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosMedios.add(new Cenarios("Interromper ritual de ceita", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosMedios.add(new Cenarios("Impedir the boys dentro do hospicio", 5, 5, 5, 5, 10, 5, 10, 1, 10));

// CENARIOS DIFÍCEIS
        this.cenariosDificeis.add(new Cenarios("Desarmar ogiva nuclear", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosDificeis.add(new Cenarios("Ajudar Velho Pucrson: O confronto final no .env", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosDificeis.add(new Cenarios("Derrotar o Bruto louco com composto V", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosDificeis.add(new Cenarios("Impedir guerra entre supers", 5, 5, 5, 5, 10, 5, 10, 1, 10));
        this.cenariosDificeis.add(new Cenarios("Impedir vulcao em erupção", 5, 5, 5, 5, 10, 5, 10, 1, 10));
    }

    // ESPECIAIS
    public void passarDia(ArrayList<Cenarios> cenariosFaceis, ArrayList<Cenarios> cenariosMedios, ArrayList<Cenarios> cenariosDificeis) {
        for (int i = 0; i < cenariosFaceis.size(); i++) {
            cenariosFaceis.get(i).aumentardificuldade();
        }
        for (int i = 0; i < cenariosMedios.size(); i++) {
            cenariosMedios.get(i).aumentardificuldade();
        }
        for (int i = 0; i < cenariosDificeis.size(); i++) {
            cenariosDificeis.get(i).aumentardificuldade();
        }
    }

    public void darXp(Equipes equipeEnviada, int xp) {
        for (int i = 0; i < equipeEnviada.getGrupo().size(); i++) {
            equipeEnviada.getGrupo().get(i).darXp(xp);
        }
    }

    public void executarMissao(Cenarios cenario, Equipes equipeEnviada) throws InterruptedException {
        // TIMER IRADISSIMO ROCK N ROLL!!!!
        Thread.sleep(30000);

        if (equipeEnviada.getGrupo().isEmpty()) {
            this.culpadoFalha = 0;
            return;
        } else {
            // TIMER DE IDA MANEIRO!!!!!
            Thread.sleep(cenario.getTempoDeIda() * 1000L);

            // TIMER DE EXECUÇÃO LEGAL!!!!
            Thread.sleep(cenario.getTempoDeExecucao() * 1000L);

            double[] resultado = equipeEnviada.executarMissao();
            if (resultado.length == 1) {
                this.culpadoFalha = (int) resultado[0];
                equipeEnviada.falha();
            } else {
                // MÉDIA DOS ATRIBUTOS DO CENÁRIO
                int somaAtributosC = 0;
                int quantidadeAtributosC = 0;
                double[] atributos = cenario.getAtributos();
                for (int i = 0; i < atributos.length; i++) {
                    if (atributos[i] != 0) {
                        somaAtributosC += atributos[i];
                        quantidadeAtributosC += 1;
                    }
                }

                double mediaAtributosC = (quantidadeAtributosC > 0) ? (double) somaAtributosC / quantidadeAtributosC : 0;

                // MÉDIA DOS ATRIBUTOS DOS HERÓIS
                int somaAtributosH = 0;
                int quantidadeAtributosH = 0;
                for (int i = 0; i < resultado.length; i++) {
                    if (resultado[i] != 0) {
                        somaAtributosH += resultado[i];
                        quantidadeAtributosH += 1;
                    }
                }

                double mediaAtributosH = (quantidadeAtributosH > 0) ? (double) somaAtributosH / quantidadeAtributosH : 0;

                Random random = new Random();
                double numRandom = random.nextDouble() * mediaAtributosC;

                if (numRandom >= mediaAtributosH && numRandom <= mediaAtributosC) {
                    System.out.println("A Missão foi concluída com Sucesso!");
                    darXp(equipeEnviada, cenario.getXpDado()); // CORRIGIDO: Passando equipeEnviada
                } else {
                    equipeEnviada.falha();
                    this.culpadoFalha = 9;
                }

                // TIMER DE VOLTA
                Thread.sleep(cenario.getTempoDeVolta() * 1000L);
            }
        }
    }

    public String motivoFalha() {
        switch (culpadoFalha) {
            case 0:
                return "Falta de herois";
            case 1:
                return "Capitão Pátria falhou";
            case 2:
                return "Luz Estrela falhou";
            case 3:
                return "Rainha Maeve falhou";
            case 4:
                return "Black Noir falhou";
            case 5:
                return "Trem Bala falhou";
            case 6:
                return "Mana Sábia falhou";
            case 7:
                return "Profundo falhou";
            case 8:
            case 9:
                return "Falha em um teste de Atributo";
            default:
                return "Culpado desconhecido";
        }
    }
}