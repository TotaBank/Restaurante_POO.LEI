package lei.grupo4.java;

import java.util.Map;


public class MenuItem {

    private static int mQuantMenuItem = 0;
    private int mId;
    private String mNome;
    private float mPreco;
    private boolean mDisponivel;
    private Map<StockItem, Integer> mIngredientes;

    public MenuItem(
            String pNome,
            float pPreco,
            boolean pDisponivel,
            Map<StockItem, Integer> pIngredientes
    ){
        MenuItem.mQuantMenuItem += 1;
        this.mId = MenuItem.mQuantMenuItem;
        this.mNome = pNome;
        this.mPreco = pPreco;
        this.mDisponivel = pDisponivel;
        this.mIngredientes = pIngredientes;
    }
    public String mostrarDetalhes(){
        String ret = "";
        ret += String.format("Nome: %s\t Preco: %.2f\tIngredientes: %s\tDisponivel: %s", this.mNome, this.mPreco, this.mIngredientes, this.mDisponivel);
        return ret;
    }

    @Override
    public String toString(){
        return this.mNome;
    }

    public float obterPreco(){ return this.mPreco; }
    public String obterNome(){return this.mNome;}
    public Map<StockItem, Integer> obterIngredientes() {
        return this.mIngredientes;
    }

    public boolean disponivel(){
        return this.mDisponivel;
    }

}
