package lei.grupo4;

import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Reserva {
    private LocalDateTime dataHora;
    private int numPessoas;
    private String nomeReserva;
    private Mesa mesa;

    private static List<Reserva> reservas = new ArrayList<>();

    private Reserva(LocalDateTime dataHora, int numPessoas, String nomeReserva, Mesa mesa) {
        this.dataHora = dataHora;
        this.numPessoas = numPessoas;
        this.nomeReserva = nomeReserva;
        this.mesa = mesa;
    }

    public static Reserva criarReserva(LocalDateTime dataHora, int numPessoas, String nomeReserva, List<Mesa> mesas) {
        // Obter mesas disponíveis com capacidade suficiente
        List<Mesa> mesasDisponiveis = Mesa.obterPorCapacidade(numPessoas);

        if (mesasDisponiveis.isEmpty()) {
            System.out.println("Nao ha mesas disponiveis para este numero de pessoas.");
            return null;
        }

        // Escolhe a primeira mesa disponível
        Mesa mesaSelecionada = mesasDisponiveis.get(0);
        mesaSelecionada.reservar();

        Reserva novaReserva = new Reserva(dataHora, numPessoas, nomeReserva, mesaSelecionada);
        reservas.add(novaReserva);

        System.out.println("Reserva criada na mesa " + mesaSelecionada.getmId() + " para " + numPessoas + " pessoas.");
        return novaReserva;
    }

    public static List<Reserva> obterPorData(LocalDate data) {
        List<Reserva> resultado = new ArrayList<>();
        for (Reserva r : reservas) {
            if (r.dataHora.toLocalDate().equals(data)) {
                resultado.add(r);
            }
        }
        return resultado;
    }
}
