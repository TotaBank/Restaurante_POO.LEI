package lei.grupo4.java;

import java.util.List;

public class Testes {
    public static void main(String[] args){
        List<Mesa> todasAsMesas = Mesa.obterTodasAsMesas();
        System.out.println(todasAsMesas);
        System.out.println(Mesa.obterPorCapacidade(8));
/*
        Pedido pedido1 = new Pedido(1,1);
        MenuItem hamburger;
        MenuItem.podeSerPreparado(hamburger);
            PedidoItem pedidoitem1 = new PedidoItem(hamburger, "sem alface");
            pedido1.adicionarItem(pedidoitem1);
            */ //lógica dos pedidos
    }
}
