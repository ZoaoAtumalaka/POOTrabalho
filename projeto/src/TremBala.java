public class TremBala extends Herois{
    private int coracao;

    public TremBala (String nome, int forca, int velocidade, int inteligencia, int defesa, int tempoDescanso, int coracao){
        super("Trem Bala",10,10,10,10,10);
        this.coracao = 100;
    }

    @Override
    public int[] executarMissao() {
        if (coracao > 0){
            this.coracao -= 25;
            return super.executarMissao();
        }

        else {
            int[] atributos = {5};
            return atributos;
        }
    }

    @Override
    public void reanimar() {
        this.coracao = 100;
        super.reanimar();
    }
}
