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
public class BinaryParser {
    private int[] binaryNumber;
    private double counter;
    private int numberOfBytes;

    public BinaryParser(){
        this.binaryNumber = null;
        this.counter = 0;
        this.numberOfBytes = 0;
    }

    public BinaryParser(int[] binaryArray){
        this.binaryNumber = binaryArray;
        this.counter = 0;
        this.numberOfBytes = 0;
    }

    public double parseInt(){
        this.numberOfBytes = this.binaryNumber.length;
        this.numberOfBytes = numberOfBytes - 1;
        for (int i = 0; i < binaryNumber.length ; i++ ) {
            if (binaryNumber[i] == 1) {
                this.counter = counter + Math.pow(2,this.numberOfBytes);
            }
            this.numberOfBytes--;
        }
        return this.counter;
    }
    
    public void setBinary(int[] binaryArray){
        this.binaryNumber = binaryArray;
    }
}
