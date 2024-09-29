package part14_trie;

import org.junit.Test;

import java.util.*;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-15  11:01
 * @Version: 1.0
 * @Description: TODO
 */

public class FindWords {
    @Test
    public void test01() {
        char[][] arr = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath", "pea", "eat", "rain"};


        findWords(arr, words);
        System.out.println(Arrays.toString(results.toArray()));
    }

    @Test
    public void test02() {
        char[][] arr = {{'o', 'a', 'a', 'n'}, {'e', 't', 'a', 'e'}, {'i', 'h', 'k', 'r'}, {'i', 'f', 'l', 'v'}};
        String[] words = {"oath","pea","eat","rain","oathi","oathk","oathf","oate","oathii","oathfi","oathfii"};

        findWords(arr, words);
        System.out.println(Arrays.toString(results.toArray()));
    }


    List<String> results = new ArrayList<String>();
    Set<String> set = new HashSet<String>();

    public List<String> findWords(char[][] board, String[] words) {
        Node node = new Node();
        for (String s : words) {
            node.add(s);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                boolean[][] visited = new boolean[board.length][board[0].length];
                find(board, node, i, j, "", visited);
                // find(board, node, i, j, String.valueOf(board[i][j]), visited);
            }
        }
        return results;
    }

    public void find(char[][] board, Node node, int i, int j, String str, boolean[][] visited) {

        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || visited[i][j]) {
            return;
        }
        str = str + board[i][j];
        if (!node.startsWith(str)) {
            return;
        }
        if (node.search(str) && set.add(str)) {
            results.add(str);
        }
        visited[i][j] = true;
        find(board, node, i, j + 1, str, visited);
        find(board, node, i + 1, j, str, visited);
        find(board, node, i, j - 1, str, visited);
        find(board, node, i - 1, j, str, visited);
        visited[i][j] = false;
    }
}

class Node {
    Node[] childrens;
    boolean isEnd;

    public Node() {
        childrens = new Node[26];
        isEnd = false;
    }

    public void add(String words) {
        Node node = this;
        for (int i = 0; i < words.length(); i++) {
            int index = words.charAt(i) - 'a';
            if (null == node.childrens[index]) {
                node.childrens[index] = new Node();
            }
            node = node.childrens[index];
        }
        node.isEnd = true;
    }

    public boolean search(String words) {
        Node node = searchPrefix(words);
        return null != node && node.isEnd;

    }

    public boolean startsWith(String prefix) {
        return null != searchPrefix(prefix);
    }

    public Node searchPrefix(String prefix) {
        Node node = this;
        for (int i = 0; i < prefix.length(); i++) {
            int index = prefix.charAt(i) - 'a';
            if (null == node.childrens[index]) {
                return null;
            }
            node = node.childrens[index];
        }
        return node;
    }
}
