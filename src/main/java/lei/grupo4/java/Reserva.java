package lei.grupo4.java;

import org.json.JSONArray;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class Reserva {
    private static final String CAMINHO_RESERVAS_JSON = "src/main/java/lei/grupo4/resources/Reservas.json";
    private UUID mID;
    private LocalDate mData;
    private Refeicao mRefeicao;
    private int mNumPessoas;
    private String mNomeReserva;
    private Mesa mMesa;

    private Reserva(LocalDate pData, Refeicao pRefeicao, int pNumPessoas, String pNomeReserva, Mesa pMesa) {
        this.mID = UUID.randomUUID();
        this.mData = pData;
        this.mRefeicao = pRefeicao;
        this.mNumPessoas = pNumPessoas;
        this.mNomeReserva = pNomeReserva;
        this.mMesa = pMesa;
    }


    public static Reserva criarReserva(LocalDate pData, Refeicao pRefeicao, int pNumPessoas, String pNomeReserva, Mesa pMesa) {
        return new Reserva(pData, pRefeicao, pNumPessoas, pNomeReserva, pMesa);
    }






    @Override
    public String toString(){
        String ret = "";
        ret+=String.format("ID: %s\tDia: %s\tRefeicao: %s\tMesa: %s\tNumeroPessoas: %d\tNome: %s",
                this.mID,
                this.mData.toString(),
                this.mRefeicao,
                this.mMesa.obterId(),
                this.mNumPessoas,
                this.mNomeReserva);
        return ret;
    }



}
