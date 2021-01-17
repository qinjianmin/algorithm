# Algorithm
项目中主要包含了常用数据结构和算法的代码实现，本文则是对其特点性能的总结分析

# 数组
数组是一种线性表存储结构，它用一组连续的内存空间来存储一组类型相同的数据。
数组最大的特点就是支持以 O(1) 的时间复杂度进行随机访问，但插入、删除操作也因此变得比较低效，平均情况时间复杂度为 O(n)

# 链表
链表是一种线性存储结构，它通过“指针”将一组零散的内存块串联起来使用。链表通常可以分为：单链表、双向链表和循环链表
链表随机访问性能差，时间复杂度为 O(n)，但链表删除和插入操作操作只需要考虑相邻结点的指针变化，时间复杂度为O(1)

## 链表和数组的性能比较
* 插入、删除、随机访问等操作的时间复杂度不同
* 对缓存的友好程度
    * 数组使用的是连续的内存空间，可以借助 CPU 的缓存机制，预读数组中的数据，访问效率更高
    * 链表在内存中并不是连续存储，所以对 CPU 缓存不友好，没办法有效预读
* 对内存的要求
    * 数组占用连续的内存空间，可能因为没有足够连续的内存空间导致 OOM（out of Memory，内存不足）”；数组动态扩容可能很费时
    * 链表本身没有大小限制，天然地支持动态扩容；链表中的每个结点都需要消耗额外的存储空间去存储一份指向下一个结点的指针，所以内存消耗更大；对链表进行频繁的插入、删除操作，还会导致频繁的内存申请和释放，容易造成内存碎片，如果是 Java 语言，就有可能会导致频繁的 GC（Garbage Collection，垃圾回收）
