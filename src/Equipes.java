import java.util.ArrayList;

public class Equipes {

    private ArrayList<Herois> grupo;

    // CONSTRUTOR
    public Equipes(Herois... herois){
        grupo= new ArrayList<>();
        for (int i = 0; i < herois.length; i++) {
            grupo.add(herois[i]);
        }
    }

    // ESPECIAIS
    public double[] executarMissao(){

        double[] retorno={0,0,0,0};

        for (int i=0;i<grupo.size();i++){
            double[] resultado=grupo.get(i).executarMissao();

            if (resultado.length == 1){
                retorno[0]+=resultado[0];
                retorno[1]+=resultado[1];
                retorno[2]+=resultado[2];
                retorno[3]+=resultado[3];
            }else {
                return resultado;
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

    public ArrayList<Herois> getGrupo() {
        return grupo;
    }
}
