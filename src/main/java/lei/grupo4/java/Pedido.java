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
        Pedido.mNumPedidos += 1;
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
    public EstadoPedido obterEstado(){return this.mEstado;}
    public Mesa obterMesa(){return this.mMesa;}
    public int obterId(){return this.mId;}
    public void adicionarItem(PedidoItem pItem){
        this.mEstado = EstadoPedido.REGISTADO;
        this.mListaItems.add(pItem);
    }

    public void removerItem(PedidoItem pItem){
        this.mListaItems.remove(pItem);
    }

    public void atualizarEstado(EstadoPedido pEstado){
        this.mEstado = pEstado;
    }
    public void servir(){
        if (this.mEstado == EstadoPedido.REGISTADO){
            this.mEstado = EstadoPedido.SERVIDO;
        }
        System.out.println("Pedido não está registado/já foi servido");

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
