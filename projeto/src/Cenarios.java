public class Cenarios {
    // Cenario = missão
    // ATRIBUTOS
    private String descricao;
    private int forcaExigida;
    private int velociddadeExigida;
    private int inteligenciaExigida;
    private int defesaExigida;
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

    // METODOS GET

    public String getDescricao() {
        return this.descricao;
    }

    public int[] getAtributos(){
        int[] Atributos = {forcaExigida, velociddadeExigida, inteligenciaExigida, defesaExigida};
        return Atributos;
    }

    public int getQuantidadeMemnbros() {
        return this.quantidadeMemnbros;
    }
}
