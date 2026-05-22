public class RainhaMaeve extends Herois {
    private int autoEstima;

    // METODO CONSTRUTOR
    public RainhaMaeve() {
        this.autoEstima = 100;
        super("Rainha Maeve", 10, 10, 10, 10, 10);
    }

    // METODOS ESPECIAIS

    @Override
    public int[] executarMissao() {
        if (this.autoEstima > 0) {
            this.autoEstima -= 20;
            return super.executarMissao();
        } else {
            int[] erro = {3};
            return erro;
        }

    }

    @Override
    public void reanimar() {
        this.autoEstima = 100;
        super.reanimar();
    }
}
