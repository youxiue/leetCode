package com.youxiue.array;

import org.junit.Test;

import java.util.*;

/**
 * 给你一个长度为 n 的字符串数组 names 。你将会在文件系统中创建 n 个文件夹：在第 i 分钟，新建名为 names[i] 的文件夹。
 * <p>
 * 由于两个文件 不能 共享相同的文件名，因此如果新建文件夹使用的文件名已经被占用，系统会以 (k) 的形式为新文件夹的文件名添加后缀，其中 k 是能保证文件名唯一的 最小正整数 。
 * <p>
 * 返回长度为 n 的字符串数组，其中 ans[i] 是创建第 i 个文件夹时系统分配给该文件夹的实际名称。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：names = ["pes","fifa","gta","pes(2019)"]
 * 输出：["pes","fifa","gta","pes(2019)"]
 * 解释：文件系统将会这样创建文件名：
 * "pes" --> 之前未分配，仍为 "pes"
 * "fifa" --> 之前未分配，仍为 "fifa"
 * "gta" --> 之前未分配，仍为 "gta"
 * "pes(2019)" --> 之前未分配，仍为 "pes(2019)"
 * <p>
 * 示例 2：
 * <p>
 * 输入：names = ["gta","gta(1)","gta","avalon"]
 * 输出：["gta","gta(1)","gta(2)","avalon"]
 * 解释：文件系统将会这样创建文件名：
 * "gta" --> 之前未分配，仍为 "gta"
 * "gta(1)" --> 之前未分配，仍为 "gta(1)"
 * "gta" --> 文件名被占用，系统为该名称添加后缀 (k)，由于 "gta(1)" 也被占用，所以 k = 2 。实际创建的文件名为 "gta(2)" 。
 * "avalon" --> 之前未分配，仍为 "avalon"
 * <p>
 * 示例 3：
 * <p>
 * 输入：names = ["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece"]
 * 输出：["onepiece","onepiece(1)","onepiece(2)","onepiece(3)","onepiece(4)"]
 * 解释：当创建最后一个文件夹时，最小的正有效 k 为 4 ，文件名变为 "onepiece(4)"。
 * <p>
 * 示例 4：
 * <p>
 * 输入：names = ["wano","wano","wano","wano"]
 * 输出：["wano","wano(1)","wano(2)","wano(3)"]
 * 解释：每次创建文件夹 "wano" 时，只需增加后缀中 k 的值即可。
 * <p>
 * 示例 5：
 * <p>
 * 输入：names = ["kaido","kaido(1)","kaido","kaido(1)"]
 * 输出：["kaido","kaido(1)","kaido(2)","kaido(1)(1)"]
 * 解释：注意，如果含后缀文件名被占用，那么系统也会按规则在名称后添加新的后缀 (k) 。
 * <p>
 * 提示：
 * <p>
 * 1 <= names.length <= 5 * 10^4
 * 1 <= names[i].length <= 20
 * names[i] 由小写英文字母、数字和/或圆括号组成。
 *
 * @author youxiue
 * @since 2023/3/3 14:15
 */
public class UniqueFileName1487 {

    @Test
    public void test() {
        System.out.println(Arrays.toString(getFolderNamesMy(new String[]{"pes", "fifa", "gta", "pes(2019)"})));
        System.out.println(Arrays.toString(getFolderNamesMy(new String[]{"gta", "gta(1)", "gta", "avalon"})));
        System.out.println(Arrays.toString(getFolderNamesMy(new String[]{"onepiece", "onepiece(1)", "onepiece(2)", "onepiece(3)", "onepiece"})));
        System.out.println(Arrays.toString(getFolderNamesMy(new String[]{"wano", "wano", "wano", "wano"})));
        System.out.println(Arrays.toString(getFolderNamesMy(new String[]{"kaido", "kaido(1)", "kaido", "kaido(1)"})));
    }

    /**
     * 暴力算法，每次都从零开始
     * 超出时间限制 ！！！！ shit！！！！
     * @param names
     * @return
     */
    public String[] my1(String[] names) {
        Set<String> set = new LinkedHashSet<>();
        for (String name : names) {
            if (!set.contains(name)) {
                set.add(name);
            } else {
                int i = 1;
                String left = name + "(";
                String right = ")";
                String newName = "";
                while (set.contains(newName = left + i + right)) {
                    i++;
                }
                set.add(newName);
            }
        }
        return set.toArray(new String[]{});
    }

    /**
     * 添加了map计数器，不再每次从0开始
     * 执行用时：51 ms, 在所有 Java 提交中击败了61.21%的用户
     * 内存消耗：58.1 MB, 在所有 Java 提交中击败了17.24%的用户
     * @param names
     * @return
     */
    public String[] my2(String[] names) {
        Set<String> set = new LinkedHashSet<>();
        Map<String, Integer> map = new HashMap<>();
        for (String name : names) {
            int i = map.getOrDefault(name, 0);
            String left = name + "(";
            String right = ")";
            String newName = name;
            while (set.contains(newName)) {
                i++;
                newName = left + i + right;
            }
            set.add(newName);
            map.put(name, i);
        }
        return set.toArray(new String[]{});
    }


    /**
     * 官方解法
     * 执行用时：59 ms, 在所有 Java 提交中击败了42.24%的用户
     * 内存消耗：53.5 MB, 在所有 Java 提交中击败了82.76%的用户
     * @param names
     * @return
     */
    public String[] getFolderNames(String[] names) {
        Map<String, Integer> map = new HashMap<String, Integer>();
        int n = names.length;
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            String name = names[i];
            if (!map.containsKey(name)) {
                res[i] = name;
                map.put(name, 1);
            } else {
                int k = map.get(name);
                while (map.containsKey(addSuffix(name, k))) {
                    k++;
                }
                res[i] = addSuffix(name, k);
                map.put(name, k + 1);
                map.put(addSuffix(name, k), 1);
            }
        }
        return res;
    }

    public String addSuffix(String name, int k) {
        return name + "(" + k + ")";
    }

    /**
     * 结合官方和自己的再优化
     * 执行用时：40 ms, 在所有 Java 提交中击败了93.97%的用户
     * 内存消耗：55.5 MB, 在所有 Java 提交中击败了59.48%的用户
     * @param names
     * @return
     */
    public String[] getFolderNamesMy(String[] names) {
        Map<String, Integer> map = new HashMap<>();
        int n = names.length;
        String[] res = new String[n];
        for (int i = 0; i < n; i++) {
            String name = names[i];
            int k = map.getOrDefault(name, 0);
            String newName = name;
            while (map.containsKey(newName)) {
                k++;
                newName = name + "(" + k + ")";
            }
            res[i] = newName;
            map.put(name, k);
            map.put(newName, 0);
        }
        return res;
    }
}
