import java.io.*;
import java.util.*;

// java file for hw8
// creating graphs for enron email data, using the CSV provided in the project files
public class ArtistGraph {
    public static void main(String[] args) throws  FileNotFoundException{
        // Scanner scanner = new Scanner(new File("Email-Enron.txt"));
        Scanner scanner = new Scanner(new File("Graph.txt"));
        int i = 0;
        ArrayList<Integer> one = new ArrayList<>();
        ArrayList<Integer> two = new ArrayList<>();
        while(scanner.hasNext()) {
            if (i % 2 == 0) one.add((Integer.valueOf(scanner.next())));
            if (i % 2 == 1) two.add((Integer.valueOf(scanner.next())));
            i++;
        }
        int max = 0;
        for(int j = 0; j<one.size();j++){
            if (one.get(j)>=two.get(j)){
                if (one.get(j)>max) max = one.get(j);
            }
            else if(two.get(j)>max) max = two.get(j);
        }
        Graph initial = new Graph(max+1);
        double t = System.currentTimeMillis();
        initial.fillFly(one,two);
        System.out.println(System.currentTimeMillis()-t);
        int connected1 = 0;
        int connected2 = 0;
        int connected3 = 0;
        Graph initial2 = new Graph(max+1);
        initial2.fill(one,two);
        Graph initial3 = new Graph(max+1);
        initial3.fill(one,two);
        t = System.currentTimeMillis();
        initial.depth();
        System.out.println(System.currentTimeMillis()-t);
        t = System.currentTimeMillis();
        for(int q = 0;q<initial3.numVertex;q++) {
            initial3.bfs(q);
        }
        System.out.println(System.currentTimeMillis()-t);
        for(int h = 0;h<initial.numVertex;h++) {
            if(initial.graph.get(h).size()>1) connected1++;
            if(initial2.graph.get(h).size()>1) connected2++;
            if(initial3.graph.get(h).size()>1) connected3++;
        }
        System.out.println(connected1);
        System.out.println(connected2);
        System.out.println(connected3);
        System.out.println(initial.trees());
        System.out.println(initial2.trees());
        System.out.println(initial3.trees());
        int r = initial.maxSize();
        System.out.println(initial.maxSize());
        System.out.println(initial2.maxSize());
        System.out.println(initial3.maxSize());
    }
}
