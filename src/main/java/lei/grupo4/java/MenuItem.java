package lei.grupo4.java;

import java.io.FileReader;
import java.io.FileWriter;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;
import org.json.JSONTokener;

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

    public float obterPreco(){ return this.mPreco; }

    // Verifica se há stock suficiente para preparar o prato
    public boolean podeSerPreparado() {
        try {
            String mCaminhoMenu = "src/main/java/lei/grupo4/resources/Menu.json";
            String mCaminhoStock = "src/main/java/lei/grupo4/resources/Stock.json";

            JSONObject mMenuJson = new JSONObject(new JSONTokener(new FileReader(mCaminhoMenu)));
            JSONObject mStockJson = new JSONObject(new JSONTokener(new FileReader(mCaminhoStock)));

            JSONObject mItemMenu = mMenuJson.getJSONObject(String.valueOf(mId));
            JSONObject mIngredientes = mItemMenu.getJSONObject("ingredientesNecessarios");

            Iterator<String> mIdsIngredientes = mIngredientes.keys();
            while (mIdsIngredientes.hasNext()) {
                String mIdIngrediente = mIdsIngredientes.next();
                int mQtdNecessaria = mIngredientes.getInt(mIdIngrediente);

                JSONObject mStockItem = mStockJson.getJSONObject(mIdIngrediente);
                int mQtdDisponivel = mStockItem.getInt("quantidade");

                if (mQtdDisponivel < mQtdNecessaria) {
                    System.out.println("Stock insuficiente para: " + mStockItem.getString("nome"));
                    return false;
                }
            }

            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Remove os ingredientes usados do stock.json
    public void consumirStock() {
        try {
            String mCaminhoMenu = "src/main/java/lei/grupo4/resources/Menu.json";
            String mCaminhoStock = "src/main/java/lei/grupo4/resources/Stock.json";

            JSONObject mMenuJson = new JSONObject(new JSONTokener(new FileReader(mCaminhoMenu)));
            JSONObject mStockJson = new JSONObject(new JSONTokener(new FileReader(mCaminhoStock)));

            JSONObject mItemMenu = mMenuJson.getJSONObject(String.valueOf(mId));
            JSONObject mIngredientes = mItemMenu.getJSONObject("ingredientesNecessarios");

            Iterator<String> mIdsIngredientes = mIngredientes.keys();
            while (mIdsIngredientes.hasNext()) {
                String mIdIngrediente = mIdsIngredientes.next();
                int mQtdNecessaria = mIngredientes.getInt(mIdIngrediente);

                JSONObject mStockItem = mStockJson.getJSONObject(mIdIngrediente);
                int mQtdDisponivel = mStockItem.getInt("quantidade");

                mStockItem.put("quantidade", mQtdDisponivel - mQtdNecessaria);
            }

            // Grava o stock atualizado
            FileWriter mWriter = new FileWriter(mCaminhoStock);
            mWriter.write(mStockJson.toString(4));
            mWriter.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
