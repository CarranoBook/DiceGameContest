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
        DiceRolls dr = new DiceRolls();
        //dr.roll(3, new int[] { 1, 2, 3, 5 });
        dr.roll(23, 3);
        TreeSet<Long> combos = dr.getCombos();
        Iterator<Long> it = combos.iterator();
        
        
        
        System.out.println(dr);
        System.out.println("Number of entries: " + combos.size());
    }
}