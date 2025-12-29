package lei.grupo4.java;

public class PedidoItem {
    MenuItem mMenuItem;
    String mObservacoes;
    float mPrecoTotalItem;
    public PedidoItem(
            MenuItem pMenuItem,
            String pObservacoes
    ){
        this.mMenuItem = pMenuItem;
        this.mObservacoes = pObservacoes;
        this.mPrecoTotalItem = pMenuItem.obterPreco();

    }
}
