package lei.grupo4.java;

public enum EstadoPedido {
    REGISTADO("REGISTADO"),
    EM_PREPARACAO("EM PREPARAÇÃO"),
    PRONTO("PRONTO"),
    SERVIDO("SERVIDO");

    String mEstado;
    EstadoPedido(String estado) {
        this.mEstado = estado;
    }

    public String toString(){
        return this.mEstado;
    }
}
