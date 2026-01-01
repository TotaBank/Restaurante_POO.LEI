package lei.grupo4.java;

import java.util.ArrayList;
import java.util.List;

public class Sala {
    private String mNome;
    private List<Mesa> mMesas;
    public Sala(String pNome){
        this.mNome = pNome;
        this.mMesas = new ArrayList<>();
    }
    @Override
    public String toString(){
        String ret = "";
        ret += String.format("%s\tMesas: %s", this.mNome, this.mMesas);
        return ret;
    }

    public void adicionarMesa(Mesa pMesa){
        this.mMesas.add(pMesa);
    }

    public String obterNome(){
        return this.mNome;
    }
    public List<Mesa> obterMesas(){
        return this.mMesas;
    }

    public Mesa procurarMesaLivre(int pNumeroPessoas){
        for(Mesa mesa : this.mMesas){
            if ((mesa.obterCapacidade() >= pNumeroPessoas) && (mesa.livre())){
                return mesa;
            }
        }
        System.out.println("Nenhuma mesa está livre");
        return null;
    }


}
