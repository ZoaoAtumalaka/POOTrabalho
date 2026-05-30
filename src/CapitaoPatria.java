class CapitaoPatria extends Herois {
    private int sanidade;

    // METODO CONSTRUTOR
    public CapitaoPatria() {
        super("Capitão Pátria", 10, 10, 10, 10, 10);
        this.sanidade = 99;
    }

    // METODOS ESPECIAIS
    @Override
    public double[] executarMissao() {
        if (this.sanidade > 0) {
            this.sanidade -= 33;
            return super.executarMissao();
        } else {
            double[] erro = {1};
            return erro;
        }

    }

    @Override
    public void reanimar() {
        this.sanidade = 99;
        super.reanimar();
    }
}