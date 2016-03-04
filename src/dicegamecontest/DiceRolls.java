/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dicegamecontest;

import carrano.ArraySet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
/**
 *
 * @author NB <nb@fs.org>
 */
public class DiceRolls {
    private ArraySet<Integer> dice;
    private ArraySet<Integer> combos;
    
    public DiceRolls() {
        this.dice = new ArraySet<>();
        combos = new ArraySet<>();
        dice.add(1);
        dice.add(2);
        dice.add(3);
    }
    
    //rolls n dice
    public void roll(int n) {
        for (int i = 1; i <= dice.size(); i++) {
            combos.add(i);
        }
        
        
        for (int i = 1; i < n; i++) {
            Iterator cIt = combos.getIterator();
            ArrayList<Integer> temp = new ArrayList<Integer>();
            while (cIt.hasNext()) {
                Integer t = (Integer) cIt.next();
                temp.add(t);
                temp.add(t*2);
                temp.add(t*3);
                //temp.add(t*4);
                //temp.add(t*5);
                //temp.add(t*6);
            }
            for (Integer j : temp)
                combos.add(j);   
        }
    }
    
    public ArraySet<Integer> getCombos() {
        return combos;
    }
    
}
