package org.example;

public interface Acoes  {
    abstract public void subirNivel(String atributoParaEvoluir);
    abstract public boolean receberXp(double xpRecebido);
    abstract public void descansar();
    abstract public void verificarDescanso();
    abstract public double[] executarMissao();
    abstract public void matar();
    abstract public void reanimar();
}