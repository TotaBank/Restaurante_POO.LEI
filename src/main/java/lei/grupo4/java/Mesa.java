package lei.grupo4.java;

import java.util.*;


class CompararPorCapacidade implements Comparator<Mesa>{
    @Override
    public int compare(Mesa o1, Mesa o2) {
        return o1.mCapacidade.compareTo(o2.mCapacidade);
    }
}
public class Mesa {
    private static Integer mQuantMesas = 0;
    Integer mId;
    Integer mCapacidade;
    EstadoMesa mEstado;
    Pedido mPedidoAtual;
    public Mesa(
            int pCapacidade
    ){
        Mesa.mQuantMesas += 1;
        this.mId = mQuantMesas;
        this.mCapacidade = pCapacidade;
        this.mEstado = EstadoMesa.LIVRE;
        this.mPedidoAtual = null;
    }
    public String mostrarDetalhes(){
        String ret ="";
        ret+=String.format("Mesa: %d\tCapacidade: %d\tEstado: %s\tPedido Atual:", this.mId, this.mCapacidade, this.mEstado, this.mPedidoAtual);
        return ret;
    }
    @Override
    public String toString(){
        return String.format("%d", this.mId);
    }

    public int obterId(){
        return this.mId;
    }

    public int obterCapacidade(){
        return this.mCapacidade;
    }

    public EstadoMesa obterEstado(){
        return this.mEstado;
    }

    public boolean livre(){
        return this.mEstado == EstadoMesa.LIVRE;
    }
    public boolean ocupada(){return this.mEstado == EstadoMesa.OCUPADA;}
    public boolean reservada(){return this.mEstado == EstadoMesa.RESERVADA;}

    public Pedido obterPedidoAtual(){
        return this.mPedidoAtual;
    }

    public void reservar(){
        if(!(this.mEstado == EstadoMesa.LIVRE)){
            System.out.println(String.format("A mesa %d não está livre.", this.mId));
        }
        this.mEstado = EstadoMesa.RESERVADA;
    }

    public void fecharPedido(){
        this.mEstado = EstadoMesa.LIVRE;
        this.mPedidoAtual = null;
    }

    public void abrirPedido(Pedido pPedido){
        this.mEstado = EstadoMesa.OCUPADA;
        this.mPedidoAtual = pPedido;
    }



}
