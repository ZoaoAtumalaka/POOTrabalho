import java.io.Serializable;

public class TremBala extends Herois implements Serializable {
    private int coracao;

    public TremBala ( ){
        super("Trem Bala",2,5,1,2,15);
        this.coracao = 100;
    }

    @Override
    public double[] executarMissao() {
        if (coracao > 0){
            this.coracao -= 25;
            return super.executarMissao();
        }

        else {
            double[] erro = {5};
            return erro;
        }
    }

    @Override
    public void reanimar() {
        this.coracao = 100;
        super.reanimar();
    }
}
