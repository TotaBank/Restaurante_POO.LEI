package lei.grupo4.java;

public class Stock {

    private int mId;
    private String mNome;
    private int mQuantidade;
    private int mStockMinimo;

    public Stock(int pId, String pNome, int pQuantidade, int pStockMinimo) {
        this.mId = pId;
        this.mNome = pNome;
        this.mQuantidade = pQuantidade;
        this.mStockMinimo = pStockMinimo;
    }

    public int getmId() {
        return mId;
    }

    public String getmNome() {
        return mNome;
    }

    public int getmQuantidade() {
        return mQuantidade;
    }

    public void setmQuantidade(int pQuantidade) {
        this.mQuantidade = pQuantidade;
    }

    public int getmStockMinimo() {
        return mStockMinimo;
    }
}

