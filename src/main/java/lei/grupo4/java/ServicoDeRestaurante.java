package lei.grupo4.java;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class ServicoDeRestaurante {
    private List<Sala> mSalas;
    private List<MenuItem> mMenu;
    private List<StockItem> mStock;
    private List<Pedido> mPedidos;
    private List<Reserva> mReservas;
    private List<Fatura> mFaturas;
    public ServicoDeRestaurante(
            List<Sala> pSalas,
            List<MenuItem> pMenu,
            List<StockItem> pStock
    ){
        this.mSalas = pSalas;
        this.mMenu = pMenu;
        this.mStock = pStock;
        this.mPedidos = new ArrayList<>();
        this.mReservas = new ArrayList<>();
        this.mFaturas = new ArrayList<>();
    }
    public List<Fatura> obterTodasAsFaturas(){return this.mFaturas;}


    public Reserva criarReserva(String pNome, int pNumeroPessoas){
        for(Sala sala : mSalas){
            for(Mesa mesa : sala.obterMesas()){
                if(mesa.livre() && mesa.obterCapacidade()>=pNumeroPessoas){
                    Reserva novaReserva = new Reserva(pNumeroPessoas, pNome, mesa);
                    this.mReservas.add(novaReserva);
                    mesa.reservar();
                    return novaReserva;
                }
            }
        }
        throw new IllegalStateException("Não há mesas livres");

    }

    public void removerReserva(Reserva pReserva){
        if(this.mReservas.contains(pReserva)){
            this.mReservas.remove(pReserva);
        } else {
            throw new IllegalStateException("Reserva não existe para poder ser removida.");
        }
    }

    public Pedido criarPedido(String pNome, int pNumeroPessoas) {
        for (Sala salaAtual : this.mSalas) {
            Mesa mesaCapaz = salaAtual.procurarMesaLivre(pNumeroPessoas);
            if (mesaCapaz != null) {
                if (mesaCapaz.obterCapacidade() >= pNumeroPessoas && mesaCapaz.livre()) {
                    Pedido novoPedido = Pedido.criarPedido(mesaCapaz);
                    this.mPedidos.add(novoPedido);
                    mesaCapaz.abrirPedido(novoPedido);
                    return novoPedido;

                }
            }
        }
        throw new IllegalStateException("Não há mesas livres");
    }

    public Pedido criarPedido(Reserva pReserva){
        if (this.mReservas.contains(pReserva)){
            String nomeReserva = pReserva.obterNomeReserva();
            int quantasPessoas = pReserva.obterNumeroPessoas();
            Mesa mesaReservada = pReserva.obterMesa();
                    Pedido novoPedido = Pedido.criarPedido(mesaReservada);
                    this.mPedidos.add(novoPedido);
                    mesaReservada.abrirPedido(novoPedido);
                    this.mReservas.remove(pReserva); //ou eventualmente podemos colocar num ficheiro json de registo
                     return novoPedido;

        }

        throw new IllegalStateException("Não há mesas livres");
    }

    public void adicionarItemAPedido(Pedido pPedido, PedidoItem pItemPedido){
        if (pItemPedido.possivelPreparar()){
            pItemPedido.reservarIngredientes();
            pPedido.adicionarItem(pItemPedido);
        }else{
            throw new IllegalStateException(String.format("Não é possível preparar o item %s", pItemPedido));
        }
    }
    public void servirPedido(Pedido pPedido){pPedido.servir();}
    public PedidoItem criarItemDePedido(MenuItem pItemMenu, String pObservacoes){
        PedidoItem novoPedido = new PedidoItem(pItemMenu, pObservacoes);
        return novoPedido;
    }


    public void removerItemAPedido(Pedido pPedido, PedidoItem pItemPedido){
        if(pPedido.obterListaItemsRegistados().contains(pItemPedido)){
            pPedido.removerItem(pItemPedido);
            pItemPedido.libertarIngredientes();
        } else {
            throw new IllegalStateException("O item não existe no pedido.");
        }
    }

    public Fatura fecharMesa(Mesa pMesa){
        if(pMesa.ocupada()){
            Pedido pedidoAtual = pMesa.obterPedidoAtual();
            boolean foiPago = pedidoAtual.pagar();
            if(foiPago){
                Fatura novaFatura = new Fatura(pedidoAtual);
                this.mFaturas.add(novaFatura);
                return novaFatura;
            }
        }
        throw new IllegalStateException("Não há mesas livres");

    }

    public List<Reserva> obterReservas(){return this.mReservas;}
    public List<Pedido> obterPedidos(){return this.mPedidos;}


}
