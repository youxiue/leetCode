package com.youxiue.string;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author: xfb
 * @projectName: leetcode->PalindromePairs
 * @description: TODO
 * @date: 2020/08/06 10:50
 */
public class PalindromePairs {

/*    给定一组唯一的单词， 找出所有不同 的索引对(i, j)，使得列表中的两个单词， words[i] + words[j] ，可拼接成回文串。

    示例 1:

    输入: ["abcd","dcba","lls","s","sssll"]
    输出: [[0,1],[1,0],[3,2],[2,4]]
    解释: 可拼接成的回文串为 ["dcbaabcd","abcddcba","slls","llssssll"]
    示例 2:

    输入: ["bat","tab","cat"]
    输出: [[0,1],[1,0]]
    解释: 可拼接成的回文串为 ["battab","tabbat"]*/

    @Test
    public void test(){
        String[] words = {"abcd","dcba","lls","s","sssll"};

        //String b = "abdced";
        //System.out.println(b.substring(0,1));
        //System.out.println(b.substring(b.length()-1));

        List<List<Integer>> list = palindromePairs(words);
        System.out.println(list.toString());
    }

    public List<List<Integer>> palindromePairs(String[] words) {
        List<List<Integer>> list = new ArrayList<List<Integer>>();
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words.length; j++) {
                String temp = words[i]+ words[j];
                if(i != j && temp.substring(0, 1).equals(temp.substring(temp.length()-1))){
                    String[] split = temp.split("");
                    List<String> tempList = Arrays.asList(split);
                    Collections.reverse(tempList);
                    StringBuffer sb = new StringBuffer();
                    for (int x = 0; x < tempList.size(); x++) {
                        sb.append(tempList.get(x));
                    }
                    if(temp.equals(sb.toString())){
                        ArrayList<Integer> arrayList = new ArrayList<>();
                        arrayList.add(i);
                        arrayList.add(j);
                        list.add(arrayList);
                    }

                }
            }
        }
        return list;
    }

}
