package lei.grupo4.java;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;

import static lei.grupo4.java.ServicoDeRestaurante.mCaminhoFatura;

public class Fatura {
    private static int mQuantasFaturas;
    private int mId;
    private float mValor;
    private Pedido mPedido;
    private MetodoDePagamento mMetodoDePagamento;
    public Fatura(Pedido pPedido){
        this.mPedido = pPedido;
        Fatura.mQuantasFaturas +=1;
        this.mId = mQuantasFaturas;
        this.mValor = pPedido.calcularTotal();
        this.mMetodoDePagamento = null;
    }
    public Pedido obterPedido(){return this.mPedido;}
    public float obterPreco(){return this.mValor;}
    public Integer obterId(){return this.mId;}
    @Override
    public String toString(){
        StringBuilder ret = new StringBuilder();
        ret.append(String.format("Fatura: %d\tPedido: %d\tValor: %.2f\n", this.mId, this.mPedido.obterId(), this.mValor));
        for(PedidoItem itemAtual : this.mPedido.mListaItemsServidos){
            ret.append(String.format("%s - %.2f€\n", itemAtual, itemAtual.obterPreco()));
        }

        return ret.toString();
    }
    public void adicionarMetodoDePagamento(MetodoDePagamento pMetododDePagamento){
        this.mMetodoDePagamento = pMetododDePagamento;
    };
    public MetodoDePagamento obterMetodoDePagamento(){return this.mMetodoDePagamento;}

    public void imprimirFatura(){
        JSONObject root;
        try{
            String dados = Files.readString(Path.of(mCaminhoFatura));
            if (dados != ""){
                root = new JSONObject(dados);
            } else {
                root = new JSONObject();
            }
            JSONArray itemsServidos = new JSONArray();
            for(PedidoItem itemServido : this.obterPedido().obterListaItemsServidos()){
                JSONObject itemEPreco = new JSONObject();
                itemEPreco.put(itemServido.toString(), itemServido.obterPreco());
                itemsServidos.put(itemEPreco);
            }
            JSONObject fatura = new JSONObject();
            fatura.put("Pedido", this.obterPedido().obterId());
            fatura.put("Preco", this.obterPreco());
            fatura.put("Items", itemsServidos);
            fatura.put("Método de Pagamento", this.obterMetodoDePagamento());
            root.put(this.obterId().toString(), fatura);


        try (PrintWriter writer = new PrintWriter(mCaminhoFatura)) {
            writer.write(root.toString().formatted());
        } catch (Exception e) {
            System.out.println("Erro: " + e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

     }
}
