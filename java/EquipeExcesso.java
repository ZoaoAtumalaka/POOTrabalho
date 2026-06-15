public class EquipeExcesso extends Exception {
    public EquipeExcesso(){
        super("O numero de membros selecionados execedeu o tamanho maximo para a missão!");
    }
}
