package org.example.Sort;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;

public class checkIfCanBreak {
    public boolean checkIfCanBreak(String s1, String s2) {
        return check(s1, s2) || check(s2,s1);
    }

    // check if s1 can break s2
    private boolean check(String s1, String s2) {
        char[] char1 = s1.toCharArray();
        char[] char2 = s2.toCharArray();

        char[] freq1 = new char[26];
        char[] freq2 = new char[26];

        for (int i = 0; i < s1.length(); i++) {
            freq1[char1[i] - 'a']++;
            freq2[char2[i] - 'a']++;
        }

        int count1 = 0, count2 = 0;
        for (int i = 0; i < freq2.length; i++) {
            count1 += freq1[i];
            count2 += freq2[i];
            if (count1 < count2)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        checkIfCanBreak ifCanBreak = new checkIfCanBreak();
        System.out.println(ifCanBreak.checkIfCanBreak("abc", "axy"));
    }


}
