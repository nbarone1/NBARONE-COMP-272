import java.io.*;
import java.util.*;

public class Span {


    public static ArrayList<WeightedEdge> kruskalOne(int v, PriorityQueue<WeightedEdge> queue){
        ArrayList<MyLinkedList<Integer>>  vSet = new ArrayList<>(v);
        int parent[] = new int[v];
        for (int i = 0;i<v;i++){
            parent[i] = i;
            vSet.add(new MyLinkedList<>());
            vSet.get(i).addFirst(i);
        }
        ArrayList<WeightedEdge> mST = new ArrayList<>();
        while(mST.size()<v-1) {
            WeightedEdge temp = queue.poll();
            int tv = parent[temp.v1];
            int tu = parent[temp.v2];
            if (tv != tu) {
                mST.add(temp);
                kruskalMerge(parent,vSet,tu, tv);
            }
        }
        return mST;
    }

    public static ArrayList<WeightedEdge> pathCompression(int v, PriorityQueue<WeightedEdge> queue2){
        int parent[] = new int[v];
        int rank[] = new int[v];
        for (int i = 0;i<v;i++){
            parent[i] = i;
            rank[i] = 0;
        }
        ArrayList<WeightedEdge> mST = new ArrayList<>();
        while(mST.size()<v-1 && !queue2.isEmpty()) {
            WeightedEdge temp = queue2.poll();
            int tv = pathFind(parent,temp.v1);
            int tu = pathFind(parent,temp.v2);

            if (tu!=tv){
                mST.add(temp);
                pathMerge(parent,tv,tu,rank);
            }
        }
        return mST;
    }

    public static void kruskalMerge(int[] parent, ArrayList<MyLinkedList<Integer>> vSet, int v, int u){
        int p = v;
        int q = u;
        int max = 0;
        int min = 0;
        if (p!=q){
            int pSize = vSet.get(p).size;
            int qSize = vSet.get(q).size;
            if(pSize>qSize){
                min = q;
                max = p;
            }
            else {
                min = p;
                max = q;
            }
        }
        MyLinkedList<Integer> minSet = vSet.get(min);
        vSet.get(max).appendList(vSet.get(min));
        Node<Integer> mI = minSet.getFirst();
        while (mI != null) {
            parent[mI.getInfo()] = max;
            mI = mI.getNext();
            minSet.removeFirst();
        }
    }

    public static int pathFind(int[] parent, int v) {
        int r =v, i =v;
        while (parent[r] != r)
            r = parent[r];
        // r is the root of the tree
        while (i != r) {
            int j = parent[i];
            parent[i] = r;
            i = j;
        }
        return r;
    }

    public static void pathMerge(int[] parent, int v, int u, int[] height) {
        if (height[v] == height[u]) {
            height[v]++;
            parent[u] = v;
        }
        else {
            if (height[v] > height[u])
                parent[u] = v;
            else
                parent[v] = u;
        }
    }

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s1 = new Scanner(new File("Weights.txt"));
        Scanner s2 = new Scanner(new File("artist_edges.txt"));
        ArrayList<WeightedEdge> wel = new ArrayList<WeightedEdge>();
        int vertCount = 0;
        while (s1.hasNext()&&s2.hasNext()) {
            int a = s2.nextInt();
            int b = s2.nextInt();
            WeightedEdge e = new WeightedEdge(a,b, s1.nextDouble());
            wel.add(e);
            vertCount = Math.max(Math.max(a,b),vertCount);
        }
        vertCount = vertCount+1;

        PriorityQueue<WeightedEdge> pq = new PriorityQueue<>(wel);
        PriorityQueue<WeightedEdge> pq2 = new PriorityQueue<>(wel);
        long t = System.currentTimeMillis();
        ArrayList<WeightedEdge> mSTKruschal = kruskalOne(vertCount,pq);
        t = System.currentTimeMillis()-t;

        long t2 = System.currentTimeMillis();
        ArrayList<WeightedEdge> mSTPC = pathCompression(vertCount,pq2);
        t2 = System.currentTimeMillis()-t2;

        int m1 = 0;
        int m2 = 0;
        for(int i = 0;i<mSTKruschal.size();i++){
            m1 += mSTKruschal.get(i).weight;
        }
        for(int i = 0;i<mSTPC.size();i++){
            m2 += mSTPC.get(i).weight;
        }

        int eCheck1 = pq.size();
        int eCheck2 = pq2.size();

        System.out.println("Kruskal results: "+mSTKruschal.size()+" edges, "+m1+" minimum weight, "+eCheck1+" unchecked edges, "+t+" runtime.");
        System.out.println("Path Compression results: "+mSTPC.size()+" edges, "+m2+" minimum weight, "+eCheck2+" unchecked edges, "+t2+" runtime.");
    }
}
