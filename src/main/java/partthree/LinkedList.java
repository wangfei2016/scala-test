package partthree;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * LinkedList.
 *
 * @author wang_fei
 * @since 2022/5/27 8:53
 */
public class LinkedList<E> implements Iterable<E> {
    private static class Node<E> {
        private Node<E> prev;    //前一个结点
        private E item;  //结点元素
        private Node<E> next;    //后一个结点

        public Node(Node<E> prev, E item, Node<E> next) {
            this.prev = prev;
            this.item = item;
            this.next = next;
        }
    }

    private final Node<E> first;   //头结点
    private final Node<E> last;    //尾结点
    private int count;  //添加的结点个数

    public LinkedList() {
        first = new Node<>(null, null, null);
        last = new Node<>(first, null, null);
        first.next = last;
        count = 0;
    }

    /**
     *
     * @return 元素个数
     */
    public int size() {
        return count;
    }

    /**
     *
     * @return 链表是否为空
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     *
     * @param i 索引
     * @return 指定位置的元素
     */
    public E get(int i) {
        return getNode(i).item;
    }

    private Node<E> getNode(int i) {
        if (i < 0 || i >= size()) {
            throw new IllegalArgumentException("索引超出范围");
        }
        Node<E> temp;
        if (i < size()/2) {
            temp = first.next;
            for (int k = 0; k < i; k++) {
                temp = temp.next;
            }
        }else {
            temp = last.prev;
            for (int k = size()-1; k > i; k--) {
                temp = temp.prev;
            }
        }
        return temp;
    }

    /**
     * 添加结点
     * @param item  要添加的元素
     */
    public void add(E item) {
        Node<E> newNode;
        if (size() == 0) {
            newNode = new Node<>(first, item, last);
            first.next = newNode;
            last.prev = newNode;
        }else {
            newNode = new Node<>(getNode(size()-1), item, last);
            newNode.prev.next = newNode;
            newNode.next.prev = newNode;
        }
        count++;
    }

    /**
     * 在指定位置插入结点
     * @param i 索引
     */
    public void add(int i, E item) {
        if (i < 0 || i > size()) {
            throw new IllegalArgumentException("索引超出范围");
        }
        if (size() == 0) {
            add(item);
            return;
        }
        addBefore(i, item);
    }

    /**
     * 将某个元素添加到链表头部
     */
    public void addFirst(E item) {
        add(0, item);
    }

    /**
     * 将某个元素添加到链表尾部
     */
    public void addLast(E item) {
        add(item);
    }

    private void addBefore(int i, E item) {
        Node<E> newNode = new Node<>(getNode(i).prev, item, getNode(i));
        newNode.prev.next = newNode;
        getNode(i).prev = newNode;
        count++;
    }

    /**
     * 删除并返回指定位置的元素
     * @param i 索引
     */
    public E remove(int i) {
        Node<E> node = getNode(i);
        node.prev.next = node.next;
        node.next.prev = node.prev;
        count--;
        return node.item;
    }

    /**
     *
     * @return 删除并返回头部的元素
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     *
     * @return 删除并返回尾部的元素
     */
    public E removeLast() {
        return remove(size()-1);
    }

    /**
     *
     * @return 得到并头部的元素
     */
    public E getFirst() {
        return get(0);
    }

    /**
     *
     * @return 得到尾部的元素
     */
    public E getLast() {
        return get(size()-1);
    }

    /**
     * 用一个新的元素替换指定位置的元素，并返回原来那个元素
     * @param i 索引
     * @param newItem 新的元素
     * @return 原来的元素
     */
    public E set(int i, E newItem) {
        E oldItem = get(i);
        getNode(i).item = newItem;
        return oldItem;
    }

    /**
     *
     * @param item 要查找的元素
     * @return 与指定元素相等的元素在列表中第一次出现的位置，如果没有这样的元素则返回-1
     */
    public int indexOf(E item) {
        int count = 0;
        for (Node<E> x = first.next; x != last; x = x.next) {
            if (x.item.equals(item)) {
                return count;
            }
            count++;
        }
        return -1;
    }

    /**
     *
     * @param item 要查找的元素
     * @return 与指定元素相等的元素在列表中最后一次出现的位置，如果没有这样的元素则返回-1
     */
    public int lastIndexOf(E item) {
        int count = 0;
        for (Node<E> x = last.prev; x != first; x = x.prev) {
            if (x.item.equals(item)) {
                return count;
            }
            count++;
        }
        return -1;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (Node<E> x = first.next; x != last; x = x.next) {
            s.append(x.item + " ");
        }
        return s.toString();
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListIterator();
    }

    private class LinkedListIterator implements Iterator<E> {
        private Node<E> current = first;

        @Override
        public boolean hasNext() {
            return current.next != last;
        }

        @Override
        public E next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            E item = current.next.item;
            current = current.next;
            return item;
        }
    }

    public static void main(String[] args) {
        LinkedList<String> LinkedList = new LinkedList<>();
        LinkedList.addFirst("A");
        LinkedList.addFirst("B");
        LinkedList.addLast("C");
        LinkedList.add("D");
        LinkedList.add(3, "E");
        System.out.println(LinkedList);

        System.out.println("------------------------");

        LinkedList.removeFirst();
        LinkedList.removeLast();
        System.out.println(LinkedList);

        System.out.println("-------------------------");

        for (String str : LinkedList) {
            System.out.println(str);
        }
    }
}
