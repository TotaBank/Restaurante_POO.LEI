package lei.grupo4.java;

public class AlertaCozinha extends Alerta{
    public AlertaCozinha(){
        super("SISTEMA COZINHA");
    }
    @Override
    public void receberNotificacao(String mensagem) {
        log(mensagem);
    }
}
