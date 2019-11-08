/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author PauloVLB
 */
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UC {
    
    private DadosCpu dadosCpu;
    private Barramento barramento;
    private ULA ula;

    private int indexMicroInstrucao;
    private int indexSubMicro;
    
    private boolean acabouSub;
    
    private boolean temOperando;
    private boolean isInstrucao;
    private String operandoInstrucao;
    private String valorInstrucao;
    
    
    public UC(DadosCpu dadosCpu, Barramento barramento, ULA ula) {
        this.barramento = barramento;
        this.dadosCpu = dadosCpu;
        this.ula = ula;
        
        indexMicroInstrucao = 0;
        indexSubMicro = 0;
        
        acabouSub = false;
        temOperando = false;
        isInstrucao = false;
        operandoInstrucao = "";
        valorInstrucao = "";
    }
   
    public void nextSubMicro()
    {
        indexSubMicro++;
    }
    public void finalizaSub()
    {
        this.acabouSub = true;
        this.indexSubMicro = 0;
    }
    public boolean acabouSub() { return acabouSub; }
    public void setAcabouSub(boolean valor){ this.acabouSub = valor; }
    
    public void buscarInstrucao(){
        switch(indexSubMicro) {
            case 0:
                System.out.println("-> BUSCAR 0");
                buscar0();
                break;
            case 1:
                System.out.println("-> BUSCAR 1");
                buscar1();
                break;
            case 2:
                System.out.println("-> BUSCAR 2");
                buscar2();
                break;
            case 3:
                System.out.println("-> BUSCAR 3");
                buscar3();
                break;
            case 4:
                System.out.println("-> BUSCAR 4");
                buscar4();
                this.finalizaSub();
                break;
        }
    }
    
    public void processarInstrucao(){
        switch(indexSubMicro) {
            case 0:
                System.out.println("-> PROCESSAR INSTRUCAO 0");
                processarInstrucao0();
                break;
            case 1:
                System.out.println("-> PROCESSAR INSTRUCAO 1");
                processarInstrucao1();
                this.finalizaSub();
                break;
        }
    }
    
    public void buscarDados()
    {
        switch(indexSubMicro) {
            case 0:
                System.out.println("-> BUSCAR DADOS 0");
                buscarDados0();
                break;
            case 1:
                System.out.println("-> BUSCAR DADOS 1");
                buscarDados1();
                this.finalizaSub();
                break;
        }
    }
    
    public void processarDados()
    {
        switch(indexSubMicro) {
            case 0:
                System.out.println("-> PROCESSAR DADOS 0");
                processarDados0();
                break;
            case 1:
                System.out.println("-> PROCESSAR DADOS 1");
                processarDados1();
                break;
            case 2:
                System.out.println("-> PROCESSAR DADOS 2");
                processarDados2();
                this.finalizaSub();
                break;
        }
    }
    
    public void buscar0(){
        System.out.println("-   -   -   -   buscar0  -   -   -   -");
        dadosCpu.setMar(dadosCpu.getPc());
        System.out.println("MAR recebeu PC");
        System.out.println("-   -   -   -     //     -   -   -   -");
    }
    public void buscar1(){
        System.out.println("-   -   -   -   buscar1  -   -   -   -");
        dadosCpu.setPc(dadosCpu.getPc() + 1);
        System.out.println("PC++");
        System.out.println("-   -   -   -     //     -   -   -   -");
    }
    public void buscar2(){
        System.out.println("-   -   -   -   buscar2  -   -   -   -");
        barramento.receberDado(dadosCpu.getMar());
        System.out.println("Barramento vai receber memória["+ dadosCpu.getMar() +"]");
        System.out.println("-   -   -   -     //     -   -   -   -");
    }
    public void buscar3(){
        System.out.println("-   -   -   -   buscar3  -   -   -   -");
        dadosCpu.setMbr(barramento.getDado());
        System.out.println("Dado recebido de memórioa[" + dadosCpu.getMar() + "] = " + dadosCpu.getMbr());
        System.out.println("-   -   -   -     //     -   -   -   -");
    }
    public void buscar4(){
        System.out.println("-   -   -   -   buscar4  -   -   -   -");
        dadosCpu.setIr((String) dadosCpu.getMbr());
        System.out.println("IR recebeu MBR");
        System.out.println("-   -   -   -     //     -   -   -   -");
    }
    
    public void processarInstrucao0(){
        System.out.println("-   -   -   -   processarInstrucao0  -   -   -   -");
        String ir = dadosCpu.getIr();
        
        this.temOperando = false;
        
        try 
        {
            String[] instrucaoSplit = ir.split(" ");
            
            if (instrucaoSplit.length == 2)
            {
                System.out.println("Tem operando!");
                this.temOperando = true;
                this.operandoInstrucao = instrucaoSplit[0];
                dadosCpu.setMar(Integer.parseInt(instrucaoSplit[1]));
            }
            else if (instrucaoSplit.length == 1)
            {
                try 
                {
                    Integer.parseInt(instrucaoSplit[0]);
                    this.isInstrucao = false;
                }
                catch(Exception ex)
                {
                    this.isInstrucao = true;
                }
                
            }
            else
            {
                throw new Exception();
            }
                
        }
        catch (Exception ex)
        {
            System.out.println("Erro ao tentar processarInstrucao0");
        }
        finally 
        {
            System.out.println("Resultado da decodificação: ");
            System.out.println("Operando = " + this.operandoInstrucao);
            System.out.println("Valor = " + dadosCpu.getMar());
            System.out.println("-   -   -   -           //           -   -   -   -");
        }
    }
    
    public void processarInstrucao1(){
        System.out.println("-   -   -   -   processarInstrucao1  -   -   -   -");
        if (this.temOperando)
        {
            System.out.println("Tem operando!");
            switch(this.operandoInstrucao)
            {
                case "soma":
                    ula.setOp(ula.getSOMA());
                    break;
                case "sub":
                    ula.setOp(ula.getSUB());
                    break;
                case "div":
                    ula.setOp(ula.getDIV());
                    break;
                case "mult":
                    ula.setOp(ula.getMULT());
                    break;
                case "maior":
                    ula.setOp(ula.getMAIOR());
                    break;
                case "menor":
                    ula.setOp(ula.getMENOR());
                    break;
                case "igual":
                    ula.setOp(ula.getIGUAL());
                    break;
            }
            
            System.out.println("ULA recebeu OP = " + ula.getOperacao() + " (" + this.operandoInstrucao  + ")"); 
            System.out.println("-   -   -   -           //           -   -   -   -");
        }
    }
    
    public void buscarDados0(){
        System.out.println("-   -   -   -   buscarDados0  -   -   -   -");
        System.out.println("Vou buscar o dado no endereço MAR " + dadosCpu.getMar());
        barramento.receberDado(dadosCpu.getMar());
        System.out.println("-   -   -   -       //        -   -   -   -");
    }
    
    public void buscarDados1(){
        System.out.println("-   -   -   -   buscarDados1  -   -   -   -");
        System.out.println("Passei o dado da memória["+ dadosCpu.getMar() +"] = " + barramento.getDado() + " para o REG da ULA");
        ula.setReg(Integer.parseInt(barramento.getDado())); 
        System.out.println("-   -   -   -       //        -   -   -   -");
    }
    
    public void processarDados0(){
        System.out.println("-   -   -   -   processarDados0  -   -   -   -");
        System.out.println("Coloquei o valor do ACC (dadosCpu) = " + dadosCpu.getAcc() + " para o ACC (ula)");
        ula.setAcc(dadosCpu.getAcc());
        System.out.println("-   -   -   -       //        -   -   -   -");
    }
    
    public void processarDados1() {
        System.out.println("-   -   -   -   processarDados1  -   -   -   -");
        System.out.println("Dei clock na ULA");
        ula.clock();
        System.out.println("-   -   -   -       //        -   -   -   -");
    }
    
    public void processarDados2() {
        System.out.println("-   -   -   -   processarDados2  -   -   -   -");
        System.out.println("Depois do clock o ACC (ula) = " + ula.getAcc() + " e ACC (dadosCpu) recebe.");
        dadosCpu.setAcc(ula.getAcc());
        System.out.println("-   -   -   -       //        -   -   -   -");
    }

}
