package lei.grupo4.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Testes {
    public static void main(String[] args){
        StockItem hamburger = new StockItem("Hamburger", 10, 5);
        StockItem queijo = new StockItem("Queijo", 10, 5);
        StockItem pao = new StockItem("Pão", 10, 5);
        StockItem garrafaAgua = new StockItem("Garrafa de Água", 10, 5);

        List<StockItem> stock = new ArrayList<>();
        stock.add(hamburger);
        stock.add(queijo);
        stock.add(pao);

        Map<StockItem, Integer> ingredientesHamburgerQueijo = new HashMap<>();
        ingredientesHamburgerQueijo.put(hamburger, 1);
        ingredientesHamburgerQueijo.put(queijo, 1);
        ingredientesHamburgerQueijo.put(pao, 1);

        Map<StockItem, Integer> ingredientesGarrafaAgua = new HashMap<>();
        ingredientesGarrafaAgua.put(garrafaAgua, 1);

        List<Sala> listaDeSalas = new ArrayList<>();
        Sala sala1 = new Sala("Sala 1");
        listaDeSalas.add(sala1);
        Mesa mesa1 = new Mesa(4);
        Mesa mesa2 = new Mesa(4);
        sala1.adicionarMesa(mesa1);
        sala1.adicionarMesa(mesa2);

        MenuItem hamburgerDeQuejo = new MenuItem(
                "Hamburger de Queijo",
                10.00F,
                true,
                ingredientesHamburgerQueijo
                );
        MenuItem garrafaDeAgua = new MenuItem("Garrafa de Água", 2.00F, true, ingredientesGarrafaAgua);
        List<MenuItem> menu = new ArrayList<>();
        menu.add(hamburgerDeQuejo);
        ServicoDeRestaurante sr = new ServicoDeRestaurante(listaDeSalas, menu, stock);

        sr.criarReserva("Tomás", 4);
        Reserva ultimaReserva = sr.obterReservas().getLast();
        System.out.println(ultimaReserva);

        sr.criarPedido(ultimaReserva);
        Pedido ultimoPedido = sr.obterPedidos().getLast();

        PedidoItem hamburgerQueijoBemPassado = sr.criarItemDePedido(hamburgerDeQuejo, "bem passado");
        hamburgerQueijoBemPassado.adicionarIngredientes(queijo, 1);
        sr.adicionarItemAPedido(ultimoPedido, hamburgerQueijoBemPassado);
        System.out.println(ultimoPedido);
        sr.servirPedido(ultimoPedido);
        System.out.println(ultimoPedido);
        sr.adicionarItemAPedido(ultimoPedido, sr.criarItemDePedido(garrafaDeAgua, "Fria") );
        System.out.println(ultimoPedido);
        sr.servirPedido(ultimoPedido);
        System.out.println(ultimoPedido);
        System.out.println("*".repeat(30));
        System.out.println(sr.fecharMesa(mesa1));






    }
}

