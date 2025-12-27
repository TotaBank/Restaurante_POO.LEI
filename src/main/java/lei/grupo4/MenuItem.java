package lei.grupo4;

import java.util.Map;

public class MenuItem {
    int mId;
    String mNome;
    float mPreco;
    boolean mDisponivel;
    Map<Stock, Integer> mIngredientes;
    public MenuItem(
            int pId,
            String pNome,
            float pPreco,
            boolean pDisponivel,
            Map<Stock, Integer> pIngredientes
    ){
        this.mId = pId;
        this.mNome = pNome;
        this.mPreco = pPreco;
        this.mDisponivel = pDisponivel;
        this.mIngredientes = pIngredientes;
    }
}
