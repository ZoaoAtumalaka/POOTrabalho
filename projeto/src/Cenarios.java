public class Cenarios {
    // Cenario = missão
    // ATRIBUTOS
    private String descricao;
    private double forcaExigida;
    private double velociddadeExigida;
    private double inteligenciaExigida;
    private double defesaExigida;
    private int tempoDeIda;
    private int tempoDeExecucao;
    private int quantidadeMemnbros;
    private int xpDado;

    // METODO CONSTRUTOR
    public Cenarios(String descricao, int forcaExigida, int velociddadeExigida, int inteligenciaExigida,
                    int defesaExigida, int tempoDeIda, int tempoDeExecucao, int quantidadeMemnbros, int xpDado){
        this.descricao = descricao;
        this.forcaExigida = forcaExigida;
        this.velociddadeExigida = velociddadeExigida;
        this.inteligenciaExigida = inteligenciaExigida;
        this.defesaExigida = defesaExigida;
        this.tempoDeIda = tempoDeIda;
        this.tempoDeExecucao = tempoDeExecucao;
        this.quantidadeMemnbros = quantidadeMemnbros;
        this.xpDado = xpDado;
    }

    public void aumentardificuldade(){
        this.forcaExigida = forcaExigida * 1.1;
        this.velociddadeExigida = velociddadeExigida * 1.1;
        this.inteligenciaExigida = inteligenciaExigida * 1.1;
        this.defesaExigida = defesaExigida * 1.1;
    }
    // METODOS GET

    public String getDescricao() {
        return this.descricao;
    }

    public double[] getAtributos(){
        double[] Atributos = {forcaExigida, velociddadeExigida, inteligenciaExigida, defesaExigida};
        return Atributos;
    }

    public int getQuantidadeMemnbros() {
        return this.quantidadeMemnbros;
    }

    public int getXpDado(){
        return this.xpDado;
    }
}

