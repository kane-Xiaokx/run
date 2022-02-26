package algorithm.link;

/**
 * 反转链表
 */
public class ReverseLink {

    /**
     * 常规反转链表
     * @param head
     * @return
     */
    public static Node reverseLink(Node head){
        Node pre = null;
        Node next = null;
        while (head != null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }
        return pre;
    }

    /**
     * 递归方式反转链表
     * @param node
     * @return
     */
    public static Node reverseLink2(Node node){

        if(node == null || node.next == null){
            return node;
        }
        Node next = node.next;
        Node reverse = reverseLink2(next);
        next.next = node;
        node.next = null;
        return reverse;
    }




    public static void main(String[] args) {
        Node node1 = Node.generatorNode(1);
        Node head = node1;
        Node.print(head);

        System.out.println("--------------->");
        head = reverseLink2(node1);
        Node.print(head);

    }




}
