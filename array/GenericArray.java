/**
 * 实现数组的插入、删除、按照下标随机访问和动态扩容等操作
 * 带泛型参数
 */
public class GenericArray<T> {
    // 内部保存数据的整形数组
    private T[] data;

    // 数组中数据个数
    private int count;

    // 无参构造器，默认容量为10
    public GenericArray() {
        this(10);
    }

    // 指定初始容量的构造器
    public GenericArray(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("数组容量不能小于等于0");
        }
        this.data = (T[]) new Object[capacity];
    }

    // 获取数组容量
    public int getCapacity() {
        return data.length;
    }

    // 获取数组元素个数
    public int size() {
        return count;
    }

    // 判断数组是否为空
    public boolean isEmpty() {
        return count == 0;
    }

    // 修改指定位置的元素
    public void set(int index, T e) {
        validateIndex(index);
        data[index] = e;
    }

    // 根据索引查找元素并返回
    public T get(int index) {
        validateIndex(index);
        return data[index];
    }

    // 查看数组是否包含某个元素
    public boolean contains(T e) {
        for (T t : data) {
            if (t.equals(e))
                return true;
        }
        return false;
    }

    // 获取对应元素首次出现的下标，不存在返回-1
    public int indexOf(T e) {
        for (int i = 0; i < count; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }

    // 在指定位置插入元素
    public void insert(int index, T e) {
        validateIndexForAdd(index);
        if (count == data.length) {
            resize(2 * data.length);
        }

        for (int i = count; i > index; i--) {
            data[i] = data[i - 1];
        }
        data[index] = e;
        count++;
    }

    // 在数组头部插入元素
    public void addFirst(T e) {
        insert(0, e);
    }

    // 在数组尾部插入元素
    public void addLast(T e) {
        insert(count, e);
    }

    // 删除指定位置的元素并返回T
    public T removeAt(int index) {
        validateIndex(index);
        T e = data[index];
        for (int i = index; i < count - 1; i++) {
            data[i] = data[i + 1];
        }
        data[--count] = null; // 防止对象游离
        if (count > 0 && count == data.length / 4)
            resize(data.length / 2);
        return e;
    }

    // 删除数组首个元素
    public T removeFirst() {
        return removeAt(0);
    }

    // 删除数组末尾元素
    public T removeLast() {
        return removeAt(count - 1);
    }

    // 删除指定元素
    public void removeElement(T e) {
        int index = indexOf(e);
        if (index != -1)
            removeAt(index);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Array size = %d, capacity = %d\n", count, data.length));
        sb.append("[");
        for (int i = 0; i < count; i++) {
            sb.append(data[i]);
            if (i != count - 1)
                sb.append(", ");
        }
        sb.append("]");
        return sb.toString();
    }

    // 改变数组容量：时间复杂度 O(n)
    private void resize(int capacity) {
        T[] copy = (T[]) new Object[capacity];
        for (int i = 0; i < count; i++) { // 注意循环次数为 count，而不是 capacity 或 data.length
            copy[i] = data[i];
        }
        data = copy;
    }

    private void validateIndex(int index) {
        if (index < 0 || index >= count)
            throw new IllegalArgumentException("索引超出范围");
    }

    private void validateIndexForAdd(int index) {
        if (index < 0 || index > count)
            throw new IllegalArgumentException("索引超出范围");
    }

    // 测试
    public static void main(String[] args) {
        GenericArray<Integer> a = new GenericArray<>();
        System.out.println(a);
        for (int i = 0; i < 10; i++) {
            a.addFirst(i);
            a.addLast(i);
        }
        System.out.println(a);
        System.out.println(a.removeLast());
        System.out.println(a.removeFirst());
        System.out.println(a.indexOf(5));
        System.out.println(a.contains(4));
        System.out.println(a.get(5));
        a.removeElement(2);
        System.out.println(a);
    }
}
