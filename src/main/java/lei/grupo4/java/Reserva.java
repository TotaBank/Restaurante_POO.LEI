package lei.grupo4.java;


import java.time.LocalDate;
import java.util.List;


public class Reserva {
    static int mQuantasReservas = 0;
    private int mId;
    private int mNumPessoas;
    private String mNomeReserva;
    private Mesa mMesa;


    public Reserva(int pNumPessoas, String pNomeReserva, Mesa pMesa) {
        Reserva.mQuantasReservas +=1;
        this.mId = mQuantasReservas;
        this.mNumPessoas = pNumPessoas;
        this.mNomeReserva = pNomeReserva;
        this.mMesa = pMesa;
    }

    public String obterNomeReserva(){return this.mNomeReserva;}
    public Mesa obterMesa(){return this.mMesa;}
    public int obterNumeroPessoas(){return this.mNumPessoas;}
    @Override
    public String toString(){
        String ret = "";
        ret+=String.format("ID: %s\tNome: %s\tNumeroPessoas: %d\tMesa: %s",
                this.mId,
                this.mNomeReserva,
                this.mNumPessoas,
                this.mMesa.obterId());
        return ret;
    }



}
