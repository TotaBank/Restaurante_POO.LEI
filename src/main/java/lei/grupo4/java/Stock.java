package lei.grupo4.java;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.JSONTokener;

public class Stock {
    static int mQuantItemStock;
    private int mId;
    private String mNome;
    private int mQuantidade;
    private int mStockMinimo;

    public Stock(String pNome, int pQuantidade, int pStockMinimo) {
        Stock.mQuantItemStock +=1;
        this.mId = Stock.mQuantItemStock;
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
        ret += String.format("Item: %s\t Quantidade:%d\t Stock Minimo: %d", this.mNome, this.mQuantidade, this.mStockMinimo);
        return ret;
    }
    @Override
    public String toString(){
        return this.mNome;
    }


}

