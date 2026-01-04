package lei.grupo4.java;

public abstract class Alerta implements Observador {
    protected String mNomeSistema;
    public Alerta(String pNomeSistema) {
        this.mNomeSistema = pNomeSistema;
    }
    protected void log(String mensagem) {
        System.out.printf("[%s] LOG: %s%n", this.mNomeSistema, mensagem);
    }


}
