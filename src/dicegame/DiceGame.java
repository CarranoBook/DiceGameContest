package dicegame;

import java.util.Iterator;

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
        dr.roll(3, new int[] { 1, 2, 3, 5 });
        
        ArraySet<Integer> combos = dr.getCombos();
        Iterator<Integer> it = combos.iterator();
        
        String result = "";
        while (it.hasNext()) {
            result += it.next() + "\n";
        }
        
        System.out.println(result);
        System.out.println("Number of entries: " + combos.size());
    }
}
