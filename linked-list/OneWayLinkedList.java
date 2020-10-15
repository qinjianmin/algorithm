/**
 * 1. 实现单向链表的插入、删除、查找等操作
 * 2. 数据类型为 int
 */
public class OneWayLinkedList {
    private Node head;

    public Node findByValue(int value) {
        Node p = head;
        while (p != null && p.data != value) {
            p = p.next;
        }
        return p;
    }

    public Node findByIndex(int index) {
        if (index < 0) {
            throw new IllegalArgumentException("索引不能小于0");
        }
        Node p = head;
        int pos = 0;
        while (p != null && pos != index) {
            p = p.next;
            ++pos;
        }
        return p;
    }

    public void insertToHead(int value) {
        Node newNode = new Node(value, null);
        insertToHead(newNode);
    }

    public void insertToHead(Node newNode) {
        if (head != null) {
            newNode.next = head;
        }
        head = newNode;
    }

    public void insertToTail(int value) {
        Node newNode = new Node(value, null);
        insertToTail(newNode);
    }

    public void insertToTail(Node newNode) {
        if (head == null) {
            head = newNode;
        } else {
            Node p = head;
            while (p.next != null) {
                p = p.next;
            }
            p.next = newNode;
        }
    }

    public void insertAfter(Node p, int value) {
        Node newNode = new Node(value, null);
        insertAfter(p, newNode);
    }

    public void insertAfter(Node p, Node newNode) {
        newNode.next = p.next;
        p.next = newNode;
    }

    public void insertBefore(Node p, int value) {
        Node newNode = new Node(value, null);
        insertBefore(p, newNode);
    }

    public void insertBefore(Node p, Node newNode) {
        if (head == p) {
            insertToHead(newNode);
            return;
        }
        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }
        if (q == null) {
            return;
        }
        newNode.next = p;
        q.next = newNode;
    }

    public void deleteByNode(Node p) {
        if (head == null) {
            return;
        }
        if (p == head) {
            head = head.next;
            return;
        }
        Node q = head;
        while (q != null && q.next != p) {
            q = q.next;
        }
        if (q == null) {
            return;
        }
        q.next = q.next.next;
    }

    public void deleteByValue(int value) {
        if (head == null) {
            return;
        }
        if (value == head.data) {
            head = head.next;
            return;
        }
        Node p = head;
        while (p.next != null) {
            if (p.next.data == value) {
                p.next = p.next.next;
                return;
            }
            p = p.next;
        }
    }

    public void printAll() {
        Node p = head;
        while (p != null) {
            System.out.print(p.data + " ");
            p = p.next;
        }
    }

    public static class Node {
        private int data;
        private Node next;

        public Node(int data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
