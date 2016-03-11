/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DiceGame;

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
    TreeMap<DiceProductInterface, Integer> combos;
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
    
    public void roll(int n) {
        while (rollNumber < n)
            this.roll();
    }
            
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
                if (!combos.containsKey(tempProd))
                    tempCombos.add(tempProd);
            }//end for

        }//end while
        
        for (DiceProductInterface newCombo : tempCombos)
            combos.put(newCombo, rollNumber);
        
    }//end roll
    
    /**
     * Puts a DiceProductInterface into the TreeMap
     * @param product DiceProductInterface which becomes the key 
     * @param rollNumber the dice roll number 
     */
    private void put(DiceProductInterface product, int rollNumber) {
        if (!combos.containsKey(product))
            combos.put(product, rollNumber);
    }
    
    public String toString() {
        String result = "Combination \t : \t roll number";
        Iterator<Entry<DiceProductInterface, Integer>> cIt = combos.entrySet().iterator();
        while (cIt.hasNext()) {
            Entry<DiceProductInterface, Integer> temp = cIt.next();
            result += "\n" + temp.getKey() + "\t : \t " +
                    temp.getValue();
        }
        
        
        return result;
    }
    
    public TreeSet<DiceProductInterface> getNthRoll(int n) {
        this.roll(n);
        Entry<DiceProductInterface, Integer> temp;
        TreeSet<DiceProductInterface> result = new TreeSet<>();
        
        Iterator<Entry<DiceProductInterface, Integer>> cIt = combos.entrySet().iterator();
        while (cIt.hasNext()) {
            temp = cIt.next();
            if (temp.getValue() == n)
                result.add(temp.getKey());
        }
        return result;
    }
    
    public int size() {
        return this.combos.size();
    }
    
    
}
