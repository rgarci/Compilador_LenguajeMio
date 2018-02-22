/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package C;

import Analizadores.Lex;
import static Analizadores.Lex.lex;

import static Analizadores.Sin.sinta;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;
import javax.swing.JOptionPane;

/**
 *
 * @author rosaura
 */
public class Main {
    public static ArrayList RES = new ArrayList();
    public static ArrayList RELA = new ArrayList();
    public static ArrayList ARIT = new ArrayList();
    public static String ASIG = "=";
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        
        Scanner enter = new Scanner(System.in);
        RES.add("PROGRAMA");
        RES.add("FINPROG");
        RES.add("SI");
        RES.add("ENTONCES");
        RES.add("SINO");
        RES.add("FINSI");
        RES.add("REPITE");
        RES.add("VECES");
        RES.add("FINREP");
        RES.add("IMPRIME");

        RELA.add(">");
        RELA.add("<");
        RELA.add("==");

        ARIT.add("+");
        ARIT.add("-");
        ARIT.add("*");
        ARIT.add("/");
        System.out.println("Por favor escriba el código en el documento \"codigo.mio\" y presione enter");
        String ent = enter.nextLine();
        lex("codigo.mio");
        if(Lex.b==0){
            sinta("codigo.lex");
        }
    }
    
}
