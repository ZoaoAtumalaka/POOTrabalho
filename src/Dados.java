import java.io.*;

public class Dados {
    private CapitaoPatria capitaoPatria;
    private TremBala tremBala;
    private ManaSabia manaSabia;
    private LuzEstrela luzEstrela;
    private BlackNoir blackNoir;
    private Profundo profundo;
    private RainhaMaeve rainhaMaeve;
    private Dias dias;

    public Dados(){
//        esses erros tão dando pq o jão nao commitou ainda
//        this.capitaoPatria=new CapitaoPatria();
//        this.luzEstrela=new LuzEstrela();
//        this.blackNoir=new BlackNoir();
//        this.profundo=new Profundo();
//        this.rainhaMaeve=new RainhaMaeve();
//        this.manaSabia=new ManaSabia();
//        this.tremBala=new TremBala();
//        this.dias=new Dias();
    }
    public void salvar(){
        try {
            persistir();
        }catch (IOException e){
            System.out.println("Erro");
        }
    }
    private Dados recuperar() throws IOException{
        try (FileInputStream diretorio = new FileInputStream("save.ser");
             ObjectInputStream entrada = new ObjectInputStream(diretorio)) {

//                tem que arrumar o try
//            return (Dados) entrada.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de save não encontrado. Criando novos dados...");
            return new Dados();
        }
        return new Dados();
    }
    private void persistir() throws IOException {
//        logica de persistencia em arquivo
        FileOutputStream diretorio= new FileOutputStream("save.ser");
        ObjectOutputStream saida= new ObjectOutputStream(diretorio);
        saida.writeObject(this);
        saida.close();
        diretorio.close();
    }
}
