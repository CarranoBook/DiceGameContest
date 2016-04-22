package DiceGame;

import java.io.IOException;

/**
 * Created on Mar 4, 2016.
 *
 * @author Wes Hampson, Nathan Bleier
 */
public class DiceGame
{
    public static void main(String[] args)
    {
        int[] diceValues = { 1, 2, 3, 4, 6 };
        
        int nRolls = 15;
        int rStart = 1000;
        int rEnd = 5000;
        
        ProbabilityFinder pf = new ProbabilityFinder(diceValues, nRolls + 1, rStart, rEnd);
        double[] probs = pf.getProbs();
       
        CSVWriter csv = null;
        try {
            csv = new CSVWriter("data.csv");
            csv.writeRow("roll", "probability");
            for (int i = 1; i < probs.length; i++) {
                csv.writeRow(i, probs[i]);
                System.out.println(probs[i]);
            }
        } catch (IOException ex) {
            System.err.printf("Error writing file! [%s: %s]\n",
                    ex.getClass().getName(), ex.getMessage());
        } finally {
            try {
                if (csv != null) {
                    csv.close();
                }
            } catch (IOException ex) {
                System.err.printf("Error closing file! [%s: %s]\n",
                    ex.getClass().getName(), ex.getMessage());
            }
        }
    }
}
