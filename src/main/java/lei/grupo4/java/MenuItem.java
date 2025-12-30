package lei.grupo4.java;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import org.json.JSONObject;
import org.json.JSONTokener;

import static lei.grupo4.java.Stock.obterStock;

public class MenuItem {

    private static final String CAMINHO_MENU_JSON ="src/main/java/lei/grupo4/resources/Menu.json";

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
    public static boolean podeSerPreparado(int idPrato) {

        Map<Integer, Stock.Ingrediente> stock = obterStock();
        Map<Integer, Integer> ingredientesNecessarios = obterIngredientesDoPrato(idPrato);

        for (Integer idIngrediente : ingredientesNecessarios.keySet()) {

            // ingrediente não existe no stock
            if (!stock.containsKey(idIngrediente)) {
                return false;
            }

            int quantidadeNecessaria = ingredientesNecessarios.get(idIngrediente);
            int quantidadeEmStock = stock.get(idIngrediente).getQuantidade();

            // quantidade insuficiente
            if (quantidadeEmStock < quantidadeNecessaria) {
                return false;
            }
        }

        // se passou todas as verificações
        return true;
    }

    public static Map<Integer, Integer> obterIngredientesDoPrato(int idPrato) {
        Map<Integer, Integer> ingredientes = new HashMap<>();
        File ficheiro = new File(CAMINHO_MENU_JSON);
        String dados = "";

        try (Scanner sc = new Scanner(ficheiro)) {
            while (sc.hasNextLine()) {
                dados += sc.nextLine();
            }

            JSONObject menu = new JSONObject(dados);
            JSONObject prato = menu.getJSONObject(String.valueOf(idPrato));
            JSONObject ingrNecessarios = prato.getJSONObject("ingredientesNecessarios");

            for (String key : ingrNecessarios.keySet()) {
                ingredientes.put(
                        Integer.parseInt(key),
                        ingrNecessarios.getInt(key)
                );
            }

        } catch (IOException e) {
            System.err.println("Erro ao ler o ficheiro Menu.json");
        } catch (Exception e) {
            System.out.println(e);
        }

        return ingredientes;
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
