package lei.grupo4.java;

import java.io.FileReader;
import java.io.FileWriter;

import org.json.JSONObject;
import org.json.JSONTokener;

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


    public static void repor(int pIdIngrediente, int pQuantidadeARepor) {
        try {
            String mCaminhoStock =
                    "src/main/java/lei/grupo4/resources/Stock.json";

            JSONObject mStockJson =
                    new JSONObject(new JSONTokener(new FileReader(mCaminhoStock)));

            String mIdStr = String.valueOf(pIdIngrediente);

            if (!mStockJson.has(mIdStr)) {
                System.out.println("Ingrediente não existe no stock.");
                return;
            }

            JSONObject mStockItem = mStockJson.getJSONObject(mIdStr);
            int mQtdAtual = mStockItem.getInt("quantidade");

            mStockItem.put("quantidade", mQtdAtual + pQuantidadeARepor);

            FileWriter mWriter = new FileWriter(mCaminhoStock);
            mWriter.write(mStockJson.toString(4));
            mWriter.close();

            System.out.println(
                    "Stock reposto: " +
                            mStockItem.getString("nome") +
                            " + " + pQuantidadeARepor
            );

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Verifica ingredientes abaixo ou no stock mínimo
    public static void abaixoDoMinimo() {
        try {
            String mCaminhoStock =
                    "src/main/java/lei/grupo4/resources/Stock.json";

            JSONObject mStockJson =
                    new JSONObject(new JSONTokener(new FileReader(mCaminhoStock)));

            boolean mExisteAlerta = false;

            for (String mIdIngrediente : mStockJson.keySet()) {

                JSONObject mStockItem =
                        mStockJson.getJSONObject(mIdIngrediente);

                String mNome = mStockItem.getString("nome");
                int mQuantidade = mStockItem.getInt("quantidade");
                int mStockMinimo = mStockItem.getInt("stockMinimo");

                if (mQuantidade <= mStockMinimo) {
                    System.out.println(
                            "[ALERTA] Ingrediente \"" + mNome +
                                    "\" atingiu o stock mínimo (" +
                                    mQuantidade + "/" + mStockMinimo +
                                    "). É necessário repor."
                    );
                    mExisteAlerta = true;
                }
            }

            if (!mExisteAlerta) {
                System.out.println("Todos os ingredientes estão acima do stock mínimo.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

