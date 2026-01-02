package lei.grupo4.java;

public class StockItem {
    static int mQuantItemStock;
    private int mId;
    private String mNome;
    private int mQuantidade;
    private int mStockMinimo;

    public StockItem(String pNome, int pQuantidade, int pStockMinimo) {
        StockItem.mQuantItemStock +=1;
        this.mId = StockItem.mQuantItemStock;
        this.mNome = pNome;
        this.mQuantidade = pQuantidade;
        this.mStockMinimo = pStockMinimo;
    }


    public void repor(int pQuantidade) {
        this.mQuantidade += pQuantidade;
    }

    public void remover(int pQuantidade){
        this.mQuantidade -= pQuantidade;
    }


    // Verifica ingredientes abaixo ou no stock mínimo
    public boolean abaixoDoMinimo() {
       return this.mQuantidade < this.mStockMinimo;
    }
    public String mostrarDetalhes(){
        String ret ="";
        ret += String.format("Item: %s\t Quantidade:%d\t StockItem Minimo: %d", this.mNome, this.mQuantidade, this.mStockMinimo);
        return ret;
    }
    @Override
    public String toString(){
        return this.mNome;
    }


}

