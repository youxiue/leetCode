package com.youxiue.string;

import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * @author: xfb
 * @projectName: leetcode->IsValid20
 * @description: 20. 有效的括号
 * @date: 2020/08/14 15:45
 */
public class IsValid20 {
    /**
     * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
     * <p>
     * 有效字符串需满足：
     * <p>
     * 左括号必须用相同类型的右括号闭合。
     * 左括号必须以正确的顺序闭合。
     * 注意空字符串可被认为是有效字符串。
     * <p>
     * 示例 1:
     * <p>
     * 输入: "()"
     * 输出: true
     * 示例 2:
     * <p>
     * 输入: "()[]{}"
     * 输出: true
     * 示例 3:
     * <p>
     * 输入: "(]"
     * 输出: false
     * 示例 4:
     * <p>
     * 输入: "([)]"
     * 输出: false
     * 示例 5:
     * <p>
     * 输入: "{[]}"
     * 输出: true
     */

    @Test
    public void test() {
        String s = "()[]{}";
        boolean valid = isValid(s);
        System.out.println(valid);
    }

    public boolean isValid(String s) {
        if (s.length() > 0 && !(s.charAt(0) == '(' || s.charAt(0) == '[' || s.charAt(0) == '{')) return false;
        if (s.length() % 2 == 1) return false;
        Stack<Character> stack = new Stack<Character>() {{
            push('?');
        }};

        for (char c : s.toCharArray()) {
            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
            } else if ((c == ')' && (!stack.pop().equals('('))) || (c == ']' && (!stack.pop().equals('['))) || (c == '}' && (!stack.pop().equals('{')))) {
                return false;
            }
        }
        return stack.size() == 1;
    }


    private static final Map<Character, Character> map = new HashMap<Character, Character>() {{
        put('{', '}');
        put('[', ']');
        put('(', ')');
        put('?', '?');
    }};


    public boolean isValid1(String s) {
        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>() {{
            add('?');
        }};
        for (Character c : s.toCharArray()) {
            if (map.containsKey(c)) stack.addLast(c);
            else if (map.get(stack.removeLast()) != c) return false;
        }
        return stack.size() == 1;
    }

    public boolean isValid2(String s) {
        // 将左边符号作为键  ,  右边符号作为值
        HashMap<Character, Character> map = new HashMap<Character, Character>() {{
            put('(', ')');
            put('[', ']');
            put('{', '}');
            put('?', '?');
        }};
        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false;

        Stack<Character> stack = new Stack<Character>() {{
            push('?');
        }};

        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) stack.push(c); // 如果是左边符号, 则放入到stack 中
            else if (!map.get(stack.pop()).equals(c)) return false; // 如果不是左边符号, 则判断是否栈顶对应的右符号
        }

        return stack.size() == 1;
    }
}