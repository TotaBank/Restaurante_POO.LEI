package lei.grupo4.java;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Pedido {
    static Integer mNumPedidos;
    Integer mId;
    List<PedidoItem> mListaItems;
    EstadoPedido mEstado;
    LocalDateTime mHoraCriacao;
    Mesa mMesa;

    public Pedido(
            Mesa pMesa
    ){
        this.mListaItems = new ArrayList<PedidoItem>();
        Pedido.mNumPedidos =+ 1;
        this.mId = Pedido.mNumPedidos;
        this.mEstado = EstadoPedido.REGISTADO;
        this.mHoraCriacao = LocalDateTime.now();
        this.mMesa = pMesa;
    }

    public void adicionarItem(PedidoItem pItem){
        this.mListaItems.add(pItem);
    }
    public void removerItem(PedidoItem pItem){
        this.mListaItems.remove(pItem);
    }

    public void confirmar(){
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

    public void iniciarPreparacao(){
        switch(this.mEstado){
            case EstadoPedido.EM_PREPARACAO:{
                String texto = String.format("O pedido está %s", EstadoPedido.EM_PREPARACAO);
                System.out.println(texto);
            }
            case EstadoPedido.PRONTO:
        }
    }
    public void servir(){
        if (this.mEstado == EstadoPedido.EM_PREPARACAO){
            System.out.println("O pedido ainda está a ser preparado");
        }
        this.mEstado = EstadoPedido.SERVIDO;
    }


}
