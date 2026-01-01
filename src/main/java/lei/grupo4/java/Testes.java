package lei.grupo4.java;

import java.util.HashMap;
import java.util.Map;

public class Testes {
    public static void main(String[] args){
        Stock hamburger = new Stock("Hamburger", 10, 5);
        Stock queijo = new Stock("Queijo", 10, 5);
        Stock pao = new Stock("Pão", 10, 5);

        Map<Stock, Integer> ingredientesHamburgerQueijo = new HashMap<>();
        ingredientesHamburgerQueijo.put(hamburger, 1);
        ingredientesHamburgerQueijo.put(queijo, 1);
        ingredientesHamburgerQueijo.put(pao, 1);

        Sala sala1 = new Sala("Sala 1");
        Mesa mesa1 = new Mesa(4);
        Mesa mesa2 = new Mesa(6);
        sala1.adicionarMesa(mesa1);
        sala1.adicionarMesa(mesa2);

        MenuItem hamburgerDeQuejo = new MenuItem(
                "Hamburger de Queijo",
                10.00F,
                true,
                ingredientesHamburgerQueijo
                );

        //gestao de restaurante, classe a ser criada
        System.out.println("-".repeat(30));
        System.out.println(hamburger.mostrarDetalhes());
        System.out.println("-".repeat(30));
        System.out.println(hamburgerDeQuejo.mostrarDetalhes());
        System.out.println("-".repeat(30));
        System.out.println(sala1);
        Mesa mesaParaPedido = sala1.procurarMesaLivre(4);
        //temporario
        /*
        if(mesaParaPedido != null){
            Pedido pedido1 = Pedido.criarPedido(mesaParaPedido);
            System.out.println(pedido1);
        }*/
        System.out.println("-".repeat(30));
        System.out.println(mesaParaPedido.mostrarDetalhes());
        Pedido pedido1 = Pedido.criarPedido(mesaParaPedido);
        System.out.println("-".repeat(30));
        System.out.println(pedido1);
        PedidoItem pedirHamburgerDeQuejo = new PedidoItem(hamburgerDeQuejo, "bem passado");
        pedirHamburgerDeQuejo.adicionarIngredientes(hamburger,1);
        System.out.println("-".repeat(30));
        System.out.println("------Adicionei um hamburger extra------");
        System.out.println(pedirHamburgerDeQuejo.mostrarDetalhes());
        pedido1.adicionarItem(pedirHamburgerDeQuejo);
        System.out.println("-".repeat(30));
        System.out.println(pedido1);








    }
}

