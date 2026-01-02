package lei.grupo4.java;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    static Integer mNumPedidos = 0;
    Integer mId;
    List<PedidoItem> mListaItemsRegistados;
    List<PedidoItem> mListaItemsServidos;
    EstadoPedido mEstado;
    Mesa mMesa;

    private Pedido(
            Mesa pMesa
    ){
        Pedido.mNumPedidos += 1;
        this.mId = Pedido.mNumPedidos;
        this.mEstado = EstadoPedido.REGISTADO;
        this.mMesa = pMesa;
        this.mListaItemsRegistados = new ArrayList<PedidoItem>();
        this.mListaItemsServidos = new ArrayList<PedidoItem>();

    }
    @Override
    public String toString(){
        String ret ="";
        ret+=String.format("Id: %d\tEstado: %s\tMesa: %s\nItems registados: %s\nItems servidos: %s", this.mId, this.mEstado, this.mMesa, this.mListaItemsRegistados, this.mListaItemsServidos);
        return ret;
    }
    public EstadoPedido obterEstado(){return this.mEstado;}
    public Mesa obterMesa(){return this.mMesa;}
    public int obterId(){return this.mId;}
    public List<PedidoItem> obterListaItemsRegistados(){return this.mListaItemsRegistados;}
    public List<PedidoItem> obterListaItemsServidos(){return this.mListaItemsServidos;}

    public void adicionarItem(PedidoItem pItem){
        this.mEstado = EstadoPedido.REGISTADO;
        this.mListaItemsRegistados.add(pItem);
    }

    public void removerItem(PedidoItem pItem){
        this.mListaItemsRegistados.remove(pItem);
    }

    public void atualizarEstado(EstadoPedido pEstado){
        this.mEstado = pEstado;
    }

    public void servir(){
        if (this.mEstado == EstadoPedido.REGISTADO){
            this.mEstado = EstadoPedido.SERVIDO;
            for(PedidoItem itemPedido : this.mListaItemsRegistados){
                this.mListaItemsServidos.add(itemPedido);
            }
            this.mListaItemsRegistados.clear();
        } else{
            throw new IllegalStateException("Pedido não está registado/já foi servido");
        }

    }

    public Float calcularTotal(){
        float retTotal = 0.0F;
        for(PedidoItem item : this.mListaItemsServidos){
            float precoItem = item.obterPreco();
            retTotal += precoItem;
        }
        return retTotal;
    }

    public static Pedido criarPedido(Mesa pMesa){
        Pedido ret = new Pedido(pMesa);
        pMesa.abrirPedido(ret);
        return ret;
    }
    public boolean pagar(){
        if (!(this.mEstado == EstadoPedido.SERVIDO)){
            System.out.println("O pedido ainda não foi servido");
            return false;
        }
        this.mMesa.fecharPedido();
        this.mEstado = EstadoPedido.PAGO;
        return true;
    }



}
