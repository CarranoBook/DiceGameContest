package DiceGame;

import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created on Mar 4, 2016.
 *
 * @author Wes Hampson
 */
public class DiceGame
{
    public static void main(String[] args)
    {

        /*
        DiceRolls dr = new DiceRolls();
        dr.roll(2, new int[] { 1, 2, 3, 5, 7});
        //dr.roll(1, 3);
        TreeSet<Long> combos = dr.getCombos();
        Iterator<Long> it = combos.iterator();
        
        
        
        System.out.println(dr);
        System.out.println("Number of entries: " + combos.size());
                */
        
        String test = "a1b2c3";
        CommutativeDiceProduct cdp = new CommutativeDiceProduct(test);
        test = "a3b3c1";
        CommutativeDiceProduct cdp2 = new CommutativeDiceProduct(test);
        CommutativeDiceProduct cdp3 = (CommutativeDiceProduct) cdp.product(cdp2);
        CommutativeDiceProduct identity = new CommutativeDiceProduct("");
        System.out.println(cdp);
        System.out.println(cdp2);
        System.out.println(cdp3);
        System.out.println(identity);
    }
}