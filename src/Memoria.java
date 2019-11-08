/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author lenne
 */
public class Memoria {
    private String[] dados = new String[100];
    
    public Memoria(){
        for(int i=0; i<100; i++){
            dados[i]="0";
        }
        dados[0] = "soma 10";
        dados[1] = "sub 11";
        dados[2] = "div 12";
        dados[3] = "mult 13";
        dados[4] = "maior 10";
        dados[5] = "menor 11";
        dados[6] = "igual 12";
        
        dados[10] = "5";
        dados[11] = "10";
        dados[12] = "2";
        dados[13] = "7";
        
    }

    public String getDados(int end) {
        return dados[end];
    }

    public void setDados(int end, String dados) {
        this.dados[end] = dados;
    }
    
    
}
