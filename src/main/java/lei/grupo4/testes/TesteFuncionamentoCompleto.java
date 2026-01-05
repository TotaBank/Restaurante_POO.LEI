package lei.grupo4.testes;

import lei.grupo4.java.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TesteFuncionamentoCompleto {
    public static void main(String[] args){
        StockItem hamburger = new StockItem("Hamburger", 10, 5);
        StockItem queijo = new StockItem("Queijo", 10, 5);
        StockItem pao = new StockItem("Pão", 10, 5);
        StockItem garrafaAgua = new StockItem("Garrafa de Água", 10, 5);
        StockItem pickles = new StockItem("Pickles", 10, 5);
        List<StockItem> stock = new ArrayList<>();
        stock.add(hamburger);
        stock.add(queijo);
        stock.add(pao);
        stock.add(pickles);

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
        sr.adicionarIngredientesAItemDePedido(hamburgerQueijoBemPassado,queijo, 1);
        sr.adicionarIngredientesAItemDePedido(hamburgerQueijoBemPassado, pickles, 1);
        sr.adicionarItemAPedido(ultimoPedido, hamburgerQueijoBemPassado);
        System.out.println("*".repeat(30));
        System.out.println(ultimoPedido);
        System.out.println(queijo.mostrarDetalhes());
        sr.servirPedido(ultimoPedido);
        System.out.println("*".repeat(30));
        System.out.println(ultimoPedido);
        System.out.println(hamburger.mostrarDetalhes());
        System.out.println(queijo.mostrarDetalhes());
        PedidoItem garrafaAguaFria = sr.criarItemDePedido(garrafaDeAgua, "Fria");
        sr.adicionarItemAPedido(ultimoPedido, garrafaAguaFria );
        sr.removerItemAPedido(ultimoPedido, garrafaAguaFria);
        PedidoItem hamburger5QueijoBemPassado = sr.criarItemDePedido(hamburgerDeQuejo, "bem passado");
        hamburgerQueijoBemPassado.adicionarIngredientes(queijo, 5);
        sr.adicionarItemAPedido(ultimoPedido, hamburgerQueijoBemPassado);
        System.out.println(ultimoPedido);
        sr.servirPedido(ultimoPedido);
        System.out.println(ultimoPedido);
        System.out.println("*".repeat(30));
        PagamentoDinheiro cartaoDoTomas = new PagamentoDinheiro(ultimoPedido.calcularTotal(), 22);
        Fatura ultimaFatura = sr.fecharMesa(mesa1, cartaoDoTomas);
        sr.imprimirFatura(sr.fecharMesa(mesa1, cartaoDoTomas));
        System.out.println(ultimaFatura);
        System.out.println(mesa1.obterEstado());






    }
}

