/**
 * 1. 实现数组的插入、删除和按照下标随机访问等操作
 * 2. 数据类型为 int
 */
public class Array {
    // 内部保存数据的整形数组
    private final int[] data;

    // 定义数组容量
    private final int n;

    // 数组中数据个数
    private int count;

    // 无参构造器
    public Array() {
        this(10);
    }

    // 指定初始容量的构造器
    public Array(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException("数组容量不能小于0");
        }
        this.data = new int[capacity];
        this.n = capacity;
        this.count = 0;
    }

    // 判断数组是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 获取数组元素个数
    public int size() {
        return count;
    }

    // 判断数组是否包含元素：平均时间复杂度 O(n)
    public boolean contains(int value) {
        for (int i = 0; i < count; i++) {
            if (data[i] == value) {
                return true;
            }
        }
        return false;
    }

    // 根据索引查找元素并返回：随机访问时间复杂度为 O(1)
    public int get(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("索引超出范围");
        }
        return data[index];
    }

    // 插入元素：平均时间复杂度 O(n)
    public void insert(int index, int value) {
        if (index < 0 || index > count) {
            throw new IllegalArgumentException("索引超出范围");
        }
        if (count == n) {
            throw new IllegalArgumentException("数组已满");
        }

        for (int i = count; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = value;
        count++;
    }

    // 删除指定位置的元素并返回：平均时间复杂度 O(n)
    public int removeAt(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException("索引超出范围");
        }

        int value = data[index];
        for (int i = index; i < count - 1; i++) {
            data[i] = data[i + 1];
        }
        count--;
        return value;
    }

    // 打印数组
    public void printAll() {
        for (int i = 0; i < count; i++) {
            System.out.print(data[i] + " ");
        }
        System.out.println();
    }

    // 测试
    public static void main(String[] args) {
        Array a = new Array();
        System.out.println(a.isEmpty());

        for (int i = 0; i < 10; i++) {
            a.insert(0, i);
        }
        System.out.println(a.contains(0));
        System.out.println(a.get(5));
        a.printAll();

        System.out.println(a.isEmpty());
        for (int i = 0; i < 10; i++) {
            System.out.println(a.removeAt(0));
        }
        System.out.println(a.isEmpty());
    }
}