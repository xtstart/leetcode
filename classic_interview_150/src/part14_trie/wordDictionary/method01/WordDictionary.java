package part14_trie.wordDictionary.method01;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * @Author: xiongtian
 * @CreateTime: 2024-07-12  09:47
 * @Version: 1.0
 * @Description: TODO
 */

public class WordDictionary {
    public static void main(String[] args) {
        WordDictionary wordDictionary = new WordDictionary();

        wordDictionary.addWord("bad");
        wordDictionary.addWord("dad");
        wordDictionary.addWord("mad");
        wordDictionary.search("pad");
        wordDictionary.search("bad");
        wordDictionary.search(".ad");
        wordDictionary.search("b..");
    }


    WordDictionary[] childrens;
    List<WordDictionary> childrenNodes;

    boolean isEnd;

    public WordDictionary() {
        childrens = new WordDictionary[26];
        childrenNodes = new ArrayList<WordDictionary>();
        isEnd =false;
    }

    public void addWord(String word) {
        WordDictionary node = this;
        for (int i = 0; i < word.length(); i++) {
            int index = word.charAt(i) - 'a';

            if (null == node.childrens[index]) {
                node.childrens[index] = new WordDictionary();
                node.childrenNodes.add(node.childrens[index]);
            }
            node = node.childrens[index];
        }
        node.isEnd = true;

    }

    public boolean search(String word) {

        Queue<WordDictionary> queue = new ArrayDeque<>();
        queue.offer(this);
        for (int i = 0; i < word.length(); i++) {
            int c = word.charAt(i);
            int index = c - 'a';

            int size = queue.size();
            for (int j = 0; j < size; j++) {
                WordDictionary cur = queue.poll();

                if (c == '.') {
                    for (int k = 0; k < cur.childrenNodes.size(); k++) {
                        queue.offer(cur.childrenNodes.get(k));
                    }
                } else if (null != cur.childrens[index]) {
                    queue.offer(cur.childrens[index]);
                }
            }
            if (queue.isEmpty()) {
                return false;
            }
        }
        for (WordDictionary wordDictionary : queue) {
            if (wordDictionary.isEnd){
                return true;
            }
        }
        return false;
    }
}
