public class Profundo extends Herois{
    private int ansiedade;

    public Profundo (String nome, int forca, int velocidade, int inteligencia, int defesa, int tempoDescanso, int sanidade){
        super("Profundo",10,10,10,10,10);
        this.ansiedade = 0;
    }

    @Override
    public int[] executarMissao() {
        if (ansiedade < 100){
            this.ansiedade -= 25;
            return super.executarMissao();
        }
        else {
            int[] erro = {6};
            return erro;
        }
    }

    @Override
    public void reanimar() {
        this.ansiedade = 100;
        super.reanimar();
    }
}
