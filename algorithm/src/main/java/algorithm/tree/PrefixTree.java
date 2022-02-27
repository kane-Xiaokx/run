package algorithm.tree;

/**
 * 普通的前缀树
 * 假设所有的数据都是小字母的字符串 "abc"
 */
public class PrefixTree {

    private Node root = new Node();

    //common
    private static class Node{
        int pass;
        int end;
        Node[] next = new Node[26];
    }
    //hash
    private static class HashNode{

    }

    public void createTree(String data){
        if(data == null){
            return;
        }
        char[] chars = data.toCharArray();
        Node node = root;
        node.pass++;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 'a';
            if(node.next[index] == null){
                node.next[index] = new Node();
            }
            Node next = node.next[index];
            next.pass++;
            node = next;
        }
        node.end ++;
    }

    public int search(String str){
        if(str == null){
            return 0;
        }
        char[] chars = str.toCharArray();
        Node node = root;
        for (int i = 0; i < chars.length; i++) {
            int index = chars[i] - 1;
            if(node.next[index] == null){
                return 0;
            }
            node = node.next[index];
        }
        return node.end;
    }

    public int startWith(String str){
        if(str == null || "".equals(str.trim())){
            return 0;
        }
        Node node = root;
        char[] chars = str.toCharArray();
        for (int i = 0; i < chars.length; i++) {
             int index = chars[i] - 'a';
             if(node.next[index] == null){
                 return 0;
             }
            node = node.next[index];
        }
        return node.pass;
    }
    public void del(String str){
        if(search(str) != 0){
            Node node = root;
            node.pass--;
            char[] chars = str.toCharArray();
            for (int c = 0; c < chars.length; c++) {
                int index = chars[c] - 'a';
                if(--node.next[index].pass == 0){
                    node.next[index] = null;
                    return;
                }
                node = node.next[index];
            }
            node.end--;
        }
    }



}
