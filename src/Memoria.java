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
        dados[0] = "ler 10";
        dados[1] = "soma 11";
        
        dados[2] = "esc 12";
        dados[3] = "sub 13";
        dados[4] = "esc 12";
        dados[5] = "ler 12";
        dados[6] = "goto 3";
        
        dados[10] = "4";
        dados[11] = "4";
        dados[12] = "0";
        dados[13] = "1";
        
    }

    public String getDados(int end) {
        return dados[end];
    }

    public void setDados(int end, String dados) {
        this.dados[end] = dados;
    }
    
    
}
