package org.example.Sort;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Locale;
import java.util.PriorityQueue;

public class arrangeWords {
    //Input: text = "Keep calm and code on"
    //Output: "On and keep calm code"
    public String arrangeWords(String text) {
        String[] strings = text.split(" ");
        strings[0] = strings[0].toLowerCase();

        Arrays.sort(strings, Comparator.comparingInt(String::length));

        char[] word0 = strings[0].toCharArray();
        word0[0] = String.valueOf(word0[0]).toUpperCase().toCharArray()[0];

        StringBuilder result = new StringBuilder(String.valueOf(word0));
        for (int i = 1; i < strings.length; i++) {
            result.append(" ").append(strings[i]);
        }

        return result.toString();
    }

    public static void main(String[] args) {
        arrangeWords words = new arrangeWords();
        System.out.println(words.arrangeWords("To be or not to be"));
    }

}
