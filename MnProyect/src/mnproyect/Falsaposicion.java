/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnproyect;
/**
 *
 * @author Karlos
 */
public class Falsaposicion {
    
    float p0;
    float p1;
    float tol;
    int n;
    
    public Falsaposicion(){
        p0 = 0;
        p1 = 0;
        tol = 0;
        n = 2;
    }
    
    public Falsaposicion(float p0, float p1, float tol, int n){
        if(n < 2){
            ArithmeticException exception = new ArithmeticException();
            throw exception; 
        }else{
            this.n = n;
        }
        this.p0 = p0;
        this.p1 = p1;
        this.tol = tol;
    }
    /*
    public float evaluate(Funcion f){
        float p;
        float q;
        int i = 2;
        float q0 = f.evaluate(p0);
        float q1 = f.evaluate(p1);
        while (i <= this.n) {            
            p = this.p1 - q1*(p1 - p0)/(q1 - q0);
            if (Math.abs(p - p1) < this.tol) {
                return p;
            }
            i = i +1;
            q = f.evaluate(p);
            if ((q * q1) < 0) {
                this.p0 = p1;
                q0 = q1;
            }else{
                this.p1 = p;
                q1 = q;
            }
        }
        System.err.println("‘Method failed after N iterations, N0 =" + n);
        return 0;   
    }*/
    
}
