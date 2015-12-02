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
public class NewtonR {
    
    static Double resultado;
    
    public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		NewtonR nr = new NewtonR();
		System.out.println("Introduzca el valor inicial de x");
		resultado = sc.nextDouble();
		
		nr.metodoNR();
	}

	private void metodoNR(){
		int i=0;

		while(i<5){
			resultado = funcionNR(resultado);
			i++;
		}
		System.out.println("El resultado es: "+resultado);
	}

	private Double funcionNR(Double i){
		//Double resultado= Math.pow(i,3)-3;
		//Double resultado = Math.pow(2.718281828,(-1.0)*i);
		Double resultado = Math.pow(i,3)-i-1;
		Double resultado2 = 3*(Math.pow(i,2))-1;

		Double resp = i-(resultado/resultado2);

		return resp;
	}
    
}
