/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DiceGame;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author NB <nb@fs.org>
 */
public class IntegerDiceProduct implements DiceProductInterface {
    long value;
    
    public IntegerDiceProduct() {
        this(1L);
    }
    
    public IntegerDiceProduct(long value) { this.value = value;}
    
    public long getValue() {return value;}
    
    @Override
    public int getPower(String genEle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public DiceProductInterface product(DiceProductInterface multiplicand) {
        //Check class of DiceProductInterface
        if (multiplicand.getClass() != IntegerDiceProduct.class) {
            throw new IllegalArgumentException("Argument must be an IntegerDiceProduct");
        }
        
        //Cast the multiplicand
        IntegerDiceProduct m = (IntegerDiceProduct) multiplicand;
        //Returns the product
        long j = this.value * m.getValue();
        return new IntegerDiceProduct(j);
    }

    @Override
    public int[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map toMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HashMap<String, Integer> getHashMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Object o) {
        if (o.getClass() != IntegerDiceProduct.class) {
            throw new IllegalArgumentException("Argument must be an IntegerDiceProduct");
        }
        
        //Cast the multiplicand
        IntegerDiceProduct m = (IntegerDiceProduct) o;
        long ov = m.getValue();
        if (ov > this.value) 
            return -1;
        else if (ov < this.value)
            return 1;
        else
            return 0;
    }
}
