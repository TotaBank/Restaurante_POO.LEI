package lei.grupo4.java;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<Mesa> mesas = new ArrayList<>();
    private static List<MenuItem> menu = new ArrayList<>();
    private static List<Pedido> pedidos = new ArrayList<>();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int opcao;
        do {
            System.out.println("\n=== SISTEMA RESTAURANTE ===");
            System.out.println("1 - Criar reserva");
            System.out.println("2 - Criar pedido");
            System.out.println("3 - Adicionar item ao pedido");
            System.out.println("4 - Ver total do pedido");
            System.out.println("5 - Confirmar pedido");
            System.out.println("0 - Sair");
            System.out.print("Opcao: ");

            opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1 -> criarReserva(sc);
               // case 2 -> criarPedido(sc);
                case 3 -> adicionarItemPedido(sc);
                case 4 -> verTotalPedido(sc);
                case 5 -> confirmarPedido(sc);
                case 0 -> System.out.println("A sair...");
                default -> System.out.println("Opcao invalida!");
            }

        } while (opcao != 0);

        sc.close();
    }



    private static void criarReserva(Scanner sc) {

        System.out.println("\n=== CRIAR RESERVA ===");

        // Data
        System.out.print("Dia: ");
        int mDia = sc.nextInt();
        System.out.print("Mes (1-12): ");
        int mMes = sc.nextInt();
        System.out.print("Ano (ex: 2025): ");
        int mAno = sc.nextInt();
        sc.nextLine();

        LocalDate mData = LocalDate.of(mAno, mMes, mDia);

        // Refeição
        System.out.println("Tipo de refeicao:");
        System.out.println("1 - Almoço");
        System.out.println("2 - Jantar");
        System.out.print("Opcao: ");
        int mOpcaoRefeicao = sc.nextInt();
        sc.nextLine();

        Refeicao mRefeicao;
        if (mOpcaoRefeicao == 1) {
            mRefeicao = Refeicao.ALMOCO;
        } else if (mOpcaoRefeicao == 2) {
            mRefeicao = Refeicao.JANTAR;
        } else {
            System.out.println("Refeicao invalida!");
            return;
        }

        // Número de pessoas
        System.out.print("Numero de pessoas: ");
        int mNumPessoas = sc.nextInt();
        sc.nextLine();

        // Nome da reserva
        System.out.print("Nome da reserva: ");
        String mNome = sc.nextLine();
/*
        Reserva mReserva = Reserva.criarReserva(
                mData,
                mRefeicao,
                mNumPessoas,
                mNome
        );

        if (mReserva != null) {
            System.out.println("Reserva criada com sucesso!");
        } else {
            System.out.println("Nao foi possivel criar a reserva.");
        }*/
    }


/*
    private static void criarPedido(Scanner sc) {
        System.out.print("Numero da mesa: ");
        int idMesa = sc.nextInt();

        Mesa mesa = mesas.stream()
                .filter(m -> m.getmCapacidade() > 0 && m.estaDisponivel() == false)
                .findFirst()
                .orElse(null);

        if (mesa == null) {
            System.out.println("Mesa nao encontrada ou nao ocupada.");
            return;
        }

        Utilizador empregado = new Utilizador(
                1,
                "Empregado",
                "emp1",
                "1234",
                Perfil.EMPREGADO_MESA,
                true
        );


        Pedido pedido = new Pedido(mesa, empregado);
        pedidos.add(pedido);

        System.out.println("Pedido criado com sucesso!");
    }
    */
    private static void adicionarItemPedido(Scanner sc) {
        if (pedidos.isEmpty()) {
            System.out.println("Nao ha pedidos.");
            return;
        }

        Pedido pedido = pedidos.get(pedidos.size() - 1);

        System.out.println("Menu:");
        for (MenuItem item : menu) {
            System.out.println(item);
        }

        System.out.print("ID do item: ");
        int idItem = sc.nextInt();

        System.out.print("Quantidade: ");
        int qtd = sc.nextInt();
        sc.nextLine();

        MenuItem item = menu.stream()
                .filter(i -> i.obterPreco() > 0)
                .findFirst()
                .orElse(null);

        if (item != null) {
            pedido.adicionarItem(new PedidoItem(item, ""));
            System.out.println("Item adicionado!");
        }
    }

    private static void verTotalPedido(Scanner sc) {
        if (pedidos.isEmpty()) {
            System.out.println("Nao ha pedidos.");
            return;
        }

        Pedido pedido = pedidos.get(pedidos.size() - 1);
        pedido.confirmar();
        System.out.println("Pedido confirmado!");
    }

    private static void confirmarPedido(Scanner sc) {
        if (pedidos.isEmpty()) {
            System.out.println("Nao ha pedidos.");
            return;
        }

        Pedido pedido = pedidos.get(pedidos.size() - 1);
        pedido.confirmar();
        System.out.println("Pedido confirmado!");
    }

}
