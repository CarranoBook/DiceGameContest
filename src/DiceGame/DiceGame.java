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
        CommutativeDiceProduct i = new CommutativeDiceProduct("");
        CommutativeDiceProduct a = new CommutativeDiceProduct("a1");
        CommutativeDiceProduct b = new CommutativeDiceProduct("b1");
        CommutativeDiceProduct a2 = new CommutativeDiceProduct("a2");
        CommutativeDiceProduct ab = new CommutativeDiceProduct("a1b1");
        CommutativeDiceProduct ba = new CommutativeDiceProduct("b1a1");
        CommutativeDiceProduct[] cdpArr = new CommutativeDiceProduct[4];
        cdpArr[0] = i;
        cdpArr[1] = a;
        cdpArr[2] = b;
        cdpArr[3] = a2;
        DiceProducts test = new DiceProducts(cdpArr);
        System.out.println(test);
        test.roll(200);
        System.out.println(test.size());
        
    }
}