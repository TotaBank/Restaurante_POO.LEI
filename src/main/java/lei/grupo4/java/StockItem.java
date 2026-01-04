package lei.grupo4.java;

import java.util.ArrayList;
import java.util.List;

public class StockItem {
    static Observador mObservadorAlertaStock = new AlertaStock();
    static int mQuantItemStock;
    private int mId;
    private String mNome;
    private int mQuantidade;
    private int mStockMinimo;
    private int mQuantidadeReservada;

    private List<Observador> mObservadores;

    public StockItem(String pNome, int pQuantidade, int pStockMinimo) {
        StockItem.mQuantItemStock +=1;
        this.mId = StockItem.mQuantItemStock;
        this.mNome = pNome;
        this.mQuantidade = pQuantidade;
        this.mStockMinimo = pStockMinimo;
        this.mObservadores = new ArrayList<>();
        this.mObservadores.add(mObservadorAlertaStock);
    }

    public int obterQuantidade(){return this.mQuantidade;}

    public void repor(int pQuantidade) {
        this.mQuantidade += pQuantidade;
    }

    public void remover(int pQuantidade){
        this.mQuantidade -= pQuantidade;
        libertarReserva(pQuantidade);
        if (abaixoDoMinimo()){
            notificarObservadores(String.format("%s está abaixo do stock mínimo", this.mNome));
        }
    }

    public void reservar(int pQuantidade){
        this.mQuantidadeReservada += pQuantidade;
    }
    public void libertarReserva(int pQuantidade){
        this.mQuantidadeReservada -= pQuantidade;
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

    public void adicionarObservador(Observador obs) {
        this.mObservadores.add(obs);
    }
    private void notificarObservadores(String pMensagem) {
        for (Observador obs : mObservadores) {
            obs.receberNotificacao(pMensagem);
        }
    }


}

