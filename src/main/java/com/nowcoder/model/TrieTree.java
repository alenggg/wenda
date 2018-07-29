package com.nowcoder.model;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Created with IntelliJ IDEA
 * Author: aleng
 * Date  : 2018/7/8
 * Time  : 13:02
 * Description: TrieTree,前缀树，字典树
 **/
@Component
public class TrieTree {
    public class TrieNode {
        public int path; //当前节点的个数

        public int end; //以当前节点结尾的个数

        public Map<Character, TrieNode> subNodes = new HashMap<>(); //key代表此节点的值，value是次节点

        public void setSubNode(Character key, TrieNode node) {
            subNodes.put(key, node);
        }

        public TrieNode getSubNode(Character key) {
            return subNodes.get(key);
        }
        //该节点有多少个子节点
        public int getSubNodeCount() {
            return subNodes.size();
        }
    }

    public TrieNode root;

    public TrieTree() {
        root = new TrieNode();
    }

    public void insert(String word) {
        if (word == null) {
            return;
        }
        char[] chars = word.toCharArray();
        TrieNode cur = root;
        for (int i = 0; i < chars.length; i++) {
            if (cur.getSubNode(chars[i]) == null) { //当前节点的子节点没有该字符，则添加
                cur.setSubNode(chars[i], new TrieNode());
            }
            cur = cur.getSubNode(chars[i]);
            cur.path++;//注意先后顺序
        }
        cur.end++;
    }

    public boolean search(String word) {
        if (word == null) {
            return false;
        }
        char[] chars = word.toCharArray();
        TrieNode cur = root;
        for (int i = 0; i < chars.length; i++) {
            if (cur.getSubNode(chars[i]) == null) {
                return false;
            }
            cur = cur.getSubNode(chars[i]);
        }
        return cur.end > 0;
    }

    public int prefixNumber(String pre) {
        if (pre == null) {
            return 0;
        }
        char[] chars = pre.toCharArray();
        TrieNode cur = root;
        for (int i = 0; i < chars.length; i++) {
            if (cur.getSubNode(chars[i]) == null) {
                return 0;
            }
            cur = cur.getSubNode(chars[i]);
        }
        return cur.path;
    }

    public void delete(String word) {
        if (search(word)) {
            char[] chars = word.toCharArray();
            TrieNode cur = root;
            for (int i = 0; i < chars.length; i++) {
                if (cur.getSubNode(chars[i]).path-- == 1) {
                    cur.setSubNode(chars[i], null);
                    return;
                }
                cur = cur.getSubNode(chars[i]);
            }
            cur.end--;
        }
    }

//    public static void main(String[] args) {
//        TrieTree trie = new TrieTree();
//        System.out.println(trie.search("zuo")); //false
//        trie.insert("zuo");
//        System.out.println(trie.search("zuo")); //true
//        trie.delete("zuo");
//        System.out.println(trie.search("zuo")); //false
//        trie.insert("zuo");
//        trie.insert("zuo");
//        trie.delete("zuo");
//        System.out.println(trie.search("zuo")); //true
//        trie.delete("zuo");
//        System.out.println(trie.search("zuo")); //false
//        trie.insert("zuoa");
//        trie.insert("zuoac");
//        trie.insert("zuoab");
//        trie.insert("zuoad");
//        trie.delete("zuoa");
//        System.out.println(trie.search("zuoa")); //false
//        System.out.println(trie.prefixNumber("zuo")); //3
//    }

}
