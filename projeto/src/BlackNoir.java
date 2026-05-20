public class BlackNoir extends Herois {
    private int alucinacao;

    public BlackNoir (String nome, int forca, int velocidade, int inteligencia, int defesa, int tempoDescanso, int alucinacao){
        super("Black noir", 10,10,10,10,10);
        this.alucinacao = 0;
    }

    @Override
    public int[] executarMissao() {
        if (alucinacao > 0){
            this.alucinacao += 15;
            return super.executarMissao();
        }
        else {
            int[] atributos = {4};
            return  atributos;
        }
    }

    @Override
    public void reanimar() {
        this.alucinacao = 0;
        super.reanimar();
    }
}
