import java.io.Serializable;

public class RainhaMaeve extends Herois implements Serializable {
    private int autoEstima;

    // METODO CONSTRUTOR
    public RainhaMaeve() {
        super("Rainha Maeve", 5, 1, 2, 2, 15);
        this.autoEstima = 100;
    }

    // METODOS ESPECIAIS

    @Override
    public double[] executarMissao() {
        if (this.autoEstima > 0) {
            this.autoEstima -= 20;
            return super.executarMissao();
        } else {
            double[] erro = {3};
            return erro;
        }

    }

    @Override
    public void reanimar() {
        this.autoEstima = 100;
        super.reanimar();
    }
}
