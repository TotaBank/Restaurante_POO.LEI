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

        List<StockItem> stock = new ArrayList<>();
        stock.add(hamburger);
        stock.add(queijo);
        stock.add(pao);

        Map<StockItem, Integer> ingredientesHamburgerQueijo = new HashMap<>();
        ingredientesHamburgerQueijo.put(hamburger, 1);
        ingredientesHamburgerQueijo.put(queijo, 1);
        ingredientesHamburgerQueijo.put(pao, 1);

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
        List<MenuItem> menu = new ArrayList<>();
        menu.add(hamburgerDeQuejo);
        ServicoDeRestaurante sr = new ServicoDeRestaurante(listaDeSalas, menu, stock);
        sr.criarReserva("João", 4);
        sr.criarReserva("Tomás", 4);
        System.out.println(sr.obterReservas());
        System.out.println(sr.criarPedido("Tomás", 4));
        Pedido ultimoPedido = sr.obterPedidos().getLast();
        PedidoItem hamburgerDeQueijoSemQueijo = new PedidoItem(hamburgerDeQuejo, "Sem queijo, bem passado");
        hamburgerDeQueijoSemQueijo.removerIngrediente(queijo);
        System.out.println(hamburgerDeQueijoSemQueijo.mostrarDetalhes());
        ultimoPedido.adicionarItem(hamburgerDeQueijoSemQueijo);
        System.out.println(ultimoPedido);
        ultimoPedido.servir();
        System.out.println(ultimoPedido);
        sr.fecharPedido(ultimoPedido.obterMesa());
        Fatura ultimaFatura = sr.obterFaturas().getLast();
        System.out.println(ultimaFatura);








    }
}

