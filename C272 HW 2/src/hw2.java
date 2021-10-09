import java.util.*;
import java.math.*;

public class hw2 {
    public static void main(String[] args){
        ExtLinkedList<Integer> el = new ExtLinkedList();
        for(Integer i = 0; i < 10; i++){
            el.add(i);
        }
        System.out.println(el.oddList().cloneLinkedList());
        System.out.println(el.evenList().cloneLinkedList());
        System.out.println(el.secondHalfList().cloneLinkedList());
        String p = "wub a lub a dub dub";
        replaceChar(p,2,'d');
        System.out.println(replaceChar(p,2,'d'));
        int [][] arr = new int[10][10];
        for(int i=0;i<10;i++){
            for(int j = 0;j<10;j++){
                arr[i][j] = i+j;
            }
        }
        rowSum(arr);
        columnMin(arr);
        LinkedList<Integer> l = new LinkedList<Integer>(Arrays.asList(1,2,3,4,5,6,7));
        prefixSum(l);
        prefixSumReverse(l);
        LinkedList<String> a = new LinkedList<String>(Arrays.asList("abra","kadabra","wizard"));
        LinkedList<String> b = new LinkedList<String>(Arrays.asList("Harry","Potter","world"));
        System.out.println(alphaMerge(a,b));
        int [] ar = {1,2,3,4,5,6};
        kdiff(ar,2);
    }

    public static String replaceChar(String p, int k, char c){
        if (k<0 || k>=p.length()) throw new StringIndexOutOfBoundsException();
        char [] str = p.toCharArray();
        str[k] = c;
        p = String.valueOf(str);
        return p;
    }

    public static void rowSum(int[][] arr){
        if (arr.length != arr[0].length) throw new ArrayIndexOutOfBoundsException("Not square");
        int i =0 ;
        int j=0;
        for(int a = i; a < arr.length;a++){
            int sum = 0;
            for(int b = j; b < arr.length; b++){
                sum = sum+arr[a][b];
            }
            if (a== arr.length-1) System.out.print(sum+"\n");
            else System.out.print(sum+", ");
        }
    }

    public static void columnMin(int[][] arr){
        for(int i = 0;i<arr[0].length;i++){
            int cmin = arr[0][i];
            for (int[] ints : arr) {
                if (cmin > ints[i]) cmin = ints[i];
            }
            if (i<arr[0].length-1) System.out.print(cmin+", ");
            else System.out.print(cmin+"\n");
        }
    }

    public static void prefixSum(LinkedList<Integer> l){
        ListIterator<Integer> li = l.listIterator();
        Integer sum = 0;
        while(li.hasNext()){
            sum += li.next();
            if (li.hasNext()) System.out.print(sum+", ");
            else System.out.print(sum+"\n");
        }
    }

    public static void prefixSumReverse(LinkedList<Integer> l){
        ListIterator<Integer> li = l.listIterator();
        while(li.hasNext()) li.next();
        Integer sum = 0;
        while(li.hasPrevious()){
            sum += li.previous();
            if (li.hasPrevious()) System.out.print(sum+", ");
            else System.out.print(sum+"\n");
        }
    }
    // Capitals are read differently from the lower cases, must fix to sort properly
    public static LinkedList<String> alphaMerge(LinkedList<String> a, LinkedList<String> b){
        LinkedList<String> merge = new LinkedList<String>();
        ListIterator<String> ali = a.listIterator();
        ListIterator<String> bli = b.listIterator();
        String afront = ali.next().toLowerCase();
        String bfront = bli.next().toLowerCase();
        while (afront != null && bfront != null) {
            if(afront.compareTo(bfront)>0) {
                merge.add(bfront);
                if(bli.hasNext())bfront = bli.next().toLowerCase();
                else bfront=null;
            }
            else if (afront.compareTo(bfront)<0) {
                merge.add(afront);
                if(ali.hasNext())afront = ali.next().toLowerCase();
                else afront=null;
            }
            else if (afront.compareTo(bfront)==0){
                merge.add(afront);
                merge.add(bfront);
                if(bli.hasNext())bfront = bli.next().toLowerCase();
                else bfront=null;
                if(ali.hasNext())afront = ali.next().toLowerCase();
                else afront=null;
            }
        }
        while (bfront != null){
            merge.add(bfront);
            if(bli.hasNext())bfront = bli.next().toLowerCase();
            else bfront=null;
        }
        while (afront != null){
            merge.add(afront);
            if(ali.hasNext())bfront = ali.next().toLowerCase();
            else afront=null;
        }
        return merge;
    }

    public static void kdiff(int[] arr, int k){
        for(int i=0;i<arr.length;i++){
            for(int j = i;j< arr.length;j++){
                if(Math.abs(arr[i]-arr[j])==k){
                    System.out.print("("+arr[i]+", "+arr[j]+"), ");
                }
            }
        }
        System.out.print("are the pairs with "+k+" difference.\n");
    }
}