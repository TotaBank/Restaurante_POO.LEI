package lei.grupo4.java;

import java.util.Map;

public class PagamentoCartao extends Pagamento{
    public PagamentoCartao(float pValor){
        super(pValor);
        this.mMetodoDePagamento = MetodoDePagamento.CARTAO;
    }
    @Override
    public BoolAndFloat pagar() {
        return new BoolAndFloat(true, 0);
    }

}
