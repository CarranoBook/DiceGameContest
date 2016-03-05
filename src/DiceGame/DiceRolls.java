package DiceGame;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

/**
 * Created on Mar 4, 2016.
 *
 * @author Nathan Bleier
 * @author Wes Hampson
 */
public class DiceRolls
{
    private final TreeSet<Long> combos;
    
    /**
     * Default constructor
     */
    public DiceRolls()
    {
        combos = new TreeSet<>();
    }
    
    /**
     * Accessor method for combos
     * @return 
     */
    public TreeSet<Long> getCombos()
    {
        return combos;
    }
    
    /**
     * This method generates the distinct products possible when rolling
     * a die with sides 1 through numSides numRolls number of times.
     * @param numRolls the number of times to roll the die
     * @param numSides the number of sides 1...numSides of the die
     */
    public void roll(int numRolls, int numSides)
    {
        // initialize and generate the number array
        int[] sideValues = new int[numSides];
        for (int i = 0; i < sideValues.length; i++) {
            sideValues[i] = i + 1;
        }
        
        roll(numRolls, sideValues);
    }
    
    /**
     * Private method that encapsulates the logic made public in 
     * {@link #roll(int, int) roll} method
     * @param numRolls
     * @param sideValues 
     */
    private void roll(int numRolls, int[] sideValues)
    {
        //Add the initial die sides to combos
        for (int i = 0; i <sideValues.length; i++) {
            combos.add((long) sideValues[i]);
        }
        
        
        Iterator<Long> it;
        ArrayList<Long> temp;
        //Roll the die numRolls number of times
        for (int i = 1; i < numRolls; i++) {
            it = combos.iterator(); //TreeSet Iterator is fail-fast.
            //It will throw a ConcurrentModificationException
            temp = new ArrayList<>();
            //Finds new values to put into combos by multiplying current
            //elements of combos by each element of sideValues
            while (it.hasNext()) {
                Long t = it.next();
                for (int k = 0; k < sideValues.length; k++) {
                    Long add = t * sideValues[k];
                    if (!combos.contains(add))
                        temp.add(add);
                } //end for
            } //end while
            
            //adds the newly found combinations to combos
            for (Long j : temp)
                combos.add(j);
        
        } //end for
    } //end roll
    
    @Override
    public String toString() {
        String result = "";
        Iterator it = combos.iterator();
        while (it.hasNext()) {
            result += it.next() + "\n";
        }
        return result;
    }
}