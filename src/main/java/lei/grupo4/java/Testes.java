package lei.grupo4.java;

import java.time.LocalDate;

public class Testes {
    public static void main(String[] args){

        // Cria o MenuItem
        Reserva.criarReserva(LocalDate.of(2025,12,31), Refeicao.JANTAR, 3 , "Tomas");
        //System.out.println(Mesa.obterPorCapacidade(8));

    }
}
