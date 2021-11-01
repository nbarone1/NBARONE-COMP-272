import java.util.*;
import java.security.*;
import java.io.*;

public class MyHashSet {
    public int tableSize;
    /// sizes will be 262,127 and 524,287 for 1,2
    /// sizes will be 262,144 and 524,288 for 3
    public ArrayList<LinkedList<String>> hT;
    public int collisions;
    double times;
    public MyHashSet(int s) {
        collisions = 0;
        tableSize = s;
        build(tableSize);
        times = 0;
    }

    public int hashCodeS(String s) {

        return Math.abs(s.hashCode())%tableSize;
    }

    private void build(int s){
        this.hT = new ArrayList<LinkedList<String>>();
        int i = 0;
        while(i<s){
            hT.add(new LinkedList<>());
            i++;
        }

    }

    public int hashCode(Object key){
        int h;
        return (key==null) ? 0 : (((Math.abs((h=key.hashCode())))^(h>>>16))%tableSize);
    }

    public int hashCodeBit(String s){
        byte[] sb = s.getBytes();
        byte[] key = null;
        try{
            MessageDigest md= MessageDigest.getInstance("SHA-256");
            key=md.digest(sb);
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        BitSet bs = BitSet.valueOf(key);
        int r = 0;
        int[] p = {2, 7,17,29,41,53,67,79,97,107,127,139,157,
                173,191, 199,227,239,241};

        for (int i = 0;i<(Math.log(tableSize)/Math.log(2));i++){
            if (bs.get(i)){
                r += p[i];
            }
        }
        return r;
    }

    public void addHash(Object k, int n){
        this.hT = hT;
        long s = System.currentTimeMillis();
        LinkedList<String> destination = hT.get(n);
        if (!destination.contains(k)){
            if (destination.size()>0){
                collisions++;
            }
            destination.addLast((String) k);
        }
        times += ((double) (s-System.currentTimeMillis()));
    }

    public double avgSize(){
        double num = 0;
        for (int i = 0; i < tableSize;i++){
            if (hT.get(i).size()>0) {
                num++;
            }
        }
        return (num+collisions)/num;
    }

    public static void main(String[] args) throws FileNotFoundException {
        File words = new File("EnglishWordList.txt");
        Scanner wordScanner = new Scanner(words);
        MyHashSet h1 = new MyHashSet(262127);
        MyHashSet h2 = new MyHashSet(524287);
        MyHashSet h3 = new MyHashSet(262127);
        MyHashSet h4 = new MyHashSet(524287);
        MyHashSet h5 = new MyHashSet(262144);
        MyHashSet h6 = new MyHashSet(524288);
        while(wordScanner.hasNext()){
            String k = wordScanner.next();
            h1.addHash(k, h1.hashCodeS(k));
            h2.addHash(k, h2.hashCodeS(k));
            h3.addHash(k, h3.hashCode(k));
            h4.addHash(k, h4.hashCode(k));
            h5.addHash(k, h5.hashCodeBit(k));
            h6.addHash(k, h6.hashCodeBit(k));
        }
        System.out.println(h1.collisions+" "+h1.avgSize()+""+h1.times);
        System.out.println(h2.collisions+" "+h2.avgSize()+""+h2.times);
        System.out.println(h3.collisions+" "+h3.avgSize()+""+h3.times);
        System.out.println(h4.collisions+" "+h4.avgSize()+""+h4.times);
        System.out.println(h5.collisions+" "+h5.avgSize()+""+h5.times);
        System.out.println(h6.collisions+" "+h6.avgSize()+""+h6.times);

    }
}
