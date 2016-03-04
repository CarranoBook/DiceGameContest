/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dicegamecontest;

import carrano.ArraySet;
import java.util.Iterator;

/**
 *
 * @author NB <nb@fs.org>
 */
public class DiceGameContest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        DiceRolls test = new DiceRolls();
        int n = 6;
        test.roll(n);
        test.getCombos();
        ArraySet<Integer> t = test.getCombos();
        /*Iterator sIt = t.getIterator();
            String result = "";
            while (sIt.hasNext()) {
                result += sIt.next() + "\n";
            }*/
            
        //System.out.println(result);
        System.out.print("Number of entries: ");
        System.out.println(t.size());
        //System.out.println((n*n*n)/2.0 + 2*n*n + 2.5*n + 1);
        
    }
    
}
