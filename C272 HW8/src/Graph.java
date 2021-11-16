import java.lang.invoke.*;
import java.util.*;
public class Graph {
    int numVertex;
    int numEdge;
    ArrayList<LinkedList<Integer>> graph;

    public Graph() {

        numVertex = 0;
        numEdge = 0;
        graph = new ArrayList<>();

    }

    public Graph(int vertexCount) {

        numVertex = vertexCount;
        numEdge = 0;
        graph = new ArrayList<>(numVertex);
        for (int i = 0; i < numVertex; i++)
            graph.add(new LinkedList<>());
    }


    public LinkedList<Integer> getNeighbors(int u) {

        return graph.get(u);
    }

    public boolean edgePresent(int u, int v) {

        return (graph.get(u).contains(v));

    }

    public void addEdge(int u, int v) {
        // lets assume that the vertices are already created.
        if (u >= 0 && u < numVertex && v >= 0 && v < numVertex) {
            if (!edgePresent(u, v))
                graph.get(u).addLast(v);

            if (!edgePresent(v, u))
                graph.get(v).addLast(u);
            numEdge++;
        } else throw new IndexOutOfBoundsException();

    }

    public void fill(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (a.size() == b.size()) {
            for (int i = 0; i < a.size(); i++) {
                addEdge(a.get(i), b.get(i));
            }
        }
    }

    public int depth() {
        int components = 0;
        boolean visit[] = new boolean[numVertex];
        for(int i = 0;i<numVertex;i++) {
            if(!visit[i]){
                dfs(i,visit);
                components++;
            }
        }
        return components;
    }

    public void dfs(int i, boolean[] visited) {
        int m = 0;
        LinkedList<Integer> nghbr = new LinkedList<>();
        visited[i] = true;
        nghbr.add(i);
        while(nghbr.size()!=0) {
            i = nghbr.pop();
            Iterator<Integer> iter = this.getNeighbors(i).listIterator();
            while(iter.hasNext()){
                int nInd = iter.next();
                if(!visited[nInd]){
                    visited[nInd] = true;
                    nghbr.add(nInd);
                    m++;
                }
            }
        }
    }

    public void fillFly(ArrayList<Integer> a, ArrayList<Integer> b) {
        if (a.size() == b.size()) {
            int places[] = new int[numVertex];
            for (int i = 0; i < a.size(); i++) {
                fly(a.get(i), b.get(i),places);
            }
        }
    }

    public void fly(int u, int v,int[] places) {
        if(places[u]==0 && places[v]==0) {
            if (graph.get(u).size() >= graph.get(v).size()) {
                union(u, v);
                graph.get(v).removeAll(graph.get(v));
                places[v] = u;
            } else {
                union(v, u);
                graph.get(u).removeAll(graph.get(u));
                graph.get(u).addLast(v);
            }
        }
        if(places[u] !=0){
            while(places[u]!=0){
                u = places[u];
            }
            union(v, u);
            graph.get(u).removeAll(graph.get(u));
            graph.get(u).addLast(v);
        }
        if(places[u] != 0){
            while(places[v]!=0){
                union(u, v);
                graph.get(v).removeAll(graph.get(v));
                places[v] = u;
            }
        }
    }

    public void bfs(int s) {
        boolean visited[] = new boolean[numVertex];
        LinkedList<Integer> neighbor = new LinkedList<>();
        visited[s] = true;
        neighbor.add(s);
        while (neighbor.size() != 0) {
            neighbor.pop();
            Iterator it = this.getNeighbors(s).listIterator();
            while (it.hasNext()) {
                int a = (int) it.next();
                if (!visited[a]) {
                    visited[a] = true;
                    neighbor.add(a);
                }
            }
        }
    }

    public void union(int a, int b) {
        for (int i = 0; i < graph.get(b).size(); i++) {
            if (!graph.get(a).contains(graph.get(b).get(i))) {
                graph.get(a).addLast(graph.get(b).get(i));
            }
        }
    }

    public int trees(){
        int tree = 0;
        boolean visits[] = new boolean[numVertex];
        for(int i = 0;i<numVertex;i++){
            if (!visits[i]&&cycle(i,visits,-1)){
                tree++;
            }
        }
        return tree;
    }

    public boolean cycle(int c, boolean[] vis,int d){
        vis[c] = true;
        int a;
        Iterator<Integer> iter = this.getNeighbors(c).listIterator();
        while(iter.hasNext()){
            a = iter.next();
            if(!vis[a] && cycle(a,vis,c)){
                return true;
            } else if (a!=c) {
                return false;
            }
        }
        return false;
    }
    public int maxSize(){
        int max = 0;
        for(int i =0;i<numVertex;i++){
            if (max<graph.get(i).size()) {
                max = graph.get(i).size();
            }
        }
        return max;
    }
}