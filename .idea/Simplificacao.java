// abstract class Herois implements Acoes
//     atributos
//         String nome
//         int forca
//         int velocidade
//         int inteligencia
//         int defesa
//         int xp
//         int xpProximoNivel
//         boolean acordado
//         boolean morto
//         int tempoDeDescanso
//     metodos
//         verificarVida- retorna o que estiver em morto;
//         executarMissao- vai retornar os atributos em array
//         matar- vai setar o morto = true
//         reanimar- vai setar o acordado = true e setar o morto = false;
//         darXp- vai receber por parametro o xp da missao e verificar se e possivel evoluir se evoluir retorna true se não false;
//         evoluirPersona- vai pegar o atributo que o user deseja evoluir e multiplicar por 1.1;
//         descansar- vai chamar um timer com o tempo de descanso e setar o acordado para falso;
//         run- vai chamar descansar

// interface Acoes
//     metodos
//         verificarVida
//         executarMissao
//         matar
//         reanimar
//         darXp
//         evoluirPersona
//         descansar

// class CapitaoPatria extends Herois
//     atributos
//         int sanidade
//     metodos
//         executarMissao- vai verificar o atributo especifico se true retorna o que vier do pai, se false retornar um array com 1 numero;
//         reanimar- vai zerar o atributo especifico e chamar o metodo do pai;

// class Profundo extends Herois
//     atributos
//         int ansiedade
//     metodos
//         executarMissao- vai verificar o atributo especifico se true retorna o que vier do pai, se false retornar um array com 1 numero;
//         reanimar- vai zerar o atributo especifico e chamar o metodo do pai;

// class TremBala extends Herois
//     atributos
//         int estamina
//     metodos
//         executarMissao- vai verificar o atributo especifico se true retorna o que vier do pai, se false retornar um array com 1 numero;
//         reanimar- vai zerar o atributo especifico e chamar o metodo do pai;

// class ManaSabia extends Herois
//     atributos
//         int fadigaMental
//     metodos
//         executarMissao- vai verificar o atributo especifico se true retorna o que vier do pai, se false retornar um array com 1 numero;
//         reanimar- vai zerar o atributo especifico e chamar o metodo do pai;

// class BlackNoir extends Herois
//     atributos
//         int alucinacao
//     metodos
//         executarMissao- vai verificar o atributo especifico se true retorna o que vier do pai, se false retornar um array com 1 numero;
//         reanimar- vai zerar o atributo especifico e chamar o metodo do pai;

// class Equipe
//     atributos
//         ArrayList<Herois> equipe
//     metodos
//         criarEquipe- vai receber por parametro 1 ou infinitos herois e criar o array e equipe;
//         executarMissao- vai chamar executarMissao dos filhos e receber os atributos, se receber um array com 1 item so retorna o mesmo, caso receba um array com mais de 1 vai somar os atributos em um novo array e retornar ele
//         falha- seta todos os herois como mortos;

// class Dias
//     atributos
//         ArrayList<Cenario> cenariosFaceis
//         ArrayList<Cenario> cenariosMedianos
//         ArrayList<Cenario> cenariosDificeis
//         int quantidadeMissoesExecutadas
//         int[] quantidadeDeMissoesPorDia
//         ArrayList<Herois> equipeEnviada
//         int culpadoFalha
//     metodos
//         passarDia-vai multiplicar a dificuldade de todas as missoes em 1.1;
//         darXp- vai dar um return do xpDado
//         executarMissao-vai começar o timer de 30 segundos e a cada segundo verificar o array de equipe enviada se estiver vazia vai setar o culpadoFalha com um numero unico que o front vai entender que foi por falta de heroi enviados, se não estiver vazia vai pra proxima parte/ vai rodar o timer de ida atualizando a cada segundo pelo callback que retorna o horario pro front/ mesma coisa de antes so que pro horario de execução/aqui onde vai ter a divisao de cenarios e a aleatoriedade, vai chamar o metodo executarMissa da equipe, se retornar um array com um unico item falha e retorna false e vai pegar esse culpado e setar em culpadoFalha, se retornar maior vai passar pelo calculo de sucessao se passar retorna true/ se o array vier com um unico numero ou der false vai chamar matar do objeto de equipe;
//         motivoFalha- vai retornar culpadoFalha;

// class Main
//     main-while(vai chamar threadDemissao que vai criar uma thread e começar um tempo aleatorio para criar outro thread, antes de criar o novo thread ele vai verificar se a quantidade de missoes executadas ja não é suficiente se for vai chamar o metodo passarDia e vai mostrar que o player passou de dia)
//     threadDeMissao-vai criar o painel de missao com a descricao atributos e quantidade de herois é nesse painel onde sera atualizado o timer pra encerrar por tempo, vai chamar o run de dias e aguardar o player selecionar quem vai mandar,vai aguardar o run responder se retornar true vai dar o xp da missao pros herois, se der false vai pegar o resultado e mostrar no painel pq a missao falhou, se aglum evoluir vai atualizar o painel pro de evolução, timer de 5 segundos e fecha o painel
//     callbackdetimer- vai ser enviado junto de run no dias, é basicamente um metodo que ira ficar atualizando o timer no swing pra ficar legal do user ver;

