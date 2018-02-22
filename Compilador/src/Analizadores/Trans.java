/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Analizadores;

import java.util.ArrayList;

/**
 *
 * @author rosaura
 */
public class Trans {
    public static ArrayList<String[]> SENTS;
    public static int band = 0;
    public static void sentImprime(ArrayList<String> lex){      //función que detecta y transforma las sentencias imprime
        int i=0;
        while(i<lex.size()){
            if(lex.get(i).equals("IMPRIME") && (lex.get(i+1).equals("[id]") || lex.get(i+1).equals("[val]") || lex.get(i+1).equals("[txt]"))){
                lex.add(i,"<SENT>");
                lex.remove(i+1);
                lex.remove(i+1);
                i=-1;
            }
            i++;
        }
    }
    public static void sentCompara(ArrayList<String> lex){      //función que detecta y transforma las sentencias compara
        int i=0;
        while(i<lex.size()){
            if(lex.get(i).equals("[id]") && lex.get(i+1).equals("[op_rel]") && (lex.get(i+2).equals("[id]") || lex.get(i+2).equals("[val]")) ){
                lex.add(i,"<COMPARA>");
                lex.remove(i+1);
                lex.remove(i+1);
                lex.remove(i+1);
                i=-1;
            }
            i++;
        }
    }
    
    public static void sent3(ArrayList<String> lex){       //función que encuentra y transforma un tipo sentencia SENT
        int i=0;
        while(i<lex.size()){
            if(lex.get(i).equals("[id]") && lex.get(i+1).equals("=") && 
                    (lex.get(i+2).equals("[id]") || lex.get(i+2).equals("[val]")) &&
                        lex.get(i+3).equals("[op_ar]") && 
                            (lex.get(i+4).equals("[id]") || lex.get(i+4).equals("[val]") )){
                
               lex.add(i,"<SENT>");
               lex.remove(i+1);
               lex.remove(i+1);
               lex.remove(i+1);
               lex.remove(i+1);
               lex.remove(i+1);
            }
            i++;
        }
    }
    public static void sent2(ArrayList<String> lex){    //función que encuentra y transforma un segundo tipo sentencia SENT
        int i=0;
        while(i<lex.size()){
            if(lex.get(i).equals("[id]") && lex.get(i+1).equals("=") && (lex.get(i+2).equals("[val]") || lex.get(i+2).equals("[id]")) ){
                lex.add(i,"<SENT>");
                lex.remove(i+1);
                lex.remove(i+1);
                lex.remove(i+1);
            }
            i++;
        }
    }
    public static void sent1(ArrayList<String> lex){        //función que encuentra y transforma un tercer tipo sentencia SENT
        int i=0;
       
        while(i<lex.size()){
            if(lex.get(i).equals("SI") && lex.get(i+1).equals("<COMPARA>") && lex.get(i+2).equals("ENTONCES") && lex.get(i+3).equals("<SENTS>") && lex.get(i+4).equals("SINO") && lex.get(i+5).equals("<SENTS>") && lex.get(i+6).equals("FINSI")){
                lex.add(i,lex.get(i).replaceAll("SI","<SENT>"));
                lex.remove(i+1);
                lex.remove(i+1);
                lex.remove(i+1);
                lex.remove(i+1);
                lex.remove(i+1);
                lex.remove(i+1);
                lex.remove(i+1);
                band++; //se activa si hay cambio
            }
            i++;
        }
        i=0;
        while(i<lex.size()){
            if(lex.get(i).equals("SI") && lex.get(i+1).equals("<COMPARA>") && lex.get(i+2).equals("ENTONCES") && lex.get(i+3).equals("<SENTS>") && lex.get(i+4).equals("FINSI")){
                lex.add(i,lex.get(i).replaceAll("SI","<SENT>"));
                lex.remove(i+1);
                lex.remove(i+1);
                lex.remove(i+1);
                lex.remove(i+1);
                lex.remove(i+1); 
                band++; //se acitiva si hay cambio
            }
            i++;
        }
        i=0;
        while(i<lex.size()){
            if(lex.get(i).equals("REPITE") && (lex.get(i+1).equals("[val]") || lex.get(i+1).equals("[id]")) && lex.get(i+2).equals("VECES") && lex.get(i+3).equals("<SENTS>") && lex.get(i+4).equals("FINREP")){
                lex.add(i,lex.get(i).replaceAll("REPITE","<SENT>"));
                lex.remove(i+1);
                lex.remove(i+1);
                lex.remove(i+1);
                lex.remove(i+1);
                lex.remove(i+1);
                band++; //se activa si hay cambio
            }
            i++;
        }
    }

    
    public static int prog(ArrayList<String> lex){      //función que detecta y transforma la sentencia PROGRAMA
        int i=0;
        int correcto=0;
        
                ArrayList<Integer> linea = new ArrayList<Integer>();
        while(i<lex.size()){
            
                if(lex.get(i).equals("<SENT>")){
                    linea.add(i); //agrega el numero de la linea donde se encuentra
                    i++;
                    while(i<lex.size()){
                         if(lex.get(i).equals("<SENT>")){
                             linea.add(i);
                             i++;
                         }
                       
                         else{ 
                            if (lex.get(i).equals("<SENTS>")){
                             linea.add(i);
                            }
                             lex.set(linea.get(0),"<SENTS>" );
                             band++; //se activa si hubo cambio
                             for (int j = (linea.size()-1); j >0; j--) {
                                int f =linea.get(j);
                                lex.remove(f);
                                 
                             }
                            linea.clear();

                            break;
                         }

                    }

                } 
            

            i++;
        }
        i=0;
        while(i<lex.size()){
            if(lex.get(i).equals("PROGRAMA") && lex.get(i+1).equals("[id]") && lex.get(i+2).equals("<SENTS>") && lex.get(i+3).equals("FINPROG")){
                lex.add(i,lex.get(i).replaceAll("REPITE","<SENT>"));
                lex.remove(i+1);
                lex.remove(i+1);
                lex.remove(i+1);
                lex.remove(i+1);
                correcto=1;
            }
            i++;
        }
        return correcto;
    }
    public static void sentSents(ArrayList<String> lex ){       //función que detecta cuando hay más de una sentencia 
        int i =0; 
        
        ArrayList<Integer> linea = new ArrayList<Integer>();
        while(i<lex.size()){
            if (lex.get(i).equals("ENTONCES")||lex.get(i).equals("SINO")||lex.get(i).equals("VECES")) {
                i++;
                if(lex.get(i).equals("<SENT>")){
                     linea.add(i); //agrega el numero de la linea donde se encuentra
                    
                    i++;
                    while(i<lex.size()){
                        if(lex.get(i).equals("<SENT>")){
                             linea.add(i);
                             i++;
                         }
                       
                         else{ 
                            if (lex.get(i).equals("<SENTS>")){
                             linea.add(i);
                             i++;
                            }
                           if((lex.get(i).equals("FINSI")||lex.get(i).equals("FINREP")|lex.get(i).equals("SINO"))&&
                                    (lex.get(i-1).equals("<SENT>")||lex.get(i).equals("<SENTS>")))
                            {
                            
                            lex.set(linea.get(0),"<SENTS>" );
                             band++; //se activa si hubo cambio
                            for (int j = (linea.size()-1); j >0; j--) {
                               int f =linea.get(j);
                                lex.remove(f);
                                 
                             }
                            }
                            linea.clear();

                            break;
                         }

                    }

                } 
            }

            i++;
        }
        
    }
}
