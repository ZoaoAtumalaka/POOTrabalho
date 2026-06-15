import java.io.Serializable;
import java.util.ArrayList;

public class Equipes implements Serializable {

    private ArrayList<Herois> grupo;

    // Construtor usado no Main (recebe array de heróis selecionados)
    public Equipes(Herois[] herois) {
        this.grupo = new ArrayList<>();
        for (Herois h : herois) {
            this.grupo.add(h);
        }
    }

    // ESPECIAIS
    public double[] executarMissao(){

        double[] retorno={0,0,0,0};

        for (int i=0;i<grupo.size();i++){
            double[] resultado=grupo.get(i).executarMissao();

            if (resultado.length == 1) {
                return resultado;
            } else {
                retorno[0] += resultado[0];
                retorno[1] += resultado[1];
                retorno[2] += resultado[2];
                retorno[3] += resultado[3];
            }

        }

        return retorno;
    }

    //MATAR TODOS OS MEMBROS DA EQUIPE
    public void falha(){
        for (int i = 0; i < grupo.size(); i++) {
            grupo.get(i).matar();
        }
    }

    public void descansarPorCodigo(int codigo){
        for (Herois heroi : grupo) {
            boolean ehAlvo =
                    (codigo == 1 && heroi instanceof CapitaoPatria) ||
                            (codigo == 2 && heroi instanceof LuzEstrela) ||
                            (codigo == 3 && heroi instanceof RainhaMaeve) ||
                            (codigo == 4 && heroi instanceof BlackNoir) ||
                            (codigo == 5 && heroi instanceof TremBala) ||
                            (codigo == 6 && heroi instanceof ManaSabia) ||
                            (codigo == 7 && heroi instanceof Profundo);

            if (ehAlvo) {
                heroi.descansar();
                break;
            }
        }
    }

    public ArrayList<Herois> getGrupo() {
        return grupo;
    }
}