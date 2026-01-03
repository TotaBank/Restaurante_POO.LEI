package lei.grupo4.java;

public class BoolAndFloat {
    float mFloat;
    boolean mBool;
    public BoolAndFloat(boolean pBool, float pFloat){
        this.mFloat = pFloat;
        this.mBool = pBool;
    }
    public boolean obterBool(){return this.mBool;}
    public float obterFloat(){return this.mFloat;}
}
