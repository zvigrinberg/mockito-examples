package com.company;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PalindromesAnagramsTest {

    private PalindromesAnagrams palindromesAnagrams;
    @Before
    public void setupTests()
    {
        palindromesAnagrams = new PalindromesAnagrams();
    }
    @Test
    public void isPalindromeAnagram() {
        assertTrue(palindromesAnagrams.isPalindromeAnagram("aabb"));
        assertFalse(palindromesAnagrams.isPalindromeAnagram("aabbb"));

    }
}