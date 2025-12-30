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
    private static final String CAMINHO_STOCK_JSON ="src/main/java/lei/grupo4/resources/Stock.json";
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

    public static class Ingrediente {
        private int id;
        private String nome;
        private int quantidade;

        public Ingrediente(int id, String nome, int quantidade) {
            this.id = id;
            this.nome = nome;
            this.quantidade = quantidade;
        }

        public int getQuantidade() {
            return quantidade;
        }
    }
    //Coloca o ficheiro stock em um MAPA
    public static Map<Integer, Ingrediente> obterStock() {
        Map<Integer, Ingrediente> stock = new HashMap<>();
        File ficheiro = new File(CAMINHO_STOCK_JSON);
        String dados = "";

        try (Scanner sc = new Scanner(ficheiro)) {
            while (sc.hasNextLine()) {
                dados += sc.nextLine();
            }

            JSONObject jsonStock = new JSONObject(dados);

            for (String key : jsonStock.keySet()) {
                int id = Integer.parseInt(key);
                JSONObject ingr = jsonStock.getJSONObject(key);

                String nome = ingr.getString("nome");
                int quantidade = ingr.getInt("quantidade");

                stock.put(id, new Ingrediente(id, nome, quantidade));
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro Stock.json");
        } catch (Exception e) {
            System.out.println(e);
        }

        return stock;
    }


}

