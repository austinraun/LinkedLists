import java.util.LinkedList;

/**
 * Created by Administrator on 2015/11/3.
 */
public class CircularLinkedList<E>  {
    private static class Node<E> {
        private E value;

        private Node<E> next;

        Node(E item) {
            this.value = item;
            this.next = null;
        }

         Node(E item, Node<E> next) {
            this.value = item;
             this.next = next;
        }

        public E getValue() {
            return value;
        }
        public Node<E> getNext() {
            return next;
        }
        public void setNext (Node<E> node) {
            next = node;
        }
        public boolean setValue(E item) {
            value = item;
            return true;
        }
    }
    Node<E> head;
    Node<E> tail;
    int size;

    public CircularLinkedList() {
       head = null;
        size = 0;
    }
    public boolean add(E item) {
        if (tail  == null) {
            head = new Node<E>(item,head);
            tail = head;
            size++;
        }
        else {
            tail.next = new Node<E>(item,tail.next);
            tail = tail.next;
            size++;
        }
        return true;
    }
    public boolean remove(Node e) {

        if (e == head) {
            if (head != null) {
                Node next = head.next;
                head.value = null;
                head.next = null;
                head = next;
                size--;
                return true;
            }
            else return false;
        }
        else {
            Node<E> n = head;
            int i = 0;
            while (n != e) {
                if (i == size) {
                    return false;
                }
                i ++;
            }
            Node next = n.next;
            n.value = null;
            n.next = null;
            n = next;
            return true;
        }
    }
    public boolean remove(int index) {
        if (index == 0) {
            if (head != null) {
                Node next = head.next;
                head.value = null;
                head.next = null;
                head = next;
                size--;
                return true;
            }
            else return false;
        }
        else if (index < size){
            Node curr = getNode(index);
            Node next = curr.next;
            curr.value = null;
            curr.next = null;
            curr = next;
            return true;
        }
        else  {
            return false;
        }
    }
    public boolean setCircuit(int index) {
        if (index >= size-1) {
            return false;
        }
        else {
            tail.next = getNode(index);
            return true;
        }
    }
    public Node<E> getNode(int index) {
        Node<E> node = new Node<E>(null);
        for (int i = 0; i <= index; i++) {
            if (i == 0) {

            node = this.head;
            }
            else {
                node = node.getNext();
            }
        }
        return node;
    }
    public boolean checkCircuit() {
        Node slowNode = head;
        Node fastNode1 = head;
        while (slowNode.next != null && fastNode1.next != null) {
            slowNode = slowNode.next;
            if (fastNode1 != null) {
                fastNode1 = fastNode1.next.next;
            }
            else {
                return false;
            }
            if (fastNode1 == null || slowNode == null) {
                return false;
            }
            if (fastNode1 == slowNode) {
                System.out.println(slowNode.getValue());
                return true;
            }
        }
    return false;
    }
  /*  public String toString() {
            return head.value + " " + toString(head.next);
        }*/

    /*public String toString(Node<E> head) {
        if (head == null) return "";
        else {
            return head.value + " " + toString(head.next);
        }
    }*/
}