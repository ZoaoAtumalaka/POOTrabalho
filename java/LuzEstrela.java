import java.io.Serializable;

public class LuzEstrela extends Herois implements Serializable {
    private int fotoluminescencia;

    // METODO CONSTRUTOR
    public LuzEstrela() {
        super("Luz Estrela", 3, 3, 3, 1, 15);
        this.fotoluminescencia = 100;
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
