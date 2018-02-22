/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizadores;

import C.Main;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author rosaura
 */
public class Lex {
    public static int b=0;
    public static void lex(String dir) throws IOException{
        ArrayList<String> lex = new ArrayList();
        ArrayList<String> line = new ArrayList();
        ArrayList<String> IDS = new ArrayList();
        ArrayList VAL = new ArrayList();
        ArrayList<String> TXT = new ArrayList();
        try{
            File archivo = new File(dir);      //Se abre el archivo de especificación

            FileReader fr = new FileReader(archivo);    //se lee el archivo

            BufferedReader b = new BufferedReader(fr);  //se separa por lineas el archivo
            
            String linea;    //Variable que almacena la linea actual del archivo
            while(null!= (linea = b.readLine())){
                line.add(linea);    //se almacena cada linea en una variable
            }
            b.close();
            for(int i=0; i<line.size();i++){
                StringTokenizer stok = new StringTokenizer(line.get(i), " ");
                while(stok.hasMoreElements()){
                    String tok1 = String.valueOf(stok.nextElement());
                    if(Main.RES.contains(tok1)){
                        lex.add(tok1);
                    }else{
                        if(Main.RELA.contains(tok1)){
                            lex.add("[op_rel]");
                        }else{
                            if(Main.ARIT.contains(tok1)){
                                lex.add("[op_ar]");
                            }else{
                                if(tok1.charAt(0)=='"'){
                                String text= tok1;
                                while(stok.hasMoreElements()){
                                    String tok2 = String.valueOf(stok.nextElement());
                                    text = text+" "+tok2;
                                    if(tok2.charAt(tok2.length()-1)=='"'){
                                        lex.add("[txt]");
                                        TXT.add(text);
                                        break;
                                        }
                                    }
                                }else{
                                    if(tok1.equals(Main.ASIG)){
                                        lex.add(Main.ASIG);
                                    }
                                    else{
                                        if(tok1.charAt(0)=='&'||tok1.charAt(0)=='|'||tok1.charAt(0)=='.'){
                                            lex.add("[val]");
                                            VAL.add(valnum(tok1));
                                        }else{
                                            if(!IDS.contains(tok1)){
                                                IDS.add(tok1);
                                            }
                                            lex.add("[id]");
                                        }                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         
                                    }
                                }
                            }
                        }
                    }
                }
                
            }
            File archivo2 = new File("codigo.lex");      //Se abre el archivo de especificación

            BufferedWriter bw = new BufferedWriter(new FileWriter(archivo2));    //se lee el archivo
            for(int i=0; i<lex.size();i++){
                bw.write(lex.get(i));
                bw.write("\n");
            }
            bw.close();
            File archivo3 = new File("codigo.sim");      //Se abre el archivo de especificación

            BufferedWriter bw2 = new BufferedWriter(new FileWriter(archivo3));    //se lee el archivo
            bw2.write("IDS = "+ IDS+"\n");
            bw2.write("TXT = "+ TXT+"\n");
            bw2.write("VALS = "+ VAL+"\n");
            bw2.close();
        }
        catch(FileNotFoundException e1){
            System.out.println("No se encuentra el archivo");
            b=1;
        }
        catch(NumberFormatException e2){
            System.out.println("NumberFormatException LOS NÚMEROS ESTÁN ESCRITOS ERRONEAMENTE");
            b=1;
        }
        catch(NonValidNumberException e3){
            System.out.println(e3.getMessage());
            b=1;
        }
        catch(NumberOrderException e4){
            System.out.println(e4.getMessage());
            b=1;
        }
    }
    
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
