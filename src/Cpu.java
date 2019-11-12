/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author isaac
 */
public class Cpu implements InterfaceForm {
    
    private DadosCpu dadosCpu;
    private Barramento barramento;
    private ULA ula;
    private UC uc;
    
    private int indexMicroInstrucao;
    
    private int indexSubInstrucao;
    private final int BUSCAR_INSTRUCAO = 0;
    private final int PROCESSAR_INSTRUCAO = 1;
    private final int BUSCAR_DADOS = 2;
    private final int PROCESSAR_DADOS = 3;
    private final int ESCREVER_DADOS = 4;
    
    private boolean isNovaSubInstrucao;
    
    
    public Cpu(Barramento barramento) {
        
        dadosCpu = new DadosCpu();
        ula = new ULA();
        uc = new UC(dadosCpu, barramento, ula); // permite que a UC manipule os registradores da CPU/barramento
        
        indexMicroInstrucao = 0;
        indexSubInstrucao = 0;
        
        isNovaSubInstrucao = false;
        
    }
    
    @Override
    public void clockMicro() {
        System.out.println(indexMicroInstrucao + " <<<<<<<<<<<<<<<<<<<<");
        switch (indexMicroInstrucao)
        {
            case 0:       
                dadosCpu.setNovaInstrucao(false); // enquanto realizar as micros posteriores ainda é a mesma sub instrucao
                uc.buscar0();
                indexMicroInstrucao++;
                break;
            case 1:
                uc.buscar1();
                indexMicroInstrucao++;
                break;
            case 2:
                uc.buscar2();
                indexMicroInstrucao++;
                break;
            case 3:
                uc.buscar3();
                indexMicroInstrucao++;
                break;
            case 4:
                uc.buscar4();
                indexMicroInstrucao++;
                break;
            case 5:
                uc.processarInstrucao0();
                indexMicroInstrucao++;
                break;
            case 6:
                uc.processarInstrucao1();
                indexMicroInstrucao++;
                break;
            case 7:
                uc.buscarDados0();
                if (uc.isGoto())
                {
                    indexMicroInstrucao = 0;
                    indexSubInstrucao = 0;
                }
                else {
                    indexMicroInstrucao++;    
                }
                break;
            case 8:
                uc.buscarDados1();
                indexMicroInstrucao++;
                break;
            case 9:
                uc.processarDados0();
                indexMicroInstrucao++;
                break;
            case 10:
                uc.processarDados1();
                indexMicroInstrucao++;
                break;
            case 11:
                uc.processarDados2();
                indexMicroInstrucao = 0;
                dadosCpu.setNovaInstrucao(true); // avisa para o computador apontar para a proxima instrucao
                break;
        }
        
    }

    @Override
    public void clockSub() {
        switch (indexSubInstrucao)
        {
            case BUSCAR_INSTRUCAO:
                uc.buscarInstrucao();
                
                if (uc.acabouSub())
                {   
                    if (!uc.isGoto()) {
                        System.out.println("Agora é uma nova subInstrucao!");
                        isNovaSubInstrucao = true;
                        indexSubInstrucao++;
                    }
                    uc.setAcabouSub(false); // resetando
                }
                else
                {
                    uc.nextSubMicro();
                }
                break;
            case PROCESSAR_INSTRUCAO:
                uc.processarInstrucao();
                
                if (uc.acabouSub())
                {   
                    System.out.println("Agora é uma nova subInstrucao!");
                    isNovaSubInstrucao = true;
                    indexSubInstrucao++;
                    uc.setAcabouSub(false); // resetando
                }
                else
                {
                    uc.nextSubMicro();
                }
                break;
            case BUSCAR_DADOS:
                uc.buscarDados();
                
                if (uc.acabouSub())
                {   
                    System.out.println("Agora é uma nova subInstrucao!");
                    isNovaSubInstrucao = true;
                    indexSubInstrucao++;
                    uc.setAcabouSub(false); // resetando
                }
                else
                {
                    uc.nextSubMicro();
                }
                break;
            case PROCESSAR_DADOS:
                uc.processarDados();
                
                if (uc.acabouSub())
                {   
                    System.out.println("Agora é uma nova subInstrucao!");
                    isNovaSubInstrucao = true;
                    indexSubInstrucao++;
                    uc.setAcabouSub(false); // resetando
                    this.finalizaInstrucao();
                }
                else
                {
                    uc.nextSubMicro();
                }
                break;
            /*case ESCREVER_DADOS:
                //uc.escreverDados(); // falta implementar
                indexSubInstrucao = 0; //??
                break;*/   
        }
    }
    
    public void finalizaInstrucao() 
    {
        this.dadosCpu.setNovaInstrucao(true);
        this.indexSubInstrucao = 0;
    }
    
    public Barramento getDadosBarramento() { return barramento; }
    public void setBarramento(Barramento barramento) { this.barramento = barramento; }

    public DadosCpu getDadosCpu() { return dadosCpu; }
    public UC getUC() { return uc; }
    
    public boolean isNovaInstrucao() { return dadosCpu.isNovaInstrucao(); }
    public void setNovaInstrucao(boolean valor){ dadosCpu.setNovaInstrucao(valor); }
    
    public boolean isNovaSubInstrucao() { return this.isNovaSubInstrucao; }
    public void setNovaSubInstrucao(boolean valor) { this.isNovaSubInstrucao = valor; }
    
}
