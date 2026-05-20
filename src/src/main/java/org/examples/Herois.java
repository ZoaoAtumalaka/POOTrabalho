package org.examples;

public abstract class Herois implements Acoes{
    private double forca;
    private double velocidade;
    private double inteligencia;
    private double defesa;
    private String status;
    private double xp;
    private int turnosDeDescanso;
    private int turnosDescansados;
    private double xpParaEvoluir;
    public Herois(double forca, double velocidade, double inteligencia, double defesa, int turnosDeDescanso, double xpParaEvoluir){
        this.forca=forca;
        this.velocidade=velocidade;
        this.inteligencia=inteligencia;
        this.defesa=defesa;
        this.status="Vivo";
        this.xp=0;
        this.turnosDeDescanso=turnosDeDescanso;
        this.turnosDescansados=0;
        this.xpParaEvoluir=xpParaEvoluir;
    }
    @Override
    public void subirNivel(String atributoParaEvoluir){
        if (atributoParaEvoluir.equals("Forca")){
            this.forca*=1.1;
        }else if (atributoParaEvoluir.equals("Velocidade")){
            this.velocidade*=1.1;
        }else if (atributoParaEvoluir.equals("Defesa")){
            this.defesa*=1.1;
        }else if (atributoParaEvoluir.equals("Inteligencia")){
            this.inteligencia*=1.1;
        }else{
            System.out.println("Atributo n~ao existe");
        }
        this.xp=0;
    }
    @Override
    public void reanimar(){
        this.status="Vivo";
        this.turnosDescansados=0;
    }
    @Override
    public boolean receberXp(double xpRecebido){
        this.xp+=xpRecebido;
        if (this.xp>=this.xpParaEvoluir){
            return true;
        }else {
            return false;
        }
    }
    @Override
    public void descansar(){
        this.status="Descansando";
    }
    @Override
    public void verificarDescanso(){
        if (this.turnosDescansados>=this.turnosDeDescanso){
            this.status="Vivo";
        }else {
            this.turnosDescansados++;
        }
    }
    @Override
    public double[] executarMissao(){
        double[] retorno= {this.forca,this.velocidade,this.inteligencia,this.defesa};
        return retorno;
    }
    @Override
    public void matar(){
        this.status="Morto";
    }

}