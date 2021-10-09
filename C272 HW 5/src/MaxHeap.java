
import java.util.*;
public class MaxHeap<E extends Comparable<E>> extends ArrayList<E> {
    // construct an empty Heap using ArrayList
    // with root at index 0.
    // don't use binary tree for implementing the heap.
    ArrayList<E> heap;

    public MaxHeap() {
        MaxHeap<E> heap = null;
    }

    // returns max value
    public E findMax() {
        // property of MaxHeap is the root is the max value
        // adding check feature in case property has been broken
        E max = this.get(0);
        for (int i = 0; i < this.size(); i++) {
            if (max.compareTo(this.get(i)) < 0) throw new NoSuchElementException();
        }
        return max;
    }

    // adds a new value to the heap at the end of the Heap and 
    // adjusts values up to the root to ensure Max heap property is satisfied.
    // parent of node at i is given by the formula (i-1)/2
    // throw appropriate exception
    public void addHeap(E val) {
        for (int i = 0; i < this.size(); i++) if (this.get(i).equals(val)) return;
        this.add(val);
        order(this.size() - 1);
    }

    //returns the max value at the root of the heap by swapping the last value 
    // and percolating the value down from the root to preserve max heap property
    // children of node at i are given by the formula 2i+1,2i+2, to not exceed the
    // bounds of the Heap index, namely, 0 ... size()-1.
    // throw appropriate exception
    public E removeHeap() {
        E remove = null;
        if (this.size() == 0) return remove;
        shift(0, this.size() - 1);
        remove = this.get(this.size() - 1);
        remove(this.size() - 1);
        for (int i = 0; i < this.size(); i++) order(i);
        System.out.print(remove + " ");
        return remove;
    }

    // takes a list of items E and builds the heap and then prints 
    // decreasing values of E with calls to removeHeap().  
    public void heapSort(List<E> list) {
        MaxHeap<E> sort = new MaxHeap<>();
        sort.buildHeap(list);
        for (int i = 0; i < sort.size(); i++) removeHeap();
    }

    // merges the other maxheap with this maxheap to produce a new maxHeap.  
    public void heapMerge(MaxHeap<E> other) {
        for (int i = 0; i < other.size(); i++) this.addHeap(other.get(i));
    }

    //takes a list of items E and builds the heap by calls to addHeap(..)
    public void buildHeap(List<E> list) {
        for (int i = 0; i < list.size(); i++) this.addHeap(list.get(i));
    }

    public static void main(String[] args) {
        MaxHeap<Integer> test = new MaxHeap<>();
        for (int i = 0; i < 10; i++) {
            test.addHeap(i);
        }
        System.out.println(test.findMax());
        MaxHeap<Integer> sorting = new MaxHeap<>();
        List<Integer> list = new ArrayList<Integer>();
        Random rn = new Random();
        for (int i = 10; i > 0; i--) list.add(rn.nextInt(15));
        for (int i = 0; i < test.size(); i++) System.out.print(test.get(i) + " ");
        System.out.println();
        sorting.buildHeap(list);
        for (int i = 0; i < sorting.size(); i++) System.out.print(sorting.get(i) + " ");
        System.out.println();
        test.heapMerge(sorting);
        for (int i = 0; i < test.size(); i++) System.out.print(test.get(i) + " ");
        System.out.println();
        sorting.heapSort(list);
    }

    private void shift(int first, int second) {
        E temp = this.get(first);
        this.set(first, this.get(second));
        this.set(second, temp);
    }

    private int leftChild(int pos) {
        return (pos * 2) + 1;
    }

    private int rightChild(int pos) {
        return (pos * 2) + 2;
    }

    private void order(int current) {
        int parent = (current - 1) / 2;
        if (this.get(current).compareTo(this.get((current - 1) / 2)) > 0) {
            shift(current, parent);
            current = parent;
            order(current);
        }
    }
}
