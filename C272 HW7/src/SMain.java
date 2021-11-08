import java.util.*;


public class SMain {
    public static void main(String[] args) {
        int k = 1000000;
        Selection<Integer> s = new Selection<>(k);
        Selection<Integer> t = new Selection<>(k);
        for(int i = 10000000;i>0;i--){
            t.input.add(i);
            Random rn = new Random();
            int a = rn.nextInt();
            s.input.add(a);
        }
        Long t1b = System.currentTimeMillis();
        System.out.println(t.oneB());
        t1b = System.currentTimeMillis()-t1b;
        System.out.println(t1b+" time");
        Long t6a = System.currentTimeMillis();
        System.out.println(t.sixA());
        t6a = System.currentTimeMillis()-t6a;
        System.out.println(t6a+" time");
        Long t6b = System.currentTimeMillis();
        System.out.println(t.sixB());
        t6b = System.currentTimeMillis()-t6b;
        System.out.println(t6b+" time");
        t1b = System.currentTimeMillis();
        System.out.println(s.oneB());
        t1b = System.currentTimeMillis()-t1b;
        System.out.println(t1b+" time");
        t6a = System.currentTimeMillis();
        System.out.println(s.sixA());
        t6a = System.currentTimeMillis()-t6a;
        System.out.println(t6a+" time");
        t6b = System.currentTimeMillis();
        System.out.println(s.sixB());
        t6b = System.currentTimeMillis()-t6b;
        System.out.println(t6b+" time");
    }
}
