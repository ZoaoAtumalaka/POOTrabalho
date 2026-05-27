import java.util.ArrayList;
import java.util.Random;

public class Dias {
    // DIAS =  fases

        private ArrayList<Cenarios> cenariosFaceis;
        private ArrayList<Cenarios> cenariosMedios;
        private ArrayList<Cenarios> cenariosDificeis;

        private int quantidadeMissoesExecutadas;
        private int[] quantidadeMissoesPorDia;
        private Equipes equipeEnviada;
        private int culpadoFalha;
        private int missaoAtual;

        // CONSTRUTOR
        public Dias(ArrayList<Cenarios> cenariosFaceis, ArrayList<Cenarios> cenariosMedios, ArrayList<Cenarios> cenariosDificeis, int quantidadeMissoesExecutadas, int[] quantidadeMissoesPorDia, Equipes equipeEnviada, int missaoAtual){
            this.cenariosFaceis = cenariosFaceis;
            this.cenariosMedios = cenariosMedios;
            this.cenariosDificeis = cenariosDificeis;
            this.quantidadeMissoesExecutadas = quantidadeMissoesExecutadas;
            this.quantidadeMissoesPorDia = quantidadeMissoesPorDia;
            //this.equipeEnviada = equipeEnviada;
            this.culpadoFalha = 0;
            this.missaoAtual = 0;
        }

        // ESPECIAIS
        public void passarDia(){
            for (int i =0; i < cenariosFaceis.size(); i++){
                cenariosFaceis.get(i).aumentardificuldade();
            }
            for (int i =0; i < cenariosMedios.size(); i++){
                cenariosMedios.get(i).aumentardificuldade();
            }
            for (int i =0; i < cenariosDificeis.size(); i++){
                cenariosDificeis.get(i).aumentardificuldade();
            }

        }

        public void darXp(int xp) {
            for (int i = 0; i < equipeEnviada.getGrupo().size(); i++) {
                equipeEnviada.getGrupo().get(i).darXp(xp);
            }
        }

        public void executarMissao(Cenarios cenario) throws InterruptedException {

            // TIMER IRADISSIMO ROCK N ROLL!!!!
            Thread.sleep(30000);

                    if (equipeEnviada.getGrupo().isEmpty()) {
                        this.culpadoFalha = 0;
                        return;
                        // avisar o front aqui (ainda nao sei como)
                    } else {

                        // TIMER DE IDA MANEIRO!!!!!
                        Thread.sleep(cenario.getTempoDeIda() * 1000L);

                                // TIMER DE EXECUCAO LEGAL!!!!
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
                                            double mediaAtributosC = somaAtributosC / quantidadeAtributosC;

                                            // MÉDIA DOS ATRIBUTOS DOS HERÓIS
                                            int somaAtributosH = 0;
                                            int quantidadeAtributosH = 0;
                                            for (int i = 0; i < resultado.length; i++) {
                                                if (resultado[i] != 0) {
                                                    somaAtributosH += resultado[i];
                                                    quantidadeAtributosH += 1;
                                                }
                                            }
                                            double mediaAtributosH = somaAtributosH / quantidadeAtributosH;

                                            Random random = new Random();
                                            double numRandom = random.nextDouble() * mediaAtributosC;
                                            //ver isso aqui em! KJAKKKK
                                            if(numRandom >= mediaAtributosH && numRandom <= mediaAtributosC){
                                                System.out.println("A Missão foi concluida com Sucesso!");
                                                darXp(50);
                                            } else {
                                                equipeEnviada.falha();
                                                culpadoFalha = 9;
                                            }

                                            // TIMER DE VOLTA
                                            Thread.sleep(cenario.getTempoDeVolta() * 1000L);
                                        }
                    }
        }

        public String motivoFalha(){
            switch(culpadoFalha){
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
                    return "Falha em um teste de Atributo";
                    case 9:
                    return "Falha em um teste de Atributo";
                default:
                    return "Culpado desconhecido";
            }
        }

}