public class MyBigInteger{

    MyLinkedList<Integer> bigI;
    
    public MyBigInteger () {
        
        bigI =new MyLinkedList<>();
    }
    
    // takes a numerically valued String p and stores it one digit at a time in the linked list
    // example, MyBigInteger("383023322") will store the integer 383023322 in the linked list 
    // one digit per node. 
    public MyBigInteger(String p) {
        Node<Integer> temp = new Node<Integer>();
        Integer [] a = new Integer[p.length()];
        char [] c = p.toCharArray();
        for(int i = 0;i<c.length;i++) {
            int t = c[i];
            a[i]=t;
        }
        for(int i = 0;i<p.length();i++) {
            bigI.addLast(a[i]);
        }
    }
    
    //add(..) adds this MyBigInteger to other MyBigInteger and returns the sum as a MyBigInteger
    // the original Big Integers don't change.
    public MyBigInteger add(MyBigInteger other) {
        MyBigInteger bI = new MyBigInteger();
        Node<Integer> temp1 = bigI.last;
        Node<Integer> temp2 = other.bigI.last;
        Node<Integer> temp3 = new Node<>();
        Integer carry = 0;
        for(int i = 0; i < bigI.size;i++){
            temp3.setInfo(temp1.info+ temp2.info);
            temp1 =temp1.getPrev();
            temp2 = temp2.getPrev();
            Integer val = temp1.info+carry;
            if (val > 9) {
                bI.bigI.addFirst(val-10);
                carry = 1;
            }
            else {
                bI.bigI.addFirst(val);
                carry = 0;
            }
        }
        return bI;
    }
    
    // returns true if and only if the two big integers are equal
    public boolean equals(Object other) {
        MyLinkedList<Integer> oL = ((MyLinkedList<Integer>) other);
        if(bigI.size != oL.size) throw new IndexOutOfBoundsException();
        for(int i =0; i< oL.size;i++) {
            if (!bigI.get(i).equals(oL.get(i))) return false;
        }
        return true;
    }
    // returns true if and only if this MyBigInteger is less than other MyBigInteger
    
    public boolean lessThan(MyBigInteger other) {
        if(bigI.size != other.bigI.size) throw new IndexOutOfBoundsException();
        for(int i = 0;i<bigI.size;i++){
            if(bigI.get(i)>other.bigI.get(i)) return true;
        }
        return false;
    }
}
