package org.example.Sort;

import java.util.*;

public class groupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<Integer, List<String>> map = new HashMap<>();

        for (String s : strs) {
            int uniqueInt = uniqueInt(s.toCharArray());
            map.putIfAbsent(uniqueInt, new ArrayList<>());
            map.get(uniqueInt).add(s);
        }

        return new ArrayList<>(map.values());
    }

    // gen unique integer by frequency in char
    private int uniqueInt(char[] str) {
        int[] freq = new int[26];
        for (int i = 0; i < str.length; i++) {
            freq[str[i] - 'a']++;
        }
        return Arrays.hashCode(freq);
    }


    public static void main(String[] args) {
        groupAnagrams anagrams = new groupAnagrams();
        System.out.println(anagrams.groupAnagrams(new String[] {"eat","tea","tan","ate","nat","bat"}));
    }
}
