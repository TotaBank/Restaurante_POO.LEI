package lei.grupo4.java;

import java.util.Map;

public abstract class Pagamento {
    float mValor;
    public Pagamento(float pValor){
        this.mValor = pValor;
    }

    public abstract BoolAndFloat pagar();
}
