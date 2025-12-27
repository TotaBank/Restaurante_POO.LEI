package lei.grupo4;

public class Mesa {
    int mId;
    int mCapacidade;
    EstadoMesa mEstado;
    public Mesa(
            int pId,
            int pCapacidade,
            EstadoMesa pEstado
    ){
        this.mId = pId;
        this.mCapacidade = pCapacidade;
        this.mEstado = pEstado;
    }


}
