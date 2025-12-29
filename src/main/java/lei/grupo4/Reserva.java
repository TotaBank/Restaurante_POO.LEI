package lei.grupo4;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class Reserva {
    LocalDateTime mDataHora;
    int mNumPessoas;
    String mNomeReserva;
    Mesa mMesaId;
    private Reserva(
            LocalDateTime pDataHora,
            int pNumPessoas,
            String pNomeReserva,
            Mesa pMesaId
    ){
        this.mDataHora = pDataHora;
        this.mNumPessoas = pNumPessoas;
        this.mNomeReserva = pNomeReserva;
        this.mMesaId = pMesaId;
    }

    public static Reserva criarReserva(
            LocalDateTime pDataHora,
            int pNumPessoas,
            String pNomeReserva){
        Mesa mesaParaReserva = null;
        List<Mesa> mesasPossiveis = Mesa.obterPorCapacidade(pNumPessoas);
        List<Reserva> reservasDoDia = Reserva.obterPorData(pDataHora.toLocalDate());

        if (mesasPossiveis != null){
            for (Mesa mesa : mesasPossiveis){

            }
            return new Reserva(pDataHora, pNumPessoas, pNomeReserva, mesaParaReserva);
        } else {
            return null;
        }
    }

    public static List<Reserva> obterPorData(LocalDate pDataHora){
        return null;
    }
}
