package dicegame;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created on Mar 4, 2016.
 *
 * @author Nathan Bleier
 * @author Wes Hampson
 */
public class DiceRolls
{
    private final ArraySet<Integer> combos;
    
    public DiceRolls()
    {
        combos = new ArraySet<>();
    }
    
    public ArraySet<Integer> getCombos()
    {
        return combos;
    }
    
    public void roll(int numRolls, int numSides)
    {
        int[] sideValues = new int[numSides];
        for (int i = 0; i < sideValues.length; i++) {
            sideValues[i] = i + 1;
        }
        
        roll(numRolls, sideValues);
    }
    
    public void roll(int numRolls, int[] sideValues)
    {
        for (int i = 0; i <sideValues.length; i++) {
            combos.add(sideValues[i]);
        }
        
        Iterator<Integer> it;
        ArrayList<Integer> temp;
        for (int i = 1; i < numRolls; i++) {
            it = combos.iterator();
            temp = new ArrayList<>();
            
            while (it.hasNext()) {
                Integer t = it.next();
                for (int k = 0; k < sideValues.length; k++) {
                    temp.add(t * sideValues[k]);
                }
            }
            
            for (Integer j : temp) {
                combos.add(j);
            }
        }
    }
}