class CapitaoPatria extends Herois {
    private int sanidade;

    // METODO CONSTRUTOR
    public CapitaoPatria() {
        this.sanidade = 99;
        super("Capitão Pátria", 10, 10, 10, 10, 10);
    }

    // METODOS ESPECIAIS

    @Override
    public int[] executarMissao() {
        if (this.sanidade > 0) {
            this.sanidade -= 33;
            return super.executarMissao();
        } else {
            int[] erro = {1};
            return erro;
        }

    }

    @Override
    public void reanimar() {
        this.sanidade = 99;
        super.reanimar();
    }
}