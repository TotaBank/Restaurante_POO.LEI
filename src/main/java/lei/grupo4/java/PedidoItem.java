package lei.grupo4.java;

import java.util.Map;

public class PedidoItem {
    private MenuItem mMenuItem;
    private String mObservacoes;
    private Map<StockItem, Integer> mIngredientes;
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
    public float obterPreco(){return this.mMenuItem.obterPreco();}

    public Map<StockItem, Integer> obterIngredientesItemMenu(){
        return this.mMenuItem.obterIngredientes();
    }

    public void removerIngrediente(StockItem pStockItem, int pQuantidade){
        if(this.mIngredientes.containsKey(pStockItem)){
            int qtdAtual = this.mIngredientes.get(pStockItem);
            int novaQtd = qtdAtual - pQuantidade;
            if (novaQtd < 0){
                novaQtd = 0;
            }
            this.mIngredientes.put(pStockItem, novaQtd);
        }
    }
    public void removerIngrediente(StockItem pStockItem){
        if(this.mIngredientes.containsKey(pStockItem)){
            int novaQtd = 0;
            this.mIngredientes.put(pStockItem, novaQtd);
        }
    }
    public void adicionarIngredientes(StockItem pStockItem, int pQuantidade){
        if(this.mIngredientes.containsKey(pStockItem)){
            int qtdAtual = this.mIngredientes.get(pStockItem);
            int novaQtd = qtdAtual + pQuantidade;
            if (novaQtd < 0){
                novaQtd = 0;
            }
            this.mIngredientes.put(pStockItem, novaQtd);
        }
    }

    public Map<StockItem, Integer> obterIngredientesFinais(){
        return this.mIngredientes;
    }

    public boolean possivelPreparar(){
        for(Map.Entry<StockItem, Integer> entry : this.mIngredientes.entrySet()){
            StockItem itemAtual = entry.getKey();
            int quantidade = entry.getValue();
            if(itemAtual.obterQuantidade() <= quantidade){
                return false;
            }
        }
        return true;
    }

    public void reservarIngredientes(){
        for(Map.Entry<StockItem, Integer> entry : this.mIngredientes.entrySet()){
            StockItem itemAtual = entry.getKey();
            int quantidade = entry.getValue();
            itemAtual.reservar(quantidade);
        }
    }

    public void libertarIngredientes(){
        for(Map.Entry<StockItem, Integer> entry : this.mIngredientes.entrySet()){
            StockItem itemAtual = entry.getKey();
            int quantidade = entry.getValue();
            itemAtual.libertarReserva(quantidade);
        }
    }



}
