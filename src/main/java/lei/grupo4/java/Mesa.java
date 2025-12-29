package lei.grupo4.java;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;
public class Mesa {
    private static final String CAMINHO_MESAS_JSON = "src/main/java/lei/grupo4/resources/Mesas.json";

    int mId;
    int mCapacidade;
    EstadoMesa mEstado;
    public Mesa(
            int pId,
            int pCapacidade,
            EstadoMesa pEstado
    ){
        this.mId = pId;
        this.mCapacidade = pCapacidade;
        this.mEstado = pEstado;
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

    public EstadoMesa obterEstado(){
        return this.mEstado;
    }

    public void mudarEstado(EstadoMesa pNovoEstado){
        this.mEstado = pNovoEstado;
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
                listaDeMesas.add(new Mesa(id, capacidade, EstadoMesa.LIVRE));
            }
        } catch(IOException e){
            System.err.println("Erro ao ler o ficheiro");
        } catch (Exception e) {
            System.out.println(e);
        }

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



}
