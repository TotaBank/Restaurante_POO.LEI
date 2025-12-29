package lei.grupo4.java;

import java.util.List;

public class Testes {
    public static void main(String[] args){
        List<Mesa> todasAsMesas = Mesa.obterTodasAsMesas();
        System.out.println(todasAsMesas);
        System.out.println(Mesa.obterPorCapacidade(8));

    }
}
