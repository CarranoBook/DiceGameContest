/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DiceGame;

import java.util.HashMap;
import java.util.Map;

/**
 * This is an interface for dice products.  Modeled off of the concept of a 
 * free monoid.
 * @author NB <nb@fs.org>
 */
public interface DiceProductInterface extends Comparable, Cloneable{
    
    /**
     * This method returns the number of times a generator element appears
     * in a specific dice product.  Only use if the product is a commutative
     * operator.
     * @param genEle the generator element
     * @return the power of the generator element
     */
    public int getPower(String genEle);
    
    /**
     * Returns the product of the calling element (the multiplier) and the 
     * multiplicand (the passed element): calling * passed.
     * @param multiplicand the multiplicand (right element) of the 
     * monoid operation
     * @return the result of the monoid operation with the calling element and
     * the passed element
     */
    public DiceProductInterface product(DiceProductInterface multiplicand);
    
    /**
     * @return the power of each element as an element in an array.  Only makes
     * sense to use this if there is some coherent ordering on the generator of
     * the monoid
     */
    public int[] toArray();
    
    /**
     * @return a map which maps the elements of the generator to their powers
     * in the dice product.  Only use if the product is a commutative operator.
     */
    public Map toMap();

    public HashMap<String, Integer> getHashMap();
    
}
