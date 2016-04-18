/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DiceGame;

import Jama.LUDecomposition;
import Jama.Matrix;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author NB <nb@fs.org>
 */
public class DiceProducts {
    TreeMap<DiceProductInterface, Integer[]> combos; //Integer[0] = first occurence.  Integer[1] = number of occurences.
    int rollNumber;
    DiceProductInterface[] products;
    
    
    DiceProducts(DiceProductInterface[] products) {
        //initialize combos and rollNumber
        combos = new TreeMap<>();
        rollNumber = 1;
        this.products = products;
        //seed combos with roll # 1
        for (int i = 0; i < products.length; i++) 
            this.put(products[i], rollNumber);
        
    }
    
    
    /**
     * This method generates all of the possible combinations from rolling the dice
     * 'n' times
     * @param n the number of times to roll the dice
     */
    public void roll(int n) {
        while (rollNumber < n)
            this.roll();
    }
            
    /**
     * Private implementation of public {@link #roll(int) roll} method.
     */
    private void roll() {
        DiceProductInterface temp;
        DiceProductInterface tempProd;
        Iterator<DiceProductInterface> tIt;
        TreeSet<DiceProductInterface> tempCombos = new TreeSet<>();
        rollNumber++;

        Iterator<DiceProductInterface> cIt = combos.navigableKeySet().iterator();
        while (cIt.hasNext()) {
            temp = cIt.next();
            
            for (DiceProductInterface side : products) {
                tempProd = temp.product(side);
                //System.out.println("Combos contains: " + tempProd + "? " + combos.containsKey(tempProd));
                tempCombos.add(tempProd);
            }//end for

        }//end while
        
        for (DiceProductInterface newCombo : tempCombos)
            this.put(newCombo, rollNumber);
        
    }//end roll
    
    /**
     * Puts a DiceProductInterface into the TreeMap
     * @param product DiceProductInterface which becomes the key 
     * @param rollNumber the dice roll number 
     */
    private void put(DiceProductInterface product, int rollNumber) {
        Integer[] r = new Integer[2];
        if (!combos.containsKey(product)) {
            r[0] = rollNumber; r[1] = 1;
            combos.put(product, r);
        }
        else
            combos.get(product)[1]++;
    } //end put
    
    
    public String toString() {
        String result = "Combination \t : \t first roll number";
        Iterator<Entry<DiceProductInterface, Integer[]>> cIt = combos.entrySet().iterator();
        while (cIt.hasNext()) {
            Entry<DiceProductInterface, Integer[]> temp = cIt.next();
            result += "\n" + temp.getKey() + "\t : \t " +
                    temp.getValue()[0]; //the first roll number
        }
        return result;
    }
    
    
    /**
     * This method returns a TreeSet containing all of
     * the DiceProductInterfaces which occur on the 'n'th roll
     * @param n the roll whose products are added to the treeset
     * @return TreeSet containing all of
     * the DiceProductInterfaces which occur on the 'n'th roll
     */
    public TreeSet<DiceProductInterface> getNthRoll(int n) {
        this.roll(n);
        Entry<DiceProductInterface, Integer[]> temp;
        TreeSet<DiceProductInterface> result = new TreeSet<>();
        
        Iterator<Entry<DiceProductInterface, Integer[]>> cIt = combos.entrySet().iterator();
        while (cIt.hasNext()) {
            temp = cIt.next();
            if (temp.getValue()[0] == n)
                result.add(temp.getKey());
        }
        return result;
    }
    
    /**
     * @return The number of distinct products
     */
    public int size() {
        return this.combos.size();
    }
    
    /**
     * The sum of the number of occurences of each product
     */
    public long totalSize() {
        Iterator<Entry<DiceProductInterface, Integer[]>> cIt = this.combos.entrySet().iterator();
        Entry<DiceProductInterface, Integer[]> temp;
        long result = 0;
        while (cIt.hasNext()) {
            temp = cIt.next();
            result += temp.getValue()[1];
        }
        return result;
    }
    
    public Matrix getStdBasisVector() {
        if (this.numberOfPrimes() == 2)
            return this.getStdBasisVectorThree();
        else if (this.numberOfPrimes() == 3)
            return this.getStdBasisVectorFour();
        else
            return null;
    }
    
    /**
     * This method returns the polynomial vector in the standard basis representing
     * the unique dice products of  dice with two prime values.  Note that, as of now,
     * it requires the function to behave polynomially beginning by at least roll number 3
     * @return polynomial vector in the standard basis
     */
    public Matrix getStdBasisVectorThree() {
        Matrix result;
        double[] d0 = {9, 3, 1, this.getNthRoll(1).size() + this.getNthRoll(2).size() + this.getNthRoll(3).size()};
        double[] d1 = {16, 4, 1, d0[3] + this.getNthRoll(4).size()};
        double[] d2 = {25, 5, 1, d1[3] + this.getNthRoll(5).size()};
        double[][] d = new double[3][4];
        d[0] = d0; d[1] = d1; d[2] = d2;
        Matrix p = new Matrix(d);
        Matrix k = p.rref();
        result = k.getMatrix(0, 2, 3, 3);//
        
        return result;
    }
    
    /**
     * This method returns the polynomial vector in the combinatorial basis representing
     * the unique dice products of  dice with two prime values.  Note that, as of now,
     * it requires the function to behave polynomially beginning by at least roll number 3
     * @return polynomial vector in the standard basis
     */
    public Matrix getCombBasisVectorThree() {
        double[][] p = new double[3][3];
        double[] p0 = {0, 0, 1};
        double[] p1 = {1, 1, -2};
        double[] p2 = {1, -1, 1};
        p[0] = p0; p[1] = p1; p[2] = p2;
                
        Matrix P3 = new Matrix(p);
        
        Matrix v = this.getStdBasisVectorThree();
        return P3.times(v);
    }
    
    /**
     * This method returns the polynomial vector in the standard basis representing
     * the unique dice products of  dice with three prime values.  Note that, as of now,
     * it requires the function to behave polynomially beginning by at least roll number 3
     * @return polynomial vector in the standard basis
     */
    public Matrix getStdBasisVectorFour() {
        Matrix result;
        double[] d0 = {27, 9, 3, 1, this.getNthRoll(1).size() + this.getNthRoll(2).size() + this.getNthRoll(3).size()};
        double[] d1 = {64, 16, 4, 1, d0[4] + this.getNthRoll(4).size()};
        double[] d2 = {125, 25, 5, 1, d1[4] + this.getNthRoll(5).size()};
        double[] d3 = {216, 36, 6, 1, d2[4] + this.getNthRoll(6).size()};
        double[][] d = new double[4][5];
        d[0] = d0; d[1] = d1; d[2] = d2; d[3] = d3;
        Matrix p = new Matrix(d);
        Matrix k = p.rref();
        result = k.getMatrix(0, 3, 4, 4);//
        
        return result;
    }
    
    /**
     * This method returns the polynomial vector in the combinatorial basis representing
     * the unique dice products of  dice with three prime values.  Note that, as of now,
     * it requires the function to behave polynomially beginning by at least roll number 3
     * @return polynomial vector in the standard basis
     */
    public Matrix getCombBasisVectorFour() {
        double[][] p = new double[4][4];
        double[] p0 = {0, 0, 0, 1};
        double[] p1 = {-1, 3, -1, -1};
        double[] p2 = {4, 0, -2, 3};
        double[] p3 = {3, -3, 3, -3};
        p[0] = p0; p[1] = p1; p[2] = p2; p[3] = p3;
                
        Matrix P4 = new Matrix(p);
        
        Matrix v = this.getStdBasisVectorFour();
        return P4.times(v);
    }
    
    public int numberOfPrimes() {
        throw new UnsupportedOperationException("Not yet implemented");
    }
        
}
