package DiceGame;

import Jama.LUDecomposition;
import Jama.Matrix;
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
        CommutativeDiceProduct c = new CommutativeDiceProduct("c1");
        CommutativeDiceProduct a2 = new CommutativeDiceProduct("a2");
        CommutativeDiceProduct b2 = new CommutativeDiceProduct("b2");
        CommutativeDiceProduct ab = new CommutativeDiceProduct("a1b1");
        CommutativeDiceProduct bc = new CommutativeDiceProduct("b1c1");
        CommutativeDiceProduct ab2 = new CommutativeDiceProduct("a1b2");
        CommutativeDiceProduct a1b3 = new CommutativeDiceProduct("a1b3");
        CommutativeDiceProduct a1b1 = new CommutativeDiceProduct("a1b1");
        CommutativeDiceProduct a3 = new CommutativeDiceProduct("a3");
        CommutativeDiceProduct a4 = new CommutativeDiceProduct("a4");
        CommutativeDiceProduct a2b2 = new CommutativeDiceProduct("a2b2");
        CommutativeDiceProduct a3b3 = new CommutativeDiceProduct("a3b3");
        CommutativeDiceProduct a4b4 = new CommutativeDiceProduct("a4b4");
        CommutativeDiceProduct a5b5 = new CommutativeDiceProduct("a5b5");
        CommutativeDiceProduct a6b6 = new CommutativeDiceProduct("a6b6");
        CommutativeDiceProduct a7 = new CommutativeDiceProduct("a7");
        CommutativeDiceProduct a1c1 = new CommutativeDiceProduct("a1c1");
        CommutativeDiceProduct d = new CommutativeDiceProduct("d1");
        CommutativeDiceProduct c2 = new CommutativeDiceProduct("c2");
        CommutativeDiceProduct a31 = new CommutativeDiceProduct("a31");
        CommutativeDiceProduct b1c1 = new CommutativeDiceProduct("b1c1");
        CommutativeDiceProduct[] cdpArr = new CommutativeDiceProduct[6];
        CommutativeDiceProduct[] cdpArr2 = new CommutativeDiceProduct[4];
        cdpArr[0] = i;
        cdpArr[1] = a;
        cdpArr[2] = b;
        cdpArr[3] = c;
        cdpArr[4] = a2;
        cdpArr[5] = a1b1;
        

        

        /*cdpArr[4] = a2;
        cdpArr[5] = b2;
        cdpArr[6] = ab;
        cdpArr[7] = bc;*/
        DiceProducts test = new DiceProducts(cdpArr);
        //System.out.println(test);
        int n = 10;
       for (int j = 1; j <= n; j++)
            System.out.println("Roll number " + j + " adds " + test.getNthRoll(j).size() + " for " + test.size() + ":\t");
        
        System.out.println(test.size());
        System.out.println(test.totalSize());
        
        
        System.out.print("Standard basis vector: ");
        test.getStdBasisVectorFour().print(3, 3);
        System.out.print("Combinatorics basis vector: ");
        test.getCombBasisVectorFour().print(3,3);
        /*System.out.println();
        for (int j = 1; j <= n; j++)
            System.out.println("Roll number " + j + ":\t" + test2.getNthRoll(j));
        */
    }
}