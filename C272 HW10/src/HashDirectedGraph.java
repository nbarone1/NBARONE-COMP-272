
import java.util.*;
import java.io.*;
public class HashDirectedGraph {
    HashMap<Integer,DirectedNodeList> hDGraph;
    int numVertex;
    int numEdges;
    public HashDirectedGraph(){
        hDGraph = new HashMap<>();
        numVertex = 0;
        numEdges = 0;
    }

    public HashDirectedGraph(int n){
        hDGraph = new HashMap<>(n);
        numVertex = n;
        numEdges = 0;
    }

    // use keyset from hashmap
    // k is leader, between 0 and maxVertex label of original set
    public void addVertex(int k){
        if (!hDGraph.containsKey(k)) {
            hDGraph.put(k,new DirectedNodeList());
        }
    }

    public boolean isEdgePresent(int u, int v){
        // check if edge exists between u and v, return that
        return getNeighborList(u).inList.contains(v) || getNeighborList(u).outList.contains(v);
    }

    // we know number of components so we can do that
    public void addEdge(int u, int v) {
        // assume all vertices are created
        // directed edge u to v will cause outdegree of u to go up and indegree of v to go up.

        if (hDGraph.containsKey(u)&&hDGraph.containsKey(v)) {
            if ((u != v) && !isEdgePresent(u, v)) {
                getNeighborList(u).addToOutList(v);
                getNeighborList(v).addToInList(u);
            }
        }
        else throw new IndexOutOfBoundsException();
        numEdges++;
    }

    public DirectedNodeList getNeighborList(int u) {
        return hDGraph.get(u);
    }

}
