package lei.grupo4.java;

public class Fatura {
    private static int mQuantasFaturas;
    private int mId;
    private float mValor;
    private Pedido mPedido;
    public Fatura(Pedido pPedido){
        if (pPedido.obterEstado() != EstadoPedido.PAGO) {
            throw new IllegalStateException(
                    "Pedido ainda não está pago"
            );
        }
        this.mPedido = pPedido;
        Fatura.mQuantasFaturas +=1;
        this.mId = mQuantasFaturas;
        this.mValor = pPedido.calcularTotal();
    }
    public Pedido obterPedido(){return this.mPedido;}
    public float obterPreco(){return this.mValor;}
    public Integer obterId(){return this.mId;}
    @Override
    public String toString(){
        StringBuilder ret = new StringBuilder();
        ret.append(String.format("Fatura: %d\tPedido: %d\tValor: %.2f\n", this.mId, this.mPedido.obterId(), this.mValor));
        for(PedidoItem itemAtual : this.mPedido.mListaItemsServidos){
            ret.append(String.format("%s - %.2f€\n", itemAtual, itemAtual.obterPreco()));
        }

        return ret.toString();
    }
}
