import java.util.*;
public class ExtLinkedList<E> extends LinkedList<E> {
    int size;
    private ListIterator<E> listIterator;

    ExtLinkedList(){
        size = 0;
    }

    public ArrayList<E> cloneLinkedList() {
        ArrayList<E> al = new ArrayList<E>();
        ListIterator<E> li = this.listIterator();
        while(li.hasNext())
            al.add(li.next());
        return al;
    }

    public ExtLinkedList<E> secondHalfList() {
        int m = this.size();
        int n = m/2;

        ExtLinkedList<E> half = new ExtLinkedList();
        while(n<m){
            half.add(this.get(n));
            n++;
        }

        return half;
    }

    public ExtLinkedList<E> oddList(){
        int n = 1;
        int s = this.size();
        ExtLinkedList<E> odd = new ExtLinkedList<E>();
        while(n<=s){
            odd.add(this.get(n));
            n = n+2;
        }
        return odd;
    }

    public ExtLinkedList<E> evenList(){
        int n = 0;
        int s = this.size();
        ExtLinkedList<E> even = new ExtLinkedList<E>();
        while(n<s){
            even.add(this.get(n));
            n = n+2;
        }
        return even;
    }
}