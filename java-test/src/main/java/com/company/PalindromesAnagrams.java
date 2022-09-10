package com.company;

import java.util.HashMap;
import java.util.Map;

public class PalindromesAnagrams {

    public static void main(String[] args) {

    }

    boolean isPalindromeAnagram(String str) {
        char[] chars = str.toCharArray();
        Map <Character,Integer> occurrences = new HashMap();
        Integer temp;
        for (int i = 0; i < chars.length; i++) {
            Integer charOccurences = (Integer)occurrences.get(chars[i]);
            if(charOccurences == null)
            {
                occurrences.put(chars[i],1);
            }

            else
            {
                charOccurences = charOccurences + 1;
                occurrences.replace(chars[i],charOccurences) ;
            }

        }
        Integer[] numOfOccurrences = new Integer[str.length()];
        occurrences.values().toArray(numOfOccurrences);
        Integer numOfSingles=0;
        boolean result=true;
        for (Integer number : numOfOccurrences) {
            if (number!= null && number % 2 == 1 )
            {
                numOfSingles++;
            }
            else
            {

              if(number == null )
                   break;

            }
            if(numOfSingles > 1)
            {
                result = false;
                break;
            }

        }
        if(numOfSingles > 1)
        {
            result= false;
        }
        return result;
    }

    private boolean isPalindrome(String str)
    {
        char[] chars = str.toCharArray();
        for (int i = 0, j= chars.length - 1; i < chars.length && j >= 0 ; i++, j--) {
            if(chars[i] != chars[j])
                 return false;
        }
        return true;
    }
}
