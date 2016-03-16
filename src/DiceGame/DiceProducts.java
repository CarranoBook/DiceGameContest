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
}
