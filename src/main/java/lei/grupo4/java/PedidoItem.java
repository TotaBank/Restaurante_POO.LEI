package lei.grupo4.java;

import java.util.Map;

public class PedidoItem {
    MenuItem mMenuItem;
    String mObservacoes;
    Map<Stock, Integer> mIngredientes;
    public PedidoItem(
            MenuItem pMenuItem,
            String pObservacoes
    ){
        this.mMenuItem = pMenuItem;
        this.mObservacoes = pObservacoes;
        this.mIngredientes = obterIngredientesItemMenu();
    }
    public String mostrarDetalhes(){
        String ret = "";
        ret += String.format("Item do Menu: %s\nObservações: %s\nIngredientes: %s\n",
                this.mMenuItem.obterNome(),
                this.mObservacoes,
                this.mIngredientes);
        return ret;
    }
    public String toString(){
        return this.mMenuItem.obterNome();
    }

    public Map<Stock, Integer> obterIngredientesItemMenu(){
        return this.mMenuItem.obterIngredientes();
    }

    public void removerIngrediente(Stock pStock, int pQuantidade){
        if(this.mIngredientes.containsKey(pStock)){
            int qtdAtual = this.mIngredientes.get(pStock);
            int novaQtd = qtdAtual - pQuantidade;
            if (novaQtd < 0){
                novaQtd = 0;
            }
            this.mIngredientes.put(pStock, novaQtd);
        }
    }
    public void adicionarIngredientes(Stock pStock, int pQuantidade){
        if(this.mIngredientes.containsKey(pStock)){
            int qtdAtual = this.mIngredientes.get(pStock);
            int novaQtd = qtdAtual + pQuantidade;
            if (novaQtd < 0){
                novaQtd = 0;
            }
            this.mIngredientes.put(pStock, novaQtd);
        }
    }

    public Map<Stock, Integer> obterIngredientesFinais(){
        return this.mIngredientes;
    }




}
