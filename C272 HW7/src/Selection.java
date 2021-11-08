import java.util.*;

public class Selection <E extends Comparable<E>>{
    final int k;
    final ArrayList<E> input;
    public Selection(int n){
        k = n;
        input = new ArrayList<>();
    }

    public E oneB(){
        ArrayList<E> s = new ArrayList<>();
        for(int i = 0; i <input.size();i++){
            s.add(input.get(i));
        }
        Collections.sort(s);
        Collections.reverse(s);
        return s.get(k-1);
    }

    // Algorithm to build maxheap and then delete root k-1 times to find kth largest
    public E sixA(){
        PriorityQueue<E> sA = new PriorityQueue<E>();
        sA.addAll(input);
        // trying to use a max heap and then using k-1 polls does not yield the correct result, neither does it in a min heap
        for (int i = 0;i<input.size()-k;i++) sA.poll();
        return sA.peek();
    }

    // Algorithm to improve on 1B
    // Set of size k elements, replace Sk with new element as desire.
    public E sixB(){
        PriorityQueue<E> sB = new PriorityQueue<E>();
        for(int i = 0;i<k;i++){
            sB.add(input.get(i));
        }
        for(int i = k;i<input.size();i++){
            if(input.get(i).compareTo(sB.peek())>0){
                sB.poll();
                sB.add(input.get(i));
            }
        }
        return sB.peek();
    }
}
