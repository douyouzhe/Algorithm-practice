
/*
Given two words (beginWord and endWord), and a dictionary's word list,
find the length of shortest transformation sequence from beginWord to endWord, such that:

Only one letter can be changed at a time.
Each transformed word must exist in the word list. Note that beginWord is not a transformed word.
For example,

Given:
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
return its length 5.

Note:
Return 0 if there is no such transformation sequence.
All words have the same length.
All words contain only lowercase alphabetic characters.
You may assume no duplicates in the word list.
You may assume beginWord and endWord are non-empty and are not the same.
*/

import java.util.*;

public class Solution1 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        //cc
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);
        int minLen = 0;

        while(!q.isEmpty()){
            int size = q.size();
            while(size-->0){
                String s = q.poll();
                if(s.equals(endWord)) return minLen+1;
                for (int i =0; i<wordList.size(); i++){
                    if(getDiff(s,wordList.get(i))==1){
                        q.offer(wordList.get(i));
                        wordList.remove(i);
                        i--; // important, when removed one, size changes
                    }
                }
            }
            minLen++;
        }
        return 0;
    }

    private int getDiff(String s1, String s2){
        int ans = 0;
        for(int i = 0; i<s1.length(); i++){
            if(s1.charAt(i)!=s2.charAt(i)) ans++;
        }
        return ans;
    }
}

//优化  two end search
class Solution2 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> beginSet = new HashSet<String>();
        Set<String> endSet = new HashSet<String>();
        List<String> words = new ArrayList<String>();

        for(String s: wordList){
            words.add(s);
        }


        if(!words.contains(endWord)) return 0;
        beginSet.add(beginWord);
        endSet.add(endWord);

        int len = 1;
        int strLen = beginWord.length();

        while (!beginSet.isEmpty() && !endSet.isEmpty()) {
            if (beginSet.size() > endSet.size()) {
                Set<String> set = beginSet;
                beginSet = endSet;
                endSet = set;
            }

            Set<String> nextLevel = new HashSet<String>();
            for (String curBegin : beginSet) {
                for(String curEnd: endSet){
                    if(getDiff(curBegin,curEnd)==1) return len+1;
                }

                for (int i =0; i<words.size(); i++){
                    String s = words.get(i);
                    if(getDiff(s,curBegin)==1){
                        nextLevel.add(s);
                        words.remove(i);
                        i--; // important, when removed one, size changes
                    }
                }
            }
            beginSet = nextLevel;
            len++;
        }
        return 0;
    }

    private int getDiff(String s1, String s2){
        int ans = 0;
        for(int i = 0; i<s1.length(); i++){
            if(s1.charAt(i)!=s2.charAt(i)) ans++;
        }
        return ans;
    }
}
