/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizadores;

import C.Main;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author rosaura
 */
public class Sin {
    
    
    public static void sinta(String dir) throws IOException{
        ArrayList<String> line = new ArrayList();
        ArrayList<String> pila = new ArrayList();
        char AP='i';
        try{
            File archivo = new File(dir);      //Se abre el archivo de especificación

            FileReader fr = new FileReader(archivo);    //se lee el archivo

            BufferedReader b = new BufferedReader(fr);  //se separa por lineas el archivo

            String linea;    //Variable que almacena la linea actual del archivo
            while(null!= (linea = b.readLine())){
                line.add(linea);    //se almacena cada linea en una variable
            }
            Trans.sentImprime(line);
            Trans.sentCompara(line);
            Trans.sent3(line);
            Trans.sent2(line);
            Trans.band=1;
            //transforma_SENT_to_SENTS(Lexico);
            while (Trans.band!=0){
                Trans.band=0;
                    //realiza las transformaciones que pueda encontrar
                Trans.sentSents(line);
                Trans.sent1(line);

            }
            int x=Trans.prog(line);
            if(x==1){
                System.out.println("COMPILACIÓN EXITOSA");
            }
            else{
                System.out.println("COMPILACIÓN FALLIDA");
            }
        }
        catch(FileNotFoundException e1){
            System.out.println("No se encuentra el archivo");
        }
    }
}
