/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DiceGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author NB <nb@fs.org>
 */
public class CommutativeDiceProduct
            implements DiceProductInterface {
    private final String identity = "1";
    private HashMap<String, Integer> table;
    int INITIAL_DEFAULT_HASH_SIZE = 6;
    //How can we group dice products so they don't each have to have a generator...
    
    /**
     * Default Constructor
     */
    public CommutativeDiceProduct() {
        table = new HashMap<>(INITIAL_DEFAULT_HASH_SIZE, 1);
    }
    
    public CommutativeDiceProduct(String initialValue) {
        table = stringToHashMap(initialValue);
    }
        
    private CommutativeDiceProduct(HashMap<String, Integer> result) {
        this.table = result;
    }
    
    @Override
    public int getPower(String genEle) {
        return table.get(genEle);
    }

    @Override
    public DiceProductInterface product(DiceProductInterface multiplicand) {
        HashMap<String, Integer> result = new HashMap<>(INITIAL_DEFAULT_HASH_SIZE);
        HashMap<String, Integer> other = multiplicand.getHashMap();
        Iterator<Entry<String, Integer>> tIt = table.entrySet().iterator();
        while (tIt.hasNext()) {
            Entry<String, Integer> temp = tIt.next();
            result.put(temp.getKey(), temp.getValue());
        } //end while
        
        tIt = other.entrySet().iterator();
        while (tIt.hasNext()) {
            Entry<String, Integer> temp = tIt.next();
            if (result.containsKey(temp.getKey())) {
                int c = result.get(temp.getKey());
                result.put(temp.getKey(), c +  temp.getValue());
            }
            else
                result.put(temp.getKey(), temp.getValue());
        } //end while
        
        return new CommutativeDiceProduct(result);
        
    }

    @Override
    public int[] toArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private String[] keyArray() {
        Iterator<String> okIt = table.keySet().iterator();
        String[] arr = new String[table.size()];
        int i = 0;
        while (okIt.hasNext())
            arr[i++] = okIt.next();
        
        Arrays.sort(arr); //Now array is sorted!
        return arr;
    }

    @Override
    public Map toMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Object o) {
        CommutativeDiceProduct other = (CommutativeDiceProduct) o;
        String[] arr = other.keyArray();
        String[] arrThis = this.keyArray();
        
        for (int i = 0; i < arr.length; i++) {
            if (arrThis[i].compareTo(arr[i]) < 0) // This element is before other element and so "less than"
                return -1;
            else if (arrThis[i].compareTo(arr[i]) > 0) //This element is after other element, and so "greater than"
                return 1;
            else {
                if (this.table.get(arrThis[i]) < other.table.get(arr[i])) //Less of this element in this , so "less than"
                    return - 1;
                else if (this.table.get(arrThis[i]) > other.table.get(arr[i]))
                    return 1;
            }
        }
        
        return 0;
    }
    
    @Override
    public String toString() {
        if (this.table.isEmpty())
            return identity;
        Iterator<Entry<String, Integer>> it = table.entrySet().iterator();
        ArrayList<String> results = new ArrayList<>();
        Entry<String, Integer> temp;
        while (it.hasNext()) {
            temp = it.next();
            results.add(temp.getKey() + "^" + temp.getValue());
        }
        Collections.sort(results);
        String result = "";
        Iterator<String> sit = results.listIterator();
        while (sit.hasNext())
            result += sit.next() + " ";
        
        return result;
    }

    private boolean verifyDecomp(String[] gen) {
        String regex = "[a-z][0-9]++";
        Pattern pattern = Pattern.compile(regex);
        for (String s : gen) {
            Matcher matcher = pattern.matcher(s);
            if (!matcher.matches())
                return false;
        }
        return true;
    }

    /**
     * This method takes a string, checks to see if it is formated correctly,
     * and then extracts the primes and their powers from the string and
     * places them in a HashMap
     * @param initialValue
     * @return 
     */
    private HashMap<String, Integer> stringToHashMap(String inputString) {
        //verify the string is formatted correctly       
        HashMap<String, Integer> result = new HashMap<>(INITIAL_DEFAULT_HASH_SIZE, 1);
        if (inputString == "")
            return result;
        String regex = "([a-z])([0-9]++)";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(inputString);
         while (!m.hitEnd()) {
            m.find();
            String k = m.group(1);
            Integer v = Integer.parseInt(m.group(2));
            if (result.containsKey(k)) {
                int c = result.get(k);
                result.put(k, v+c);
            }
            else
                result.put(k, v);
        } //end while
  
        return result;
    }

    @Override
    public HashMap<String, Integer> getHashMap() {
        return table;
    } 
   
}
