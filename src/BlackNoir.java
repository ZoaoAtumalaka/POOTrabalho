public class BlackNoir extends Herois {
    private int alucinacao;

    public BlackNoir (){
        super("Black noir", 10,10,10,10,10);
        this.alucinacao = 100;
    }

    @Override
    public double[] executarMissao() {
        if (alucinacao > 0){
            this.alucinacao -= 15;
            return super.executarMissao();
        }
        else {
            double[] erro = {4};
            return  erro;
        }
    }

    @Override
    public void reanimar() {
        this.alucinacao = 100;
        super.reanimar();
    }
}
