public class LuzEstrela extends Herois {
    private int fotoluminescencia;

    // METODO CONSTRUTOR
    public LuzEstrela() {
        this.fotoluminescencia = 100;
        super("Luz Estrela", 10, 10, 10, 10, 10);
    }

    // METODOS ESPECIAIS

    @Override
    public double[] executarMissao() {
        if (this.fotoluminescencia > 0) {
            this.fotoluminescencia -= 20;
            return super.executarMissao();
        } else {
            double[] erro = {2};
            return erro;
        }

    }

    @Override
    public void reanimar() {
        this.fotoluminescencia = 100;
        super.reanimar();
    }
}
