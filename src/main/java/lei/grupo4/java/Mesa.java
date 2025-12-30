package lei.grupo4.java;

import java.io.File;
import java.io.IOException;
import java.sql.Array;
import java.sql.Ref;
import java.time.LocalDate;
import java.util.*;

import org.json.JSONArray;
import org.json.JSONObject;


class CompararPorCapacidade implements Comparator<Mesa>{
    @Override
    public int compare(Mesa o1, Mesa o2) {
        return o1.mCapacidade.compareTo(o2.mCapacidade);
    }
}
public class Mesa {
    private static final String CAMINHO_MESAS_JSON = "src/main/java/lei/grupo4/resources/Mesas.json";
    private static final String CAMINHO_RESERVAS_JSON = "src/main/java/lei/grupo4/resources/Reservas.json";

    Integer mId;
    Integer mCapacidade;
    public Mesa(
            int pId,
            int pCapacidade
    ){
        this.mId = pId;
        this.mCapacidade = pCapacidade;
    }
    public String toString(){
        String ret ="";
        ret+=String.format("Mesa: %d\nCapacidade: %d", this.mId, this.mCapacidade);
        return ret;
    }

    public int obterId(){
        return this.mId;
    }

    public int obterCapacidade(){
        return this.mCapacidade;
    }

    public static List<Mesa> obterTodasAsMesas(){
        List<Mesa> listaDeMesas = new ArrayList<>();
        File jsonMesas = new File(CAMINHO_MESAS_JSON);
        String dados = "";
        try(Scanner r = new Scanner(jsonMesas)){
            while (r.hasNextLine()) {
                dados +=r.nextLine();

            }
            JSONArray arrayMesas = new JSONArray(dados);
            for(int i = 0; i < arrayMesas.length(); i++ ){
                JSONObject mesaSelecionada = arrayMesas.getJSONObject(i);
                int id = mesaSelecionada.getInt("id");
                int capacidade = mesaSelecionada.getInt("capacidade");
                listaDeMesas.add(new Mesa(id, capacidade));
            }
        } catch(IOException e){
            System.err.println("Erro ao ler o ficheiro");
        } catch (Exception e) {
            System.out.println(e);
        }
        Comparator<Mesa> comparador = new CompararPorCapacidade(); // no final, da sort pelo tamanho da mesa asc
        listaDeMesas.sort(comparador);
        return listaDeMesas;
    }
    public static List<Mesa> obterPorCapacidade(int pNumPessoas){
        List<Mesa> todasAsMesas = Mesa.obterTodasAsMesas();
        List<Mesa> mesasCapazes = new ArrayList<Mesa>();
        for(Mesa mesa : todasAsMesas){
            if (mesa.mCapacidade >= pNumPessoas){
                mesasCapazes.add(mesa);
            }
        }
        return mesasCapazes;
    }

    public boolean podeSerReservada(LocalDate pData, Refeicao pRefeicao){
        String dados = Utilitarios.carregarOuInicializarFicheiroJSON(CAMINHO_RESERVAS_JSON, FicheiroJSON.OBJECT);
            JSONObject dias = new JSONObject(dados);
            if (!dias.has(pData.toString())){ //caso nao exista o dia, nenhuma marcacao foi feita, estao todas livres
                return true;
            }
            JSONArray refeicao = dias.getJSONObject(pData.toString()).getJSONArray(pRefeicao.toString());
            for(int i = 0; i < refeicao.length(); i++){
                JSONObject informacaoReserva = refeicao.getJSONObject(i);
                int mesa = informacaoReserva.getInt("mesa");
                if (this.mId == mesa)
                    return false;
            }
            return true;
    }



}
