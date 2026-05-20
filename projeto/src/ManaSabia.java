public class ManaSabia extends Herois{
    private int fadigaMental;

    public ManaSabia(String nome, int forca, int velocidade, int inteligencia, int defesa, int tempoDescanso, int fadigaMental){
        super("Mana Sabia",10,10,10,10,10);
        this.fadigaMental = 0;
    }

    @Override
    public int[] executarMissao() {
        if (fadigaMental < 100){
            this.fadigaMental -= 25;
            return super.executarMissao();
        }
        else {
            int[] atributos = {6};
            return atributos;
        }
    }

    @Override
    public void reanimar(){
        this.fadigaMental = 0;
        super.reanimar();
    }
}
