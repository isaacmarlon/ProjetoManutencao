public class ULA {

    public final int SOMA = 0;
    public final int SUB = 1;
    public final int DIV = 2;
    public final int MULT = 3;
    public final int MAIOR = 4;
    public final int MENOR = 5;
    public final int IGUAL = 6;
    public final int GOTO = 7;
    public final int LER = 8;
    public final int ESC = 9;

    private int operacao;
    private int registrador;
    private int acumulador;
    
    private boolean isGoto;
    private boolean isEscrever;
    
    public ULA()
    {
        operacao = 0;
        isGoto = false;
        isEscrever = false;
    }

    public void setOp(int operacao){ this.operacao = operacao;}
    public void setReg(int registrador){ this.registrador = registrador;}
    public void setAcc(int acumulador){ this.acumulador = acumulador;}

    public void clock(){
        switch(operacao){
            case SOMA:
                acumulador += registrador;
                break;
            case SUB:
                acumulador -= registrador;
                break;
            case DIV:
                acumulador /= registrador;
                break;
            case MULT:
                acumulador *= registrador;
                break;
            case MAIOR:
                acumulador = acumulador > registrador ? 1:0;
                break;
            case MENOR:
                acumulador = acumulador < registrador ? 1:0;
                break;
            case IGUAL:
                acumulador = acumulador == registrador ? 1:0;
                break;
            /*
            case GOTO:
                isGoto = true;
                break;
            */
            case LER:
                acumulador = registrador;
                break;
            case ESC:
                isEscrever = true;
                break;
        }
    }

    public int getAcc(){return acumulador;}
    public boolean getEstado(){return acumulador == 0;}
    public int getOperacao() { return operacao; }
    
    public int getSOMA() { return SOMA; }
    public int getDIV() { return DIV; }
    public int getIGUAL() { return IGUAL; }
    public int getMAIOR() { return MAIOR; }
    public int getMULT() { return MULT; }
    public int getMENOR() { return MENOR; }
    public int getSUB() { return SUB; }
    public int getGOTO() { return GOTO; }

    public int getESC() {
        return ESC;
    }

    public int getLER() {
        return LER;
    }
    
    

    public int getRegistrador() {
        return registrador;
    }

    public boolean isGoto() {
        return isGoto;
    }
    public boolean isEscrever()
    {
        return isEscrever;
    }
    public void setEscrever(boolean valor)
    {
        isEscrever = valor;
    }
    
    
}