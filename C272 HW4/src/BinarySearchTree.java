public class BinarySearchTree<E extends Comparable<E>> extends BinaryTree<E> {
    Node<E> root;

    public BinarySearchTree() {
        root = null;
    }

    public BinarySearchTree(E val) {
        root = new Node<E>(val);
    }

    // returns true if BST has val else false.
    public boolean contains(E val) {
        //if (root == null) return false;
        return reCo(root, val);
    }

    // inserts val at the right place satisfying search tree properties, should handle if the tree is empty
    // if value is already there then don’t insert it again
    public void insert(E val) {
        if(root == null) root = new Node<>(val);
        else {
            Node<E> cur = root;
            reIn(cur, val);
        }
    }

    // returns the minimum value stored in the tree
    public E findMin() {
        Node<E> n = root;
        E minVal = n.info;
        while(n.left!= null){
            n= n.left;
            if (n.info != null)
                if (minVal.compareTo(n.info)>0) minVal = n.info;
        }
        return minVal;
    }

    // returns the maximum value stored in the tree
    public E findMax() {
        Node<E> n = root;
        E maxVal = n.info;
        while(n.right!= null){
            n= n.right;
            if (n.info != null)
                if (maxVal.compareTo(n.info)<0) maxVal = n.info;
        }
        return maxVal;
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> bt= new BinarySearchTree<>();
        bt.insert(5);
        bt.insert(10);
        bt.insert(3);
        bt.insert(20);
        bt.insert(8);
        bt.insert(4);
        bt.preOrder(bt.root);
        System.out.println(bt.contains(8));
        System.out.println(bt.contains(21));
        System.out.println(bt.findMin());
        System.out.println(bt.findMax());
    }

    public boolean reCo(Node<E> n, E val){
        E x = n.info;
        if (n.info.compareTo(val) > 0) {
            if (n.left != null) {
                return reCo(n.left,val);
            }
            else return false;
        }
        else if (n.info.compareTo(val) < 0) {
            if (n.right != null) {
                return reCo(n.right,val);
            }
            else return false;
        }
        return n.info.compareTo(val) == 0;
    }
    public void reIn(Node<E> n, E val){
        if(n.info.compareTo(val)>0) {
            if (n.left == null){
                addLeft(n,val);
            }
            else reIn(n.left,val);
        }
        else if(n.info.compareTo(val)<0) {
            if (n.right == null){
                addRight(n,val);
            }
            else reIn(n.right,val);
        }
    }
}