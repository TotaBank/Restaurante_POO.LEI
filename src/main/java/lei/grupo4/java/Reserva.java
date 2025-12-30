package lei.grupo4.java;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class Reserva {
    private static final String CAMINHO_RESERVAS_JSON = "src/main/java/lei/grupo4/resources/Reservas.json";

    private LocalDate mData;
    private Refeicao mRefeicao;
    private int mNumPessoas;
    private String mNomeReserva;
    private Mesa mMesa;

    private Reserva(LocalDate pData, Refeicao pRefeicao, int numPessoas, String nomeReserva, Mesa mesa) {
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
            if(mesa.mEstado == EstadoMesa.LIVRE)
                mesasDisponiveis.add(mesa);
        } // ha um problema, tamos a atualizar o estado da mesa com membros de dados mas deviamos estar
        //a utilizar o ficheiro json
        //é necessario olhar para o dia refeicao e mesa
        if (mesasDisponiveis.isEmpty()) {
            System.out.println("Nao ha mesas disponiveis para este numero de pessoas.");
            return null;
        }

        // Escolhe a primeira mesa disponível
        Mesa mesaSelecionada = mesasDisponiveis.get(0);
        mesaSelecionada.mudarEstado(EstadoMesa.RESERVADA);

        Reserva novaReserva = new Reserva(pData, pRefeicao, pNumPessoas, nomeReserva, mesaSelecionada);
        Reserva.registarReserva(novaReserva);
        return novaReserva;
    }
    public static void registarReserva(Reserva pReserva){
        File jsonReservas = new File(CAMINHO_RESERVAS_JSON);
        String dados = "";
        try(Scanner r = new Scanner(jsonReservas)){
            while (r.hasNextLine()) {
                dados +=r.nextLine();

            }
            JSONArray reservas = new JSONArray(dados);
            JSONObject novaReserva = new JSONObject();
            novaReserva.put("id", UUID.randomUUID());
            novaReserva.put("data", pReserva.mData);
            novaReserva.put("refeicao", pReserva.mRefeicao);
            novaReserva.put("numeroPessoas", pReserva.mNumPessoas);
            novaReserva.put("nome", pReserva.mNomeReserva);
            novaReserva.put("mesa", pReserva.mMesa.mId);
            reservas.put(novaReserva);

            try (FileWriter writer = new FileWriter(jsonReservas, false)) {
                writer.write(reservas.toString(4));
            }

        } catch(IOException e){
            System.err.println("Erro ao ler o ficheiro");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

}
