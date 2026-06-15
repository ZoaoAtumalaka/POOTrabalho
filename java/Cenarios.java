import java.io.Serializable;

public class Cenarios implements Serializable {
    // Cenario = missão

    // ATRIBUTOS
    private String descricao;
    private double forcaExigida;
    private double velociddadeExigida;
    private double inteligenciaExigida;
    private double defesaExigida;
    private int tempoDeIda;
    private int tempoDeExecucao;
    private int tempoDeVolta;
    private int quantidadeMembros;
    private int xpDado;

    // METODO CONSTRUTOR
    public Cenarios(String descricao, int forcaExigida, int velociddadeExigida, int inteligenciaExigida,
                    int defesaExigida, int tempoDeIda, int tempoDeExecucao, int tempoDeVolta, int quantidadeMembros, int xpDado){
        this.descricao = descricao;
        this.forcaExigida = forcaExigida;
        this.velociddadeExigida = velociddadeExigida;
        this.inteligenciaExigida = inteligenciaExigida;
        this.defesaExigida = defesaExigida;
        this.tempoDeIda = tempoDeIda;
        this.tempoDeExecucao = tempoDeExecucao;
        this.tempoDeVolta = tempoDeVolta;
        this.quantidadeMembros = quantidadeMembros;
        this.xpDado = xpDado;
    }

    // ESPECIAIS

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

    public int getQuantidadeMembros() {
        return this.quantidadeMembros;
    }

    public int getXpDado(){
        return this.xpDado;
    }

    public int getTempoDeIda(){ return this.tempoDeIda; };

    public int getTempoDeExecucao(){ return this.tempoDeExecucao; };

    public int getTempoDeVolta(){ return this.tempoDeVolta; };
    public int getQuantidadeDeMembros(){return this.quantidadeMembros;}

    public String getAtributosNecessarios(){
        String[] nomes = {"Força", "Velocidade", "Inteligência", "Defesa"};
        double[] valores = this.getAtributos();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < valores.length; i++) {
            if (valores[i] != 0) {
                if (sb.length() > 0) sb.append(", ");
                sb.append(nomes[i]).append(": ").append(String.format("%.1f", valores[i]));
            }
        }
        if (sb.length() == 0) sb.append("Nenhum atributo específico");
        return sb.toString();
    }

}