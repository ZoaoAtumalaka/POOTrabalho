public class Herois implements Acoes,Runnable{
    private String nome;
    private double forca;
    private double velocidade;
    private double inteligencia;
    private double defesa;
    private int xp;
    private int xpProximoNivel;
    private boolean acordado;
    private boolean morto;
    private int tempoDeDescanso;

    // CONSTRUTOR
    public Herois(String nome, int forca, int velocidade, int inteligencia, int defesa, int tempoDeDescanso) {
        this.nome = nome;
        this.forca = forca;
        this.velocidade = velocidade;
        this.inteligencia = inteligencia;
        this.defesa = defesa;
        this.tempoDeDescanso = tempoDeDescanso;
        this.xp=0;
        this.xpProximoNivel=1000;
        this.acordado=true;
        this.morto=false;
    }

    // ESPECIAIS
    @Override
    public boolean verificarVida(){
        return this.morto;
    }

    @Override
    public double[] executarMissao(){
        double[] atributos= {velocidade,inteligencia,defesa,forca};
        return atributos;
    }
    public double[] getAtributos(){
        double[] atributos={this.forca,this.velocidade,this.inteligencia,this.defesa};
        return atributos;
    }
    @Override
    public void matar(){
        this.morto=true;
    }

    @Override
    public void reanimar(){
        this.morto=false;
        this.acordado=true;
    }

    @Override
    public boolean darXp(int xp){
        if (this.xp>=this.xpProximoNivel){
            this.xp=0;
            this.xpProximoNivel*=1.1;
            return true;
        }else {
            this.xp+=xp;
            return false;
        }
    }

    @Override
    public void evoluir(String atributo){
        if (atributo.equals("Forca")){
            this.forca*=1.1;
        }else if(atributo.equals("Velocidade")){
            this.velocidade*=1.1;
        }else if (atributo.equals("Defesa")){
            this.defesa*=1.1;
        }else if (atributo.equals("Inteligencia")){
            this.inteligencia*=1.1;
        }
    }

    @Override
    public void descansar(){
        try{
            this.acordado=false;
            Thread.sleep(this.tempoDeDescanso);
            this.acordado=true;
        }catch (InterruptedException e){
//            aqui da pra por o objeto de excessao que é minimo pro trabalho
            System.out.println("Erro");
        }
    }

    @Override
    public void run(){
        descansar();
    }

}