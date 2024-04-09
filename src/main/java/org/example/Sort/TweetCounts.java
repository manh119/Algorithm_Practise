package org.example.Sort;

import java.util.*;


class TweetCounts {

    private Map<String, TreeMap<Integer, Integer>> map;

    public TweetCounts() {
        map = new HashMap<>();
    }

    public void recordTweet(String tweetName, int time) {

        if (!map.containsKey(tweetName))
            map.put(tweetName, new TreeMap<>());
        TreeMap<Integer, Integer> tweetMap = map.get(tweetName);
        tweetMap.put(time, tweetMap.getOrDefault(time, 0) + 1);
    }

    public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
        if (!map.containsKey(tweetName))
            return null;

        int interval = 0;
        if (freq.equals("minute"))
            interval = 60;
        else if (freq.equals("hour"))
            interval = 3600;
        else if (freq.equals("day"))
            interval = 3600 * 60;

        int size = (endTime - startTime) / interval + 1;

        int[] buckets = new int[size];

        for (Map.Entry<Integer, Integer> entry : map.get(tweetName).subMap(startTime, endTime + 1).entrySet()) {
            int index = (entry.getKey() - startTime) / interval;
            buckets[index] += entry.getValue();
        }

        List<Integer> ans = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            ans.add(buckets[i]);
        }

        return ans;
    }


    public static void main(String[] args) {
        TweetCounts tweetCounts = new TweetCounts();
        tweetCounts.recordTweet("tweet3", 0);
        tweetCounts.recordTweet("tweet3", 60);
        tweetCounts.recordTweet("tweet3", 120);
        // 1 1 1
        System.out.println(tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 120));
        // 3
        System.out.println(tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 124));

    }
}


