package lei.grupo4.java;

import java.util.Map;

public class PedidoItem {
    MenuItem mMenuItem;
    String mObservacoes;
    Map<Stock, Integer> mIngredientes;
    public PedidoItem(
            MenuItem pMenuItem,
            String pObservacoes
    ){
        this.mMenuItem = pMenuItem;
        this.mObservacoes = pObservacoes;

    }



}
