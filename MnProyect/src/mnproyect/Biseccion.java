/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnproyect;

import java.util.Scanner;

/**
 *
 * @author Ernesto
 */
public class Biseccion {
    static Double resultado;
    static Double x;
    static Double a;
    static Double b;
    static Double tol = 1.732050;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Biseccion bi = new Biseccion();
        System.out.println("ingrese el intervalo menor");
        a = sc.nextDouble();
        System.out.println("ingrese el intervalo mayor");
        b = sc.nextDouble();
        bi.metodo();
    }

    private void metodo() {

        int i = 0;
        x = (a + b) / 2;
        //System.out.println("x: "+x);
        //resultado = funcion(x);
        //System.out.println("resultado: "+resultado);

        while (i < 12) {
            if (resultado > 0) {
                b = x;
            } else {
                a = x;
            }

            x = (a + b) / 2;
            //System.out.println("x: "+x);
            //resultado = funcion(x);
            //System.out.println("resultado: "+resultado);
            i++;

        }

        System.out.println("El resultado es: " + x);
    }
    
    
    
    
    /*
    private void parser(String algo){
            String function="";
            for(int x =0; x < algo.length(); x++){
                char a = algo.charAt(x);
                function = function + a;
                if(a=='('){
                    parser(algo.replace(function, ""));
                }
                if(a == ')'){
                    elige(function.replace(")|(", ""));
                }
            }
        }
        
        private void elige(String algo){
            if(algo.charAt(0) == '-')
                
            try {
                String aux[] = algo.split("+");
                
            } catch (Exception e) {
                
            }
            
            /*
                switch(a){
                    case '+':
                        break;
                       
                    case '*':
                        break;
                              
                    case '-':
                        break;
                    case '/':
                        break;
                }
                }*/
      //      }

    /*public Double funcion(String f) {
        f
        //Double resultado= Math.pow(i,3)-3;
        //return resultado;
    }*/
}
