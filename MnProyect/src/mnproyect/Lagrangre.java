/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnproyect;

import java.lang.Math;
import java.io.*;
import java.util.Scanner;

/**
 *
 * @author LuisFernando
 */
public class Lagrangre {

   public static void MetodoInterLagrange(){
		int n,i;
		double x;
                double b = 0;
                double w = 0;
                double z = 0;
                double d = 0;
                String y;
                String j, k, l, t, e;
		double a[][] = new double [4][2];
		System.out.println("\t\t\t\"METODO DE INTERPOLACION LAGRANGE  2-4 PTOS.\"");
		do{
		System.out.print("Dame el numero de Nodos: ");
		n=leeint();	
		}while(n<2 || n>4);
		System.out.print("Dame los  pares de Nodos: ");
		for(i=0;i<n;i++){
				System.out.println("Dame x "+i);
				a[i][0]=lee();
				System.out.println("Dame y "+i);
				a[i][1]=lee();
				}
		//System.out.println("Dame el valor a interpolar ");
		//x=lee();
		switch(n){
                    //(x-a[1][0])*a[0][1])
			case 2:
			y=((a[0][1] + "x + " + (a[0][1] * a[1][0]) +"/" +(a[0][0]-a[1][0]))+  "  ||  " +(a[1][1] + "x + " + (a[1][1] * a[0][0])+"/" +(a[1][0]-a[0][0])));
                        j =  ((a[0][1])/(a[0][0]-a[1][0]) + (a[1][1])/(a[1][0]-a[0][0]) +  "x + " +((a[0][1] * a[1][0])/(a[0][0]-a[1][0]) + (a[1][1] * a[0][0]) / (a[1][0]-a[0][0]) )); 
			System.out.println(j);
			break;
                           
                           //(a[0][0]-a[1][0])*(a[0][0]-a[2][0]);
                            //((a[1][0]-a[0][0])*(a[1][0]-a[2][0]));
                            //((a[2][0]-a[0][0])*(a[2][0]-a[1][0]));
                            //a[0][1]
			case 3:
                           
                            double mar = makeChido(a[0][1],((a[0][0]-a[1][0])*(a[0][0]-a[2][0])));
                            double mer =  makeChido(a[1][1],((a[1][0]-a[0][0])*(a[1][0]-a[2][0])));
                            double mir = makeChido(a[2][1],((a[2][0]-a[0][0])*(a[2][0]-a[1][0])));
			y=(makeChido(a[0][1],((a[0][0]-a[1][0])*(a[0][0]-a[2][0])))+ "x2 + " +  (-a[2][0] + -a[1][0]) + "x + " + (-a[2][0] *- a[1][0]) +  "  ||  " + makeChido(a[1][1],((a[1][0]-a[0][0])*(a[1][0]-a[2][0]))) + "x2 + " +  (a[0][0] + a[2][0]) + "x + " + (a[2][0] * a[0][0]) + "  ||  " + makeChido(a[2][1],((a[2][0]-a[0][0])*(a[2][0]-a[1][0]))) + "x2 + " +  (a[0][0] + a[1][0]) + "x + " + (a[0][0] * a[1][0])  );
			j = (makeChido(a[0][1],((a[0][0]-a[1][0])*(a[0][0]-a[2][0]))) + makeChido(a[1][1],((a[1][0]-a[0][0])*(a[1][0]-a[2][0]))) + makeChido(a[2][1],((a[2][0]-a[0][0])*(a[2][0]-a[1][0])))  + "x2")    ;
                        k= ((mar * (-a[2][0] + -a[1][0])) + ( mer * (-a[0][0] + -a[2][0])) + ( mir * (-a[0][0] + -a[1][0])) + " x");
                        l = ((mar *(-a[2][0] * -a[1][0])) + (mer *(-a[0][0] * -a[2][0])) + (mir *(-a[0][0] * -a[1][0]))  + "");
                        t = (j + "   " + k + "    " + l);
                       
                        System.out.println(t);
			break;
			
                             
                            //;
                            //((a[2][0]-a[0][0])*(a[2][0]-a[1][0])*(a[2][0]-a[3][0]));
                            //((a[3][0]-a[0][0])*(a[3][0]-a[1][0])*(a[3][0]-a[2][0]));
			case 4:
                            b =((a[0][0]-a[1][0])*(a[0][0]-a[2][0])*(a[0][0]-a[3][0]));
                            w  = ((a[1][0]-a[0][0])*(a[1][0]-a[2][0])*(a[1][0]-a[3][0]));
                            z = ((a[2][0]-a[0][0])*(a[2][0]-a[1][0])*(a[2][0]-a[3][0]));
                            d = ((a[3][0]-a[0][0])*(a[3][0]-a[1][0])*(a[3][0]-a[2][0]));
			y=((makeChido(a[0][1],b)) + "x3 + " + (sacaDatosx2(a,0) * makeChido(a[0][1],b)) + "x2 + " + (sacaDatosx1(a,0) * makeChido(a[0][1],b)) + "x + " +   ((-a[3][0] * (-a[1][0] * -a[2][0]) )* makeChido(a[0][1],b) ) + "  ||  " + makeChido(a[1][1],w) + "x3 + " + (sacaDatosx2(a,1) * makeChido(a[1][1],w)) + "x2 + " + (sacaDatosx1(a,1) * makeChido(a[1][1],w)) + "x +" + ((-a[3][0] * (-a[0][0] * -a[2][0]) *makeChido(a[1][1],w)) ) +  "  ||  " + makeChido(a[2][1],z) + "x3 + " + (sacaDatosx2(a,2) *makeChido(a[2][1],z)) + "x2 + " + ((sacaDatosx1(a,2) *makeChido(a[2][1],z)) + "x + " +  ((-a[3][0] * (-a[0][0] * -a[1][0]) * makeChido(a[2][1],z)) ) +  "  ||  " + makeChido(a[3][1],d) + "x3 + " + (sacaDatosx2(a,3)* makeChido(a[3][1],d)) + "x2 + " + (sacaDatosx1(a,3) * makeChido(a[3][1],d)) + "x + " + " + " + ((-a[2][0] * (-a[0][0] * -a[1][0]) )* makeChido(a[3][1],d)) +" " ));
                            
                        j = ((makeChido(a[0][1], b)) + (makeChido(a[1][1],w)) + (makeChido(a[2][1],z)) + (makeChido(a[3][1],d)) + "x3");
                        k = ((sacaDatosx2(a,0)*  makeChido(a[0][1], b)) + (sacaDatosx2(a,1) * makeChido(a[1][1],w)) + (sacaDatosx2(a,2) * makeChido(a[2][1],z)) + (sacaDatosx2(a,3) * makeChido(a[3][1],d)) + "x2");
                        l = ((sacaDatosx1(a,0) * makeChido(a[0][1], b)) + (sacaDatosx1(a,1) *  makeChido(a[1][1],w)) + (sacaDatosx1(a,2) *makeChido(a[2][1],z)) + (sacaDatosx1(a,3)  * makeChido(a[3][1],d)) + "x");
                        e = (((-a[2][0] * (-a[1][0] * -a[3][0])*makeChido(a[0][1], b) ))+ ((-a[2][0] * (-a[0][0] * -a[3][0]) )*  makeChido(a[1][1],w) )+ ((-a[1][0] * (-a[0][0] * -a[3][0]) ) *makeChido(a[2][1],z)) +((-a[2][0] * (-a[0][0] * -a[1][0]) )* makeChido(a[3][1],d)) + "");
                        t  = (j + "    " + k + "    " + l + "   " + e);
                        
                        System.out.println(makeChido(a[0][1], b));
                        System.out.println(sacaDatosx1(a,0));
                        System.out.println((sacaDatosx1(a,0)* makeChido(a[0][1], b)));
                        System.out.println(makeChido(a[1][1], w));
                        System.out.println(sacaDatosx1(a,1));
                        System.out.println((sacaDatosx1(a,1)* makeChido(a[1][1], w)));
                        System.out.println(makeChido(a[2][1], z));
                        System.out.println(sacaDatosx1(a,2));
                        System.out.println((sacaDatosx1(a,2)* makeChido(a[2][1], z)));
                        System.out.println(makeChido(a[3][1], d));
                        System.out.println(sacaDatosx1(a,3));
                        System.out.println((sacaDatosx1(a,3)* makeChido(a[3][1], d)));
                        
                        System.out.println(t);
                        //System.out.println((sacaDatosx2(a,0)* makeChido(a[0][1], b)));
			break;
			default:
			System.out.println("INVALIDO");
			break;
			
		}
                        
	}
   public static double sacaDatosx2(double a[][],int g){
       double fiinal = 0;
       if(g == 0){
           double f = -a[1][0]  -a[2][0];
            fiinal = f  -a[3][0] ;
      }
       if(g == 1){
            double f = -a[0][0]  -a[2][0];
            fiinal = f  -a[3][0] ;
       }
       if(g == 2){
            double f = -a[0][0]  -a[1][0];
            fiinal = f  -a[3][0] ;
       }
       if(g == 3){
            double f = -a[0][0]  -a[1][0];
            fiinal = f  -a[2][0] ;
       }
       else{
           
       }
       return fiinal;
       
   }
   
   public static double sacaDatosx1(double a[][],int g){
       double fiinal = 0;
       if(g == 0){
           double f = -a[1][0] * -a[2][0];
            fiinal = f +(-a[3][0] * ((-a[1][0] + -a[2][0])))  ;
      }
       if(g == 1){
            double f = -a[0][0] - a[1][0];
            fiinal = f + (-a[3][0] * ((-a[0][0] -a[2][0])));
       }
       if(g == 2){
            double f = -a[0][0]  -a[1][0];
            fiinal =  f + (-a[3][0] * (-a[0][0]  -a[2][0])) ;
       }
       if(g == 3){
            double f = -a[0][0]  -a[1][0];
            fiinal =  f + (-a[3][0] * (-a[0][0]  -a[2][0])) ;
       }
       else{
           
       }
       return fiinal;
       
   }
   
   
   public static double makeChido(double y, double d){
       double r = 0;
       double t = 0;
       
       r = 1/d;
       t = r*y;
       return t;
   }

	public static double lee(){
		double num;
		try{
			InputStreamReader isr = new InputStreamReader (System.in);
			BufferedReader br = new BufferedReader(isr);
			String sdato;
			sdato = br.readLine();
			num = Double.parseDouble(sdato);
		}
		catch(IOException ioe){
			num=0.0;
		}
		return num;
		}

	public static int leeint(){
		int num;
		try{
			InputStreamReader isr = new InputStreamReader (System.in);
			BufferedReader br = new BufferedReader(isr);
			String sdato;
			sdato = br.readLine();
			num = Integer.parseInt(sdato);
		}
		catch(IOException ioe){
			num=0;
		}
		return num;
		}
	

	

/*
        public  void resulucionX(int x,double a[][]){
            if(x == 2){
               String h =  a[1][1] + "x"  + suma2(a);
            }
            if(x == 3){
                String j = a[]
            }
            
            
        }
        public double suma2(double a[][]){
            
            double r = a[0][1] * a[0 ][0];
            return r;
        }
            
            
        
        
       
        
    
    /**
     *
     */
    /*public static void main(String args[]){
        
        MetodoInterLagrange();
        
    }*/
    
   }


   

