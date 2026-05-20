public interface Acoes{
    abstract public boolean verificarVida ();
    abstract public void matar();
    abstract public void reanimar();
    abstract public int[] executarMissao();
    abstract public boolean darXp(int xp);
    abstract public void evoluir(String atributo);
    abstract public void descansar();
}
