import java.io.*;

public class Dados implements Serializable {
    private CapitaoPatria capitaoPatria;
    private TremBala tremBala;
    private ManaSabia manaSabia;
    private LuzEstrela luzEstrela;
    private BlackNoir blackNoir;
    private Profundo profundo;
    private RainhaMaeve rainhaMaeve;
    private Dias dias;

    public Dados(){
        this.capitaoPatria = new CapitaoPatria();
        this.luzEstrela = new LuzEstrela();
        this.blackNoir = new BlackNoir();
        this.profundo = new Profundo();
        this.rainhaMaeve = new RainhaMaeve();
        this.manaSabia = new ManaSabia();
        this.tremBala = new TremBala();
        this.dias = new Dias();
    }

    public void salvar(){
        try {
            persistir();
        }catch (IOException e){
            System.out.println("Erro ao salvar o jogo.");
        }
    }

    public Dados recuperar() {
        try (FileInputStream diretorio = new FileInputStream("save.ser");
             ObjectInputStream entrada = new ObjectInputStream(diretorio)) {

            return (Dados) entrada.readObject();

        } catch (FileNotFoundException e) {
            System.out.println("Arquivo de save não encontrado. Criando novos dados...");
            return new Dados();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro na leitura do save. Criando novos dados...");
            return new Dados();
        }
    }

    private void persistir() throws IOException {
        FileOutputStream diretorio = new FileOutputStream("save.ser");
        ObjectOutputStream saida = new ObjectOutputStream(diretorio);
        saida.writeObject(this);
        saida.close();
        diretorio.close();
    }
}