package lei.grupo4.testes;

import lei.grupo4.java.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TesteReservaMesaErro {
    public static void main(String[] args){
        //Definir Stock
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
        //definir receitas
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
        menu.add(garrafaDeAgua);

        ServicoDeRestaurante sr = new ServicoDeRestaurante(listaDeSalas, menu, stock);

        Reserva novaReserva = sr.criarReserva("Tomás", 8);
        System.out.println(novaReserva);

    }
}
