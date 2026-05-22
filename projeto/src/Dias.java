import java.util.ArrayList;

public class Dias {
        private ArrayList<Cenarios> cenariosFaceis;
        private ArrayList<Cenarios> cenariosMedios;
        private ArrayList<Cenarios> cenariosDificeis;
        private int quantidadeMissoesExecutadas;
        private int[] quantidadeMissoesPorDia;
        private Equipes equipeEnviada;
        private int culpadoFalha;
        private int missaoAtual;

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

        public void darXp(){
            return ;
        }


}


         darXp- vai dar um return do xpDado
         executarMissao-vai começar o timer de 30 segundos e a cada segundo verificar o array de equipe enviada se estiver vazia vai setar o culpadoFalha com um numero unico que o front vai entender que foi por falta de heroi enviados, se não estiver vazia vai pra proxima parte/ vai rodar o timer de ida atualizando a cada segundo pelo callback que retorna o horario pro front/ mesma coisa de antes so que pro horario de execução/aqui onde vai ter a divisao de cenarios e a aleatoriedade, vai chamar o metodo executarMissa da equipe, se retornar um array com um unico item falha e retorna false e vai pegar esse culpado e setar em culpadoFalha, se retornar maior vai passar pelo calculo de sucessao se passar retorna true/ se o array vier com um unico numero ou der false vai chamar matar do objeto de equipe;
         motivoFalha- vai retornar culpadoFalha;