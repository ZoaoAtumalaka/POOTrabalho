public class ManaSabia extends Herois{
    private int fadigaMental;

    public ManaSabia( ){
        super("Mana Sabia",10,10,10,10,10);
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
