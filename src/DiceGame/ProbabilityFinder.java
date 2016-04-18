/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DiceGame;

import java.util.TreeMap;

/**
 *
 * @author NB <nb@fs.org>
 */
public class ProbabilityFinder {
    int[] dice;
    double[] probs;
    int[][] map; //1st index: the value.  2nd index: the number of times it occurs at the nth roll
    int rolls;
    int rollcount;
    int rStart;
    int rEnd;
    
    
    /**
     * This class simply counts the probability of rolling within a range on 1 through n dice of arbitrary integer sides
     * @param dice is an integer array containing the sides of the dice
     * @param rolls the number of dice to roll
     * @param rStart the left bound of the range (inclusive)
     * @param rEnd  the right bound of the range (inclusive)
     */
    public ProbabilityFinder(int[] dice, int rolls, int rStart, int rEnd) {
        this.dice = dice;
        this.rolls = rolls;
        this.rStart = rStart;
        this.rEnd = rEnd;
        initializeMap();
        executeRolls();
        this.generateProbabilities();
    }
    
    public double[] getProbs() {
        return probs;
    }
    
    private void initializeMap() {
        map = new int[rEnd+1][rolls+1];
        for (int d : dice)
            if (d < map.length)
                map[d][1] = 1;
        int rollcount = 1;
    }
    
    private void roll() {
        rollcount++;
        for (int i = 1; i <= rEnd; i++) {
            for (int j : dice) {
                int b = i * j; //b is the product of the dice face with each number <= rEnd
                if (b < map.length)
                    map[b][rollcount] += map[i][rollcount-1]; //increment the number of times b is rolled on the nth roll by the number of times i occured on the n-1th roll.
            }
        }
    }
    
    private void executeRolls() {
        for (int i = 1; i<rolls; i++)
            roll();
    }
     
    
    private int[] countOccurrences() {
        int[] occurrences = new int[rolls+1]; // occurrences[j] is the number of ways to roll within the roll range on the jth roll.
        int sum;
        for (int i = 1; i <= rolls; i++) {
            sum = 0;
            for (int j = rStart; j<=rEnd; j++) {
                sum += map[j][i];
            }
            occurrences[i] = sum;
        }
        return occurrences;
    }
    
    private void generateProbabilities() {
        int[] occurrences = countOccurrences();
        double[] probabilities = new double[occurrences.length];
        
        for (int i = 1; i <= rolls; i++)
            probabilities[i] = occurrences[i] / (Math.pow(dice.length, i));
        
        probs =  probabilities;
    }
    
    
}
