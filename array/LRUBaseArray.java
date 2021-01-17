import java.util.Scanner;

public class LRUBaseArray<T> {
    private static final int DEFAULT_CAPACITY = 10;

    private T[] container;
    private int length;
    private int capacity;

    public LRUBaseArray() {
        this.length = 0;
        this.capacity = DEFAULT_CAPACITY;
        container = (T[]) new Object[capacity];
    }

    public LRUBaseArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("capacity cannot <= 0");
        }
        this.length = 0;
        this.capacity = capacity;
        container = (T[]) new Object[capacity];
    }

    private void add(T element) {
        int index = indexOf(element);
        if (index == -1) {
            if (length == capacity) {
                removeAtTail();
            }
        } else {
            removeAt(index);
        }
        insertToHead(element);
    }

    private void removeAt(int index) {
        for (int i = index; i < length - 1; i++) {
            container[i] = container[i + 1];
        }
        container[length - 1] = null;
        length--;
    }

    private int indexOf(T element) {
        for (int i = 0; i < length; i++) {
            if (container[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private void insertToHead(T element) {
        for (int i = length - 1; i >= 0; i--) {
            container[i + 1] = container[i];
        }
        container[0] = element;
        length++;
    }

    private void removeAtTail() {
        container[length -1] = null;
        length--;
    }

    private void printAll() {
        for (int i = 0; i < length; i++) {
            System.out.print(container[i] + " ");
        }

    }

    public static void main(String[] args) {
        LRUBaseArray<String> array = new LRUBaseArray<>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.next();
            if ("over".equals(str)) {
                break;
            }
            array.add(str);
            array.printAll();
        }
    }
}
