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
public class PuntoFijo {
    
    static Double resultado;
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		PuntoFijo pf = new PuntoFijo();
		System.out.println("Introduzca el valor inicial de x");
		resultado = sc.nextDouble();
		
		pf.metodoPF();
	}

	private void metodoPF(){
		int i=0;

		while(i<15){
			resultado = funcionPF(resultado);
			//System.out.println("El resultado es: "+resultado);
			i++;
		}
		System.out.println("El resultado es: "+resultado);
	}

	private Double funcionPF(Double i){
		//Double resultado= Math.pow(i,3)-3;
		Double resultado = Math.pow(2.718281828,(-1.0)*i);

		return resultado;
	}
	
    
}
