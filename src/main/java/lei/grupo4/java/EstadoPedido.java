package lei.grupo4.java;

public enum EstadoPedido {
    REGISTADO("REGISTADO"),
    SERVIDO("SERVIDO"),
    PAGO("PAGO");

    String mEstado;
    EstadoPedido(String estado) {
        this.mEstado = estado;
    }

    public String toString(){
        return this.mEstado;
    }
}
