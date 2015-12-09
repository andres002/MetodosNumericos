/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mnproyect;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.Stack;

/**
 *
 * @author andres_002
 */
public class Parser {

    //2. truncamiento, 1.- redondeo, 3.- Todos los digitos
    public String function = "";
    int k = 6;
    public int opcion = 1;
    double value = 0.0;
    public String X = "";

    public void cambiavariables() {

    }
    /**
     * Algoritmo de cambio de expresiones de infijo a postfijo
     * @param expr String con la expresion infijo
     * @return String expresion postfijo
     */
    
    /* ALGORITMO DE CAMBIO DE EXPRESIONES DE INFIJO A POSTFIJO */
    public String Postfijo(String expr) {
        String[] arrayInfix = expr.split(" ");

        //Declaración de las pilas
        Stack< String> E = new Stack< String>(); //Pila entrada
        Stack< String> P = new Stack< String>(); //Pila temporal para operadores
        Stack< String> S = new Stack< String>(); //Pila salida

        //Añadir la array a la Pila de entrada (E)
        for (int i = arrayInfix.length - 1; i >= 0; i--) {
            E.push(arrayInfix[i]);
        }

        try {
            //Algoritmo Infijo a Postfijo
            while (!E.isEmpty()) {
                switch (orden(E.peek())) {
                    case 1:
                        P.push(E.pop());
                        break;
                    case 3:
                    case 4:
                    case 5:
                    case 6:
                    case 7:
                    case 8:
                    case 9:
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                        while (orden(P.peek()) >= orden(E.peek())) {
                            S.push(P.pop());
                        }
                        P.push(E.pop());
                        break;
                    case 2:
                        while (!P.peek().equals("(")) {
                            S.push(P.pop());
                        }
                        P.pop();
                        E.pop();
                        break;
                    default:
                        S.push(E.pop());
                }
            }

            //Eliminacion de `impurezas´ en la expresiones algebraicas
            String infix = expr.replace(" ", "");
            String postfix = S.toString().replaceAll("[\\]\\[,]", "");

            //Mostrar resultados:
            System.out.println("Expresion Infija: " + infix);
            System.out.println("Expresion Postfija: " + postfix);
            return postfix;

        } catch (Exception ex) {
            System.out.println("Error en la expresión algebraica");
            System.err.println(ex);
        }
        return "";
    }

    /**
     * Metodo que cambia las variabes por su valor real
     * @return String con la expresion infijo
     */
    public String depurar() {
        String s = "";
        //this.function = this.function.replace("x", this.X + "");
        s = this.function;
        s = s.replace("x", this.X + "");
        s = s.replaceAll("pi", "3.141592653589793");
        s = s.replaceAll("e", "2.71828182846");
        s = s.replaceAll("\\s+", ""); //Elimina espacios en blanco
        s = "(" + s + ")";
        String simbols = "+*-/()^";
        String str = "";
        String aux = "";
        String aux2 = "";
        //String trig = "sincostanloglnseccsccot";

        //Deja espacios entre operadores
        for (int i = 0; i < s.length(); i++) {

            try {
                if (simbols.contains("" + s.charAt(i))) {
                    str += " " + s.charAt(i) + " ";
                } else {
                    aux2 = "" + s.charAt(i) + s.charAt(i + 1);
                    //System.out.println("" + s.charAt(i)+ s.charAt(i+1) );
                    if (aux2.equals("ln")) {
                        i++;
                        str += " " + aux2 + " ";
                    } else {

                        aux = "" + s.charAt(i) + s.charAt(i + 1) + s.charAt(i + 2);
                        if ("sin".equals(aux) || "cos".equals(aux) || "tan".equals(aux) || "log".equals(aux)
                                || "sec".equals(aux) || "csc".equals(aux) || "cot".equals(aux)) {
                            i = i + 2;
                            str += " " + aux + " ";

                        } else {
                            System.out.println(s.charAt(i));
                            str += s.charAt(i);
                        }
                    }
                }
            } catch (Exception e) {
                str += s.charAt(i);
            }

// str += s.charAt(i);
        }
        return str.replaceAll("\\s+", " ").trim();
    }

        
    /**
     * Metodo de apoyo para el metodo postfijo
     * @param signo String con el signo deseado
     * @return Retorna el equivalente al String indicado
     */
    private static int orden(String signo) {
        switch (signo) {
            case "cot":
                return 13;
            case "csc":
                return 12;
            case "sec":
                return 11;
            case "ln":
                return 10;
            case "log":
                return 9;
            case "tan":
                return 8;
            case "cos":
                return 7;
            case "sin":
                return 6;
            case "^":
                return 5;
            case "/":
                return 4;
            case "*":
                return 4;
            case "+":
                return 3;
            case "-":
                return 3;
            case ")":
                return 2;
            case "(":
                return 1;
            default:
                return 99;
        }
    }
    
    /**
     * Metodo que verifica que los parentesis de la expresion sean correctos
     * @return true en caso de que la sintaxis sea correcta, de lo contrario retorna false
     */
    public boolean verifica_parentesis() {
        String dato = function;
        Stack<String> cadena = new Stack<String>();
        boolean correcto = true;
        char c;
        boolean entrada = false;
        for (int x = 0; x < dato.length(); x++) {
            c = dato.charAt(x);
            if (c == '(') {
                cadena.push(c + "");
                entrada = true;
            }
            if (c == ')') {
                if (cadena.isEmpty()) {
                    return false;
                } else {
                    cadena.pop();
                }
            }
        }
        if (cadena.isEmpty() != true) {
            return false;
        }
        return true;
    }

    /**
     * Metodo que devuelve el resultado de la evaluacion de la ecuacion
     * @param expr String con la expresion postfija
     * @return String con el resultado de la evaluacion
     */
    public String Resultado(String expr) {
        expr = expr.replaceAll("m", "-");
        String[] post = expr.split(" ");

        //Declaración de las pilas
        Stack< String> E = new Stack< String>(); //Pila entrada
        Stack< String> P = new Stack< String>(); //Pila de operandos

        //Añadir post (array) a la Pila de entrada (E)
        for (int i = post.length - 1; i >= 0; i--) {
            E.push(post[i]);
        }

        //Algoritmo de Evaluación Postfija
        String operadores = "+*-/^";
        String operadores2 = "sincostanloglnseccsccot";
        while (!E.isEmpty()) {
            if (operadores.contains("" + E.peek())) {
                P.push(evaluar(E.pop(), P.pop(), P.pop()) + "");
            } else {
                if (operadores2.contains(E.peek())) {
                    P.push(evaluar(E.pop(), P.pop()));
                } else {
                    P.push(E.pop());
                }

            }
        }

        //Mostrar resultados:
        System.out.println("Expresion: " + expr);
        System.out.println("Resultado: " + P.peek());
        return P.peek();

    }

    /**
     * Metodo que se encarga de realizar la operacion matematica dependiendo de los operadores que contenga la expresion
     * @param String op
     * @param String n2
     * @param String n1
     * @return BigDecimal
     */
    private BigDecimal evaluar(String op, String n2, String n1) {

        BigDecimal num1 = new BigDecimal(redonTrunc(n1));
        BigDecimal num2 = new BigDecimal(redonTrunc(n2));
        //BigDecimal resultado = new BigDecimal("0");
        //int num1 = Integer.parseInt(n1);
        //int num2 = Integer.parseInt(n2);
        if (op.equals("+")) {
            BigDecimal resultado = new BigDecimal(redonTrunc(num1.add(num2) + ""));
            return resultado;
        }
        if (op.equals("-")) {
            BigDecimal resultado = new BigDecimal(redonTrunc(num1.subtract(num2) + ""));
            return resultado;
        }
        if (op.equals("*")) {
            BigDecimal resultado = new BigDecimal(redonTrunc(num1.multiply(num2) + ""));
            return resultado;
            //return (num1 * num2);
        }
        if (op.equals("/")) {
            MathContext m;
            if (opcion == 1) {
                m = new MathContext(k, RoundingMode.HALF_EVEN);//redondeo
            } else {
                m = new MathContext(k, RoundingMode.DOWN);//truncamiento
            }

            BigDecimal resultado = new BigDecimal(redonTrunc(num1.divide(num2, m) + ""));
            return resultado;
            //return (num1 / num2);
        }
        if (op.equals("^")) {
            double aux = Math.pow(num1.doubleValue(), num2.doubleValue());
            BigDecimal resultado = new BigDecimal(redonTrunc(aux + ""));

            return resultado;
        }
        BigDecimal resultado = new BigDecimal("0");
        return resultado;
    }
    
    /**
     * Metodo que selecciona la expresion matematica a usar
     * @param String op
     * @param String n1
     * @return String con la expresion
     */

    private String evaluar(String op, String n1) {
        n1 = redonTrunc(n1);
        double num1 = Double.parseDouble(n1);
        //int num2 = Integer.parseInt(n2);
        switch (op) {
            case "cot":
                return redonTrunc((Double.parseDouble(redonTrunc(Math.cos(num1) + "")) / Double.parseDouble(redonTrunc(Math.sin(num1) + ""))) + "");
            case "csc":
                return redonTrunc(1 / Double.parseDouble(redonTrunc(Math.sin(num1) + "")) + "");
            case "sec":
                return redonTrunc(1 / Double.parseDouble(redonTrunc(Math.cos(num1) + "")) + "");
            case "ln":
                return redonTrunc(Math.log(num1) + "");
            case "log":
                return redonTrunc(Math.log10(num1) + "");
            case "tan":
                return redonTrunc(Math.tan(num1) + "");
            case "cos":
                return redonTrunc(Math.cos(num1) + "");
            case "sin":
                return redonTrunc(Math.sin(num1) + "");
        }

        return "";
    }
    
    /**
     * Metodo que redondea o trunca la expresion dependiendo la opcion seleccionada
     * @param numero String con el numero a redondear, truncar o dejar intacto
     * @return String con el numero expresado correctamente 
     */
    public String redonTrunc(String numero) {
        //2. truncamiento, 1.- redondeo, 3.- Todos los digitos

        BigDecimal b = new BigDecimal(numero);
        String aux = "";
        if (numero.contains(".")) {
            switch (opcion) {
                case 1:
                    aux = b.setScale(k, RoundingMode.HALF_EVEN) + "";
                    return aux;

                case 2:
                    aux = b.setScale(k, RoundingMode.DOWN) + "";
                    return aux;

                default:
                    return numero;

            }
        }
        return numero;
    }

}
