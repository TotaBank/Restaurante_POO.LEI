package lei.grupo4;

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
    Utilizador empregadoMesa;

    public Pedido(
            Mesa pMesa,
            Utilizador pEmpregadoMesa
    ){
        this.mListaItems = new ArrayList<PedidoItem>();
        Pedido.mNumPedidos =+ 1;
        this.mId = Pedido.mNumPedidos;
        this.mEstado = EstadoPedido.REGISTADO;
        this.mHoraCriacao = LocalDateTime.now();
        this.mMesa = pMesa;
        this.empregadoMesa = pEmpregadoMesa;
    }

    public void adicionarItem(MenuItem pItem, int pQtd){

    }
    public void removerItem(MenuItem pItem){

    }

    public void confirmar(){

    }
    public void atualizarEstado(){

    }
    public void calcularTotal(){

    }


}
