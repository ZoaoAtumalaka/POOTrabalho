import java.io.Serializable;

public class ManaSabia extends Herois implements Serializable {
    private int fadigaMental;

    public ManaSabia( ){
        super("Mana Sabia",1,2,5,2,60);
        this.fadigaMental = 0;
    }

    @Override
    public double[] executarMissao() {
        if (fadigaMental < 100){
            this.fadigaMental += 25;
            return super.executarMissao();
        }
        else {
            double[] erro = {6};
            return erro;
        }
    }

    @Override
    public void reanimar(){
        this.fadigaMental = 0;
        super.reanimar();
    }
}
