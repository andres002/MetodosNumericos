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
    private float inicialP;
    private float tol;
    private int n;
    private float p;
    
    public PuntoFijo(){
        this.inicialP = 0;
        this.n = 0;
        this.tol = 0;
    }
        
    public PuntoFijo(float p,float tol,int n){
        this.inicialP = p;
        this.tol = tol;
        this.n = n;
    }
    
    /*public double calculate(){
        int i = 1;
        while (i <= this.n) {            
            this.p = funcion.evauate(inicialP);
            if (Math.abs(this.p - this.inicialP) < this.tol) {
                return this.p;
            }
            i = i+1;
            this.inicialP = this.p;
        }
        System.err.println("‘The method failed after N iterations, N =" + this.n);
        return 0;
    }*/
    

	
}
