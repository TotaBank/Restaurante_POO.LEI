package lei.grupo4.java;

public class AlertaStock extends Alerta{
    public AlertaStock(){
        super("SISTEMA STOCK");
    }
    @Override
    public void receberNotificacao(String mensagem) {
        log(mensagem);
    }
}
