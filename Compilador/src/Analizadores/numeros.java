/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizadores;

import java.util.StringTokenizer;

/**
 *
 * @author rosaura
 */
public class numeros {
     public static int valnum(String tok1){
        for(int n=1; n<tok1.length()-1;n++){
                if(tok1.charAt(n)!='&'&&tok1.charAt(n)!='|'&&tok1.charAt(n)!='.'&&tok1.charAt(n)!=','){
                    throw new NumberFormatException();
                }
            }
        int valt=0;
        StringTokenizer stok2 = new StringTokenizer(tok1, ",");
        int k=0;
        while(stok2.hasMoreElements()){
            int val=0;
            String tok2=String.valueOf(stok2.nextElement());
            for(int n=0;n<tok2.length();n++){
                if(tok2.charAt(n)=='|'){
                    for(int m=n+1;m<tok2.length();m++){
                        if(tok2.charAt(m)=='&'||tok2.charAt(m)=='.'){
                            throw new NumberOrderException("Los números no tienen el orden correcto");
                        }
                    }
                }
                if(tok2.charAt(n)=='.'){
                    for(int m=n+1;m<tok2.length();m++){
                        if(tok2.charAt(m)=='&'){
                            throw new NumberOrderException("Los números no tienen el orden correcto");
                        }
                    }
                }
            }
            for(int s=0;s<tok2.length();s++){
                switch(tok2.charAt(s)){
                case '&':
                    val+=0;
                    break;
                case '|':
                    val+=5;
                    break;
                case '.':
                    val+=1;
                    break;
                }
                if(val>20){
                    throw new NonValidNumberException("Hay un nivel con un valor mayor a 20");
                } 
                val*=(Math.pow(20, k));
                
            }
            valt+=val;
            k++;
        }
        return valt;
    }
}
