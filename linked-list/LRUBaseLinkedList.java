import java.util.Scanner;

/**
 * 基于链表的 LRU 算法
 */
public class LRUBaseLinkedList<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private Node<T> headNode;

    // 链表容量
    private int capacity;

    // 链表长度
    private int length;

    public LRUBaseLinkedList() {
        headNode = new Node<>();
        this.capacity = DEFAULT_CAPACITY;
        this.length = 0;
    }

    public LRUBaseLinkedList(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity cannot <= 0");
        }
        headNode = new Node<>();
        this.capacity = capacity;
        this.length = 0;
    }

    public void add(T element) {
        Node<T> preNode = findPreNode(element);
        if (preNode == null) {
            if (length >= capacity) {
                deleteAtTail();
            }
        } else {
            deleteNext(preNode);
        }
        insertToHead(element);
    }

    private void printAll() {
        Node<T> node = headNode.getNext();
        while (node != null) {
            System.out.print(node.getElement() + " ");
            node = node.getNext();
        }
        System.out.println();
    }

    private Node<T> findPreNode(T element) {
        Node<T> node = headNode;
        while (node.getNext() != null) {
            if (node.getNext().getElement().equals(element)) {
                return node;
            }
            node = node.getNext();
        }
        return null;
    }

    private void deleteAtTail() {
        Node<T> node = headNode;
        while (node.getNext() != null) {
            if (node.getNext().getNext() == null) {
                node.setNext(null);
                length--;
                return;
            }
            node = node.getNext();
        }
    }

    private void deleteNext(Node<T> preNode) {
        preNode.setNext(preNode.getNext().getNext());
        length--;
    }

    private void insertToHead(T element) {
        Node<T> node = new Node<>(element);
        node.setNext(headNode.getNext());
        headNode.setNext(node);
        length++;
    }


    private static class Node<T> {
        private T element;
        private Node<T> next;

        public Node() {
        }

        public Node(T element) {
            this.element = element;
        }

        public Node(T element, Node<T> next) {
            this.element = element;
            this.next = next;
        }

        public T getElement() {
            return element;
        }

        public void setElement(T element) {
            this.element = element;
        }

        public Node<T> getNext() {
            return next;
        }

        public void setNext(Node<T> next) {
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LRUBaseLinkedList<String> list = new LRUBaseLinkedList<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.next();
            if ("over".equals(str)) {
                break;
            }
            list.add(str);
            list.printAll();
        }
    }
}
