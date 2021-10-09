
import java.util.*;
public class MyLinkedList<E>
{
    Node<E> first;
    Node<E> last;
    int size;

    public MyLinkedList()
    {
       first = null;
       last = null;
       size=0;
    }
    
    public boolean isEmpty() {
        
        return (size==0);
        
    }
    
    public void addFirst(E info) {
        
        Node<E> n =new Node<>();
        n.setInfo(info);
        
       if (isEmpty())  
           last=n;
           
    
       else {
        n.setNext(first);
        first.setPrev(n);
        
    }
    first=n;
    size++;
        
    }
    
    public E removeFirst() {
        
    if (!isEmpty()) {
        
    E val = first.getInfo();
        if (size>1) {
       
        first.getNext().setPrev(null);
        first=first.getNext();
        size--;
        
    }
    else if (size==1) {
       
         first=null;
         last=null;
         size--;
         
        
    }
      return val;  
    }
    else 
    throw new NoSuchElementException();

    }
    
    
    public E removeLast() {
        
    if (!isEmpty()) {
        
   
        E val = last.getInfo();
        if (size>1) {
        last.getPrev().setNext(null);
        last=last.getPrev();
        size--;
    }
       else if (size==1) {
           
         first=null;
         last=null;
         size--;
        }
        return val;
    }
    else 
    
        throw new NoSuchElementException();
}   
//incomplete code below
//handle empty list situation    
    public E remove(int k) {

        if(isEmpty()) throw new NoSuchElementException();
        
        if (!isEmpty()) {
        Node<E> temp = first;
        
        if ((k>=0) && (k<size)) {
            if (k==0) return removeFirst();
            else if (k==size-1) return removeLast();
            else {
                // get to k
               // int i=0;
                for (int i=0;i<k;i++) 
                temp = temp.getNext();
                E val=temp.getInfo();
              temp.getPrev().setNext(temp.getNext());
              temp.getNext().setPrev(temp.getPrev());
              size--;
              return val;
                
            }
        }
        else throw new IndexOutOfBoundsException();
    }
            else {
                System.out.println("list empty ..");
            
                throw new NoSuchElementException();
        }
    }
    // adds an item to the end of the list with info field set to val
    public void addLast(E val) {
        Node<E> temp = new Node<>();
        temp.setInfo(val);
        temp.setPrev(last);
        last.setNext(temp);
        last = temp;
        size++;
    }
    
    // prints all items in the list from first to last taking care of situations when the list is empty
    // use exception handling
    public void printListForward() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        else {
            Node<E> temp = first;
            for(int i = 0; i <size;i++){
                System.out.print(temp.getInfo());
                temp = temp.next;
            }
        }
    }
    
    // prints all items in the list from last to first taking care of situations when the list is em
    // use exception handling
    public void printListBackward() {
        if(isEmpty()) {
            throw new NoSuchElementException();
        }
        Node<E> temp = last;
        for(int i = size;i>0;i--){
            System.out.print(temp);
            temp = temp.prev;
        }
        
    }
    
    //returns true if and only if the list contains at least one element e such that 
    //Objects.equals(o,e)
    //return false if the list is empty
    public boolean contains(Object o) {
        if (isEmpty()) return false;
        for(int i = 0; i<=size;i++){
            if(this.get(i).equals(o)) return true;
        }
        return false;
    }
    
    // brings the current list back to an empty list
    public void clear() {
        for(int i = 0; i <size;i++){
            removeFirst();
            size--;
        }
    }
    // returns the info value stored at node i 
    // throw IndexOutOfBounds exception of i is out of bounds or the list is empty
    public E get(int i) {
        if(isEmpty() || i >=size) throw new IndexOutOfBoundsException();
        Node<E> temp = first;
        int j = 0;
        while (j != i){
            temp = temp.next;
        }
        E e = temp.getInfo();
        return e;
    }
    
    // compares this MyLinkedList with the parameter otherList 
    // and returns true if the linkedlists have identical values from beginning to end
    // same size and this.get(i).equals(otherList.get(i)) should be true for all i
    // lists can be empty in which case return true
    //should run in O(n) time where n is the size of each list.
    public boolean equals(Object otherList) {
        MyLinkedList<E> oL = ((MyLinkedList<E>) otherList);
        Node<E> temp = first;
        if (size != oL.size) throw new IndexOutOfBoundsException();
        for(int i = 0; i < size;i++){
            if (!temp.getInfo().equals(oL.get(i))) return false;
            temp = temp.next;
        }
        return true;
    }
    
    
}
    
    