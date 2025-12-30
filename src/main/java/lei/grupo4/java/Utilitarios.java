package lei.grupo4.java;

import org.json.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Utilitarios {
    public static String carregarOuInicializarFicheiroJSON(String caminho, FicheiroJSON pTipoFicheiro) {
        File ficheiro = new File(caminho);
        try{
            if (!ficheiro.exists()) {
                ficheiro.createNewFile();
                if (pTipoFicheiro == FicheiroJSON.OBJECT)
                    Files.writeString(ficheiro.toPath(), "{}");
                else
                    Files.writeString(ficheiro.toPath(), "[]");

            }

            String conteudo = Files.readString(ficheiro.toPath());
            if (conteudo.isBlank()) {
                if (pTipoFicheiro == FicheiroJSON.OBJECT)
                    conteudo = "{}";
                else
                    conteudo = "[]";
            }

            return conteudo;
    } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void escreverJsonEmFicheiro(JSONObject pJsonObject, String pCaminho){
        try{
            File ficheiro = new File(pCaminho);
            if (!ficheiro.exists()){
                ficheiro.createNewFile();
            }

            try (FileWriter writer = new FileWriter(ficheiro, false)) {
                writer.write(pJsonObject.toString(4));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
