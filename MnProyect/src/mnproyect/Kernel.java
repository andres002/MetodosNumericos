/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnproyect;

import java.math.BigDecimal;

/**
 *
 * @author andres_002
 */
public final class Kernel {
    
    public static String conversor(BigDecimal numero ){
        
        BigDecimal x = new BigDecimal(numero.toString());
        BigDecimal signo = Kernel.sign(x);
        int expo = 0;
        x = Kernel.absolute(x);
        if(x.compareTo(new BigDecimal("0")) != 0){
            while(x.compareTo(new BigDecimal("1"))  >= 1){
                x = x.divide(new BigDecimal("10"));
                expo++;
            }
            while(x.compareTo(new BigDecimal("0.1")) < 0.1){
                x = x.multiply(new BigDecimal("10"));
                expo--;
            }
            
        }
        x = x.multiply(signo);
        return "("+x.toString() + "*10^" + expo +")";
    }
    
    
      public static BigDecimal getMantisa(BigDecimal numero ){
        
        BigDecimal mantissa = new BigDecimal(numero.toString());
        BigDecimal signo = Kernel.sign(mantissa);
        int expo = 0;
        mantissa = Kernel.absolute(mantissa);
        if(mantissa.compareTo(new BigDecimal("0")) != 0){
            while(mantissa.compareTo(new BigDecimal("1"))  >= 1){
                mantissa = mantissa.divide(new BigDecimal("10"));
                expo++;
            }
            while(mantissa.compareTo(new BigDecimal("0.1")) < 0.1){
                mantissa = mantissa.multiply(new BigDecimal("10"));
                expo--;
            }
            
        }
        mantissa = mantissa.multiply(signo);
        return mantissa;
    }
      
        public static int getExponente(BigDecimal numero ){
        
        BigDecimal x = new BigDecimal(numero.toString());
        BigDecimal signo = Kernel.sign(x);
        int expo = 0;
        x = Kernel.absolute(x);
        if(x.compareTo(new BigDecimal("0")) != 0){
            while(x.compareTo(new BigDecimal("1"))  >= 1){
                x = x.divide(new BigDecimal("10"));
                expo++;
            }
            while(x.compareTo(new BigDecimal("0.1")) < 0.1){
                x = x.multiply(new BigDecimal("10"));
                expo--;
            }
            
        }
        x = x.multiply(signo);
        return expo;
    }
    
    public static BigDecimal sign(BigDecimal a){
        if(a.compareTo(new BigDecimal("0")) < 0 )
            return new BigDecimal("-1");
        return new BigDecimal("1");
    
    }
    
    public static BigDecimal absolute( BigDecimal a){
        if(a.compareTo(new BigDecimal("0")) < 0 )
            return a.multiply(new BigDecimal("-1"));
        return a.multiply(new BigDecimal("1"));
    }
    
    public static String truncado(String numero, int k){
        int contk = 0;
        String numredo = "";
        for(int i = 0; i < numero.length(); i++){
            if(
                    numero.charAt(i)>='+' ||
                    numero.charAt(i)>='-' ||
                    numero.charAt(i)>='.'
            ){
                numredo+= numero.charAt(i);
            }
            if(numero.charAt(i)>='0' && numero.charAt(i) <='9'){
                contk++;
                if(contk == k){
                    numredo += numero.charAt(i);
                    return numredo;
                }
                numredo+= numero.charAt(i);
            }
        }
        return numredo;
    }
    
    public static String redondeo(String numero, int k){
        int contk = 0;
        String numredo = "";
        for(int i = 0; i < numero.length(); i++){
            if(
                    numero.charAt(i)>='+' ||
                    numero.charAt(i)>='-' ||
                    numero.charAt(i)>='.'
            ){
                numredo+= numero.charAt(i);
            }
            if(numero.charAt(i)>='0' && numero.charAt(i) <='9'){
                contk++;
                if(contk == k){
                    if(numero.charAt(i+1)>='5' && numero.charAt(i+1) <='9'){
                        numredo += numero.charAt(i)+1;
                    }
                    numredo += numero.charAt(i);
                    return numredo;
                }
                numredo += numero.charAt(i);
            }
        }
        return numredo;
    }
}


