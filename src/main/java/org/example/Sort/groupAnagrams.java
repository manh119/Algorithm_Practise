package org.example.Sort;

import java.util.*;

public class groupAnagrams {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();

        for (String s : strs) {
            char[] sortedS = s.toCharArray();
            Arrays.sort(sortedS);
            map.putIfAbsent(String.copyValueOf(sortedS), new ArrayList<>());
            map.get(String.copyValueOf(sortedS)).add(s);
        }

        return new ArrayList<>(map.values());
    }


    public static void main(String[] args) {
        groupAnagrams anagrams = new groupAnagrams();
        System.out.println(anagrams.groupAnagrams(new String[] {"eat","tea","tan","ate","nat","bat"}));
    }
}
