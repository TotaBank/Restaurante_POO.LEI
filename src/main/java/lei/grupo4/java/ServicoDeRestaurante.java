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
    public List<Fatura> obterFaturas(){return this.mFaturas;}


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

    public Pedido criarPedido(String pNome, int pNumeroPessoas){
        boolean reservado = false;
        Mesa mesaSelecionada = null;
        for(Reserva reserva : this.mReservas){
            if(reserva.obterNomeReserva() == pNome && reserva.obterNumeroPessoas() >=pNumeroPessoas){
                reservado = true;
                mesaSelecionada = reserva.obterMesa();
            }
        }
        if (!(reservado)) {
            for (Sala sala : mSalas) {
                for (Mesa mesa : sala.obterMesas()) {
                    if (mesa.livre() && mesa.obterCapacidade() >= pNumeroPessoas) {
                        mesaSelecionada = mesa;
                    }
                }
            }
        }
        if (mesaSelecionada != null){
            Pedido novoPedido = Pedido.criarPedido(mesaSelecionada);
            this.mPedidos.add(novoPedido);
            mesaSelecionada.abrirPedido(novoPedido);
            return novoPedido;
        }

        throw new IllegalStateException("Não há mesas livres");

    }

    public Fatura fecharPedido(Mesa pMesa){
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

    public List<Reserva> obterReservas(){
        return this.mReservas;
    }
    public List<Pedido> obterPedidos(){return this.mPedidos;}


}
