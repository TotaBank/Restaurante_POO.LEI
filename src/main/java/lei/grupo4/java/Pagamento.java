package lei.grupo4.java;

import java.util.Map;

public abstract class Pagamento {
    float mValor;
    MetodoDePagamento mMetodoDePagamento;
    public Pagamento(float pValor){
        this.mValor = pValor;
    }

    public abstract BoolAndFloat pagar();
    public MetodoDePagamento obterMetodoDePagamento(){return this.mMetodoDePagamento;}
}
