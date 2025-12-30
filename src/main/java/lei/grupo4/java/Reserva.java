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

    private Reserva(LocalDate pData, Refeicao pRefeicao, int numPessoas, String nomeReserva, Mesa mesa) {
        this.mID = UUID.randomUUID();
        this.mData = pData;
        this.mRefeicao = pRefeicao;
        this.mNumPessoas = numPessoas;
        this.mNomeReserva = nomeReserva;
        this.mMesa = mesa;
    }
    private Reserva(UUID pId,LocalDate pData, Refeicao pRefeicao, int numPessoas, String nomeReserva, Mesa mesa) {
        this.mID = pId;
        this.mData = pData;
        this.mRefeicao = pRefeicao;
        this.mNumPessoas = numPessoas;
        this.mNomeReserva = nomeReserva;
        this.mMesa = mesa;
    }

    public static Reserva criarReserva(LocalDate pData, Refeicao pRefeicao, int pNumPessoas, String nomeReserva) {
        // Obter mesas disponíveis com capacidade suficiente
        List<Mesa> mesasComCapacidade = Mesa.obterPorCapacidade(pNumPessoas);
        List<Mesa> mesasDisponiveis = new ArrayList<>();
        for (Mesa mesa : mesasComCapacidade){
            if(mesa.podeSerReservada(pData, pRefeicao))
                mesasDisponiveis.add(mesa);
        }
        if (mesasDisponiveis.isEmpty()) {
            System.out.println("Nao ha mesas disponiveis para este numero de pessoas.");
            return null;
        }

        // Escolhe a primeira mesa disponível
        Mesa mesaSelecionada = mesasDisponiveis.get(0);

        Reserva novaReserva = new Reserva(pData, pRefeicao, pNumPessoas, nomeReserva, mesaSelecionada);
        novaReserva.registarReserva();
        return novaReserva;
    }
    public void registarReserva(){
            String dados = Utilitarios.carregarOuInicializarFicheiroJSON(CAMINHO_RESERVAS_JSON, FicheiroJSON.OBJECT);
            JSONObject reservas = new JSONObject(dados);
            JSONObject objetoDia;
            if (reservas.has(this.mData.toString())){
                objetoDia = reservas.getJSONObject(this.mData.toString());
            } else {
                objetoDia = new JSONObject();
                objetoDia.put("ALMOCO", new JSONArray());
                objetoDia.put("JANTAR", new JSONArray());
                reservas.put(this.mData.toString(), objetoDia);
            }

            JSONArray refeicao = reservas.getJSONObject(this.mData.toString()).getJSONArray(this.mRefeicao.toString());
            JSONObject reserva = new JSONObject();
            reserva.put("id", this.mID);
            reserva.put("mesa", this.mMesa.obterId());
            reserva.put("nome", this.mNomeReserva);
            reserva.put("numeroPessoas", this.mNumPessoas);
            refeicao.put(reserva);

            Utilitarios.escreverJsonEmFicheiro(reservas, CAMINHO_RESERVAS_JSON);
    }

    public static void removerReserva(Reserva pReserva){
        List<Reserva> todasAsReservas = carregarFicheiroReservas();
        List<Reserva> nova = new ArrayList<>();
        for(Reserva reserva : todasAsReservas){
            if (!reserva.mID.equals(pReserva.mID)){
                nova.add(reserva); //se encontrar a reserva nao adicionar ao novo array
            }
        }
        Utilitarios.apagarFicheiro(CAMINHO_RESERVAS_JSON);
        for(Reserva reserva : nova){
            reserva.registarReserva();
        }
    }

    public static List<Reserva> carregarFicheiroReservas(){
        List<Reserva> ret = new ArrayList<>();
        Refeicao refeicaoConstrutor;
        String dados = Utilitarios.carregarOuInicializarFicheiroJSON(CAMINHO_RESERVAS_JSON, FicheiroJSON.OBJECT);
        JSONObject dias = new JSONObject(dados);
        for(String dia : dias.keySet()){
            JSONObject objetoDia = dias.getJSONObject(dia);
            for(String refeicao : objetoDia.keySet()){
                JSONArray arrayRefeicao = objetoDia.getJSONArray(refeicao);

                for(int i = 0; i < arrayRefeicao.length(); i++){
                    JSONObject informacaoRefeicao = arrayRefeicao.getJSONObject(i);
                    if(refeicao.equals("ALMOCO")) {
                        refeicaoConstrutor = Refeicao.ALMOCO;
                    }else{
                        refeicaoConstrutor = Refeicao.JANTAR;}
                        Reserva reserva = new Reserva(
                            UUID.fromString(informacaoRefeicao.getString("id")),
                            LocalDate.parse(dia),
                            refeicaoConstrutor,
                            informacaoRefeicao.getInt("numeroPessoas"),
                            informacaoRefeicao.getString("nome"),
                            Mesa.obterMesaPorId(informacaoRefeicao.getInt("mesa"))
                        );
                    ret.add(reserva);
                }
            }
        }
        return ret;
    }


    @Override
    public String toString(){
        String ret = "";
        ret+=String.format("Dia: %s\tRefeicao: %s\tMesa: %s\tNumeroPessoas: %d\tNome: %s\tID: %s",
                this.mData.toString(),
                this.mRefeicao,
                this.mMesa.obterId(),
                this.mNumPessoas,
                this.mNomeReserva,
                this.mID);
        return ret;
    }



}
