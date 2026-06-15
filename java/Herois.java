import java.io.Serializable;

abstract public class Herois implements Acoes, Runnable, Serializable {
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
        this.xp = 0;
        this.xpProximoNivel = 1000;
        this.acordado = true;
        this.morto = false;
    }

    // ESPECIAIS
    @Override
    public boolean verificarVida() {
        return this.morto;
    }

    public boolean verificarDescanso() {
        return this.acordado;
    }

    @Override
    public double[] executarMissao() {
        double[] atributos = {velocidade, inteligencia, defesa, forca};
        return atributos;
    }

    public double[] getAtributos() {
        double[] atributos = {this.forca, this.velocidade, this.inteligencia, this.defesa};
        return atributos;
    }

    public String getNome() {
        return this.nome;
    }

    public int getXp() {
        return this.xp;
    }

    public int getXpProximoNivel() {
        return this.xpProximoNivel;
    }

    @Override
    public void matar() {
        this.morto = true;
    }

    @Override
    public void reanimar() {
        this.morto = false;
        this.acordado = true;
    }

    @Override
    public boolean darXp(int xp) {
        this.xp += xp;
        if (this.xp >= this.xpProximoNivel) {
            this.xp = 0;
            this.xpProximoNivel *= 1.1;
            return true; // subiu de nível
        } else {
            return false;
        }
    }

    @Override
    public void evoluir(String atributo) {
        if (atributo.equals("Forca")) {
            this.forca *= 1.1;
        } else if (atributo.equals("Velocidade")) {
            this.velocidade *= 1.1;
        } else if (atributo.equals("Defesa")) {
            this.defesa *= 1.1;
        } else if (atributo.equals("Inteligencia")) {
            this.inteligencia *= 1.1;
        }
    }

    @Override
    public void descansar() {
        try {
            this.acordado = false;
            Thread.sleep(this.tempoDeDescanso * 1000L); // tempoDeDescanso agora é em segundos
            this.acordado = true;
        } catch (InterruptedException e) {
            System.out.println("Descanso interrompido de " + this.nome);
            this.acordado = true;
        }
    }

    public void descansarAsync() {
        if (!this.morto) {
            Thread t = new Thread(this);
            t.setDaemon(true);
            t.start();
        }
    }

    @Override
    public void run() {
        descansar();
    }
}