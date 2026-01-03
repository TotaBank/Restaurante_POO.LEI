package lei.grupo4.java;

public enum MetodoDePagamento {
    CARTAO("CARTAO"),
    DINHEIRO("DINHEIRO");

    String mTipo;
    MetodoDePagamento(String pTipo) {
        this.mTipo = pTipo;
    }

    public String toString(){
        return this.mTipo;
    }
}
