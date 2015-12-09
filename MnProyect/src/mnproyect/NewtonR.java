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
    private double inicialP;
    private double tol;
    private int n;
   // private Funcion f;
    //private Funcion derivada;
    private double p;
    
    
   /* public NewtonR(){
        
    }
    
    public NewtonR(double inicialP, double tol, int n, Funcion f,Funcion derivada){
        this.inicialP = inicialP;
        this.tol = tol;
        this.n = n;
        this.f = f;
        this.derivada = derivada;
    }
    
    public double coreFuncion(){
        int i = 1;
        while (i <= this.n) {
            this.p = this.inicialP - f.evaluate(this.inicialP)/derivada.evaluate(this.inicialP);
            if (Math.abs(this.p - this.inicialP) < this.tol) {
                return this.p;
            }
            i = i+1;
            this.inicialP = this.p;
        }
        System.out.println("(‘The method failed after N0 iterations, N0 =" + this.n);
        return 0;
    }*/
    
    
    
    
    
}


/*static Double resultado;
    
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
	}*/
