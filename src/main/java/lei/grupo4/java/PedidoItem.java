package lei.grupo4.java;

public class PedidoItem {
    MenuItem mMenuItem;
    int mQuantidade;
    String mObservacoes;
    float mPrecoTotalItem;
    public PedidoItem(
            MenuItem pMenuItem,
            int pQuantidade,
            String pObservacoes
    ){
        this.mMenuItem = pMenuItem;
        this.mObservacoes = pObservacoes;
        this.mQuantidade = pQuantidade;
        this.mPrecoTotalItem = pMenuItem.obterPreco() * pQuantidade;

    }
}
