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
    @Override
    public String toString(){
        String ret = "";
        ret += String.format("Fatura: %d\tPedido: %d\tValor: %.2f", this.mId, this.mPedido.obterId(), this.mValor);
        return ret;
    }
}
