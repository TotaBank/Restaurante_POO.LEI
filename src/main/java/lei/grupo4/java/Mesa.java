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
    private Integer mQuantMesas = 0;
    Integer mId;
    Integer mCapacidade;
    EstadoMesa mEstado;
    public Mesa(
            int pCapacidade
    ){
        mQuantMesas +=1;
        this.mId = mQuantMesas;
        this.mCapacidade = pCapacidade;
        this.mEstado = EstadoMesa.LIVRE;
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
    public boolean livre(){
        return this.mEstado == EstadoMesa.LIVRE;
    }




}
