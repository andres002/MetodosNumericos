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
public class Secante {
    
   static Double x0,x1,x2;
	static Double resultado, resultado2;


	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Secante sec = new Secante();
		System.out.println("Introduzca el valor inicial de x0");
		x0 = sc.nextDouble();
		System.out.println("Introduzca el valor inicial de x1");
		x1 = sc.nextDouble();

		
		sec.metodoSec();
		
	}

	public void metodoSec(){

		int i=0;
		Double resp;

		while(i<5){
			resultado = funcionSec(x0);
			resultado2 = funcionSec(x1);
			x2 = x1-((x1-x0)/(resultado2-resultado))*resultado2;//NO MODIFICAR!!! ESTA ES LA FUNCION PRINCIPAL DEL METODO
			x0=x1;
			x1 = x2;

			i++;
		}
		System.out.println("El resultado es: "+x1);

	}

	public Double funcionSec(Double i){

		Double resultado = Math.pow(i,3)+2*Math.pow(i,2)+10*i-20;
		return resultado;
	}

}
