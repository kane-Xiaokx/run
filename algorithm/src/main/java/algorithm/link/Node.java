package algorithm.link;

public class Node {

    Node next;
    int val;

    public Node() {
    }

    public Node(Node next, int val) {
        this.next = next;
        this.val = val;
    }

    public Node getNext() {
        return next;
    }

    public void setNext(Node next) {
        this.next = next;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }

    /**
     * 按照顺序生产一个单向链表
     * 比如maxValue = 4;
     * 那么会生成一个
     * 1 -> 2 -> 3 -> 4
     * 的链表
     * @param maxValue
     * @return
     */
    public static Node generatorNode(int maxValue){
        if(maxValue <= 0){
            return null;
        }
        Node last = new Node();
        last.setVal(maxValue);
        if(maxValue == 1){
            return last;
        }
        return generatorNode(last, --maxValue);
    }

    private static Node generatorNode(Node next,int value){
        if(value == 1){
            return new Node(next,value);
        }
        return generatorNode(new Node(next,value),--value);
    }

    public static void print(Node node){
        while (node != null){
            System.out.println(node.val);
            node = node.next;
        }

    }
}
