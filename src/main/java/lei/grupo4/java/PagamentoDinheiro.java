package lei.grupo4.java;

import java.util.Map;

public class PagamentoDinheiro extends Pagamento{
    float mValorRecebido;
    public PagamentoDinheiro(float pValor, float pValorRecebido){
        super(pValor);
        this.mValorRecebido = pValorRecebido;
    }
    @Override
    public BoolAndFloat pagar() {
        float troco = calcularTroco();
        if (troco >= 0){
            return new BoolAndFloat(true, troco);
        }
        return new BoolAndFloat(false, Math.abs(troco));
    }
    public float calcularTroco(){
        return  this.mValorRecebido-this.mValor;
    }
}
