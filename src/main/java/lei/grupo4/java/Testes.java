package lei.grupo4.java;

import java.time.LocalDate;

public class Testes {
    public static void main(String[] args){

        // Cria o MenuItem
        Reserva.criarReserva(LocalDate.of(2025,12,31), Refeicao.JANTAR, 3 , "Tomas");
        //System.out.println(Mesa.obterPorCapacidade(8));

        /* //teste pode ser preparado e consume itens do stock
        // Cria o MenuItem
        MenuItem hamburger = new MenuItem(1, "Hambúrguer Clássico", 8.5f, true, null);

        // Verifica se pode ser preparado
        if (hamburger.podeSerPreparado()) {
            System.out.println("Hambúrguer pode ser preparado");

            // Remove stock
            hamburger.consumirStock();
            System.out.println("Stock atualizado após pedido");
        } else {
            System.out.println("Hambúrguer NÃO pode ser preparado");
        }
        */


        /* //teste restock
        Stock.repor(1, 10); // repõe 10 unidades de Carne
        Stock.repor(4, 5);  // repõe 5 unidades de Alface
        */

        /* //teste de stock minimo
        Stock.abaixoDoMinimo();
        */


    }
}

