package com.company;

import java.util.HashMap;
import java.util.Map;

public class PalindromesAnagrams {

    public static void main(String[] args) {
        PalindromesAnagrams palindromesAnagrams = new PalindromesAnagrams();
        palindromesAnagrams.isPalindromeAnagram("aabb");
    }

    boolean isPalindromeAnagram(String str) {
        char[] chars = str.toCharArray();
        Map <Character,Integer> occurrences = new HashMap();
        Integer temp;
        for (int i = 0; i < chars.length; i++) {
            Integer charOccurences = (Integer)occurrences.get(chars[i]);
            if(charOccurences == null)
            {
                occurrences.put(chars[i],0);
            }

            else
            {
                charOccurences = charOccurences + 1;
                occurrences.replace(chars[i],charOccurences) ;
            }

        }
        Integer[] numOfOccurences = (Integer[])(occurrences.values().toArray());
        Integer numOfSingles=0;
        boolean result=true;
        for (Integer number : numOfOccurences) {
            if (number == 1 )
            {
                numOfSingles++;
            }
            else
            {
                if(number % 2 !=0)
                {
                    result = false;
                }
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
