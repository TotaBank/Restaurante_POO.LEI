package lei.grupo4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
public class Mesa {
    private static final String CAMINHO_MESAS_JSON = "Mesas.json";

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

    public static List<Mesa> obterTodasAsMesas(){
        List<Mesa> listaDeMesas = new ArrayList<>();
        JSONObject root = new JSONObject(CAMINHO_MESAS_JSON);
        Iterator<String> id = root.keys();
        while (id.hasNext()){
            String currentId = id.toString();
            JSONObject dataForCurrentId = root.getJSONObject(currentId);
            //falta verificar e comecar a fazer a funcao
        }
        return null;
    }
    public static List<Mesa> obterPorCapacidade(int pNumPessoas){
        return null;
    }

}
