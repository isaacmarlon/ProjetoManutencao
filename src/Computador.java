/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author isaac
 */
public class Computador 
{
    
    private Cpu cpu;
    private Memoria memoria;
    private Barramento barramento;

    public Computador() 
    {
        barramento =  new Barramento();
        cpu = new Cpu(barramento);
        memoria = new Memoria();
    }
    
    public void clockMicroInstrucao() 
    {
        cpu.clockMicro();
        this.checaProcessoBarramento();
    }
    
    public void clockSubInstrucao() 
    {
        while(!cpu.isNovaSubInstrucao()) 
        {
            cpu.clockSub();
            this.checaProcessoBarramento();
        }
        System.out.println("Computador checou que é uma novaSubInstrucao também!");
        cpu.setNovaSubInstrucao(false);
    }
    
    public void clockInstrucao() 
    {
        while(!cpu.isNovaInstrucao()) 
        {
            this.clockSubInstrucao();
        }
    }

    public boolean cpuNovaInstrucao() { return cpu.isNovaInstrucao(); }
    public void setNovaInstrucao(boolean valor) { cpu.setNovaInstrucao(valor); }
    
    public Cpu getCpu() { return cpu; }
    
    public Memoria getMemoria() { return memoria; }
    public void setMemoria(Memoria memoria) { this.memoria = memoria; }

    void checaProcessoBarramento() 
    {
        if (barramento.isBarramentoAtivo())
        {
            if(barramento.isRecebendo())
            {
                String dado = memoria.getDados(barramento.getEnd());
                barramento.setDado(dado);
            }
            else
            {
                memoria.setDados(barramento.getEnd(), barramento.getDado());
            }
            barramento.fim();
        }
    }
}
