package lei.grupo4.java;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    static Integer mNumPedidos;
    Integer mId;
    List<PedidoItem> mListaItems;
    EstadoPedido mEstado;
    Mesa mMesa;

    private Pedido(
            Mesa pMesa
    ){
        Pedido.mNumPedidos =+ 1;
        this.mId = Pedido.mNumPedidos;
        this.mEstado = EstadoPedido.REGISTADO;
        this.mMesa = pMesa;
        this.mListaItems = new ArrayList<PedidoItem>();
    }
    @Override
    public String toString(){
        String ret ="";
        ret+=String.format("Id: %d\tEstado: %s\tMesa: %s\nLista de items: %s", this.mId, this.mEstado, this.mMesa, this.mListaItems);
        return ret;
    }

    public void adicionarItem(PedidoItem pItem){
        this.mListaItems.add(pItem);
    }

    public void removerItem(PedidoItem pItem){
        this.mListaItems.remove(pItem);
    }

    public void atualizarEstado(EstadoPedido pEstado){
        this.mEstado = pEstado;
    }

    public Float calcularTotal(){
        float retTotal = 0.0F;
        for(PedidoItem item : this.mListaItems){
            float precoItem = item.mMenuItem.obterPreco();
            retTotal += precoItem;
        }
        return retTotal;
    }

    public static Pedido criarPedido(Mesa pMesa){
        Pedido ret = new Pedido(pMesa);
        pMesa.abrirPedido(ret);
        return ret;
    }



}
