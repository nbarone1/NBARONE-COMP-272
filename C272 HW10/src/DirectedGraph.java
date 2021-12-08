
import java.util.*;
import java.io.*;
public class DirectedGraph  {
    ArrayList<DirectedNodeList> dGraph;
    int numVertex;
    boolean [] marked;
    int [] finishing;
    int counter;
    ArrayList<Integer> leaders;
    // hashmap for storing leader and its sc
    HashMap<Integer,ArrayList<Integer>> components;
    // array list for storing components of DFT leaders
    ArrayList<Integer> comps;
    // leader array to mark vertex's leader
    int [] leaderSet;
    ArrayList<Edge> edges;
    int max;
    
    public DirectedGraph() {
        dGraph = new ArrayList<>();
        numVertex=0;
    }
    
    public DirectedGraph(int n) {
        numVertex =n;
        dGraph = new ArrayList<>(n);
        marked= new boolean[n];
        finishing = new int[n];
        counter = 0;
        leaders = new ArrayList<>();
        leaderSet = new int[n];
        components = new HashMap<>();
        comps = new ArrayList<>();
        for (int i=0;i<numVertex;i++) dGraph.add(new DirectedNodeList());
        edges = new ArrayList<>();
        int max = 0;
    }
    
    public void addEdge(int u, int v) {
       // assume all vertices are created
       // directed edge u to v will cause outdegree of u to go up and indegree of v to go up.
       
       if (u>=0 && u<numVertex && v>=0 && v<numVertex) { 
	      if (u!=v) {
           getNeighborList(u).addToOutList(v);
           getNeighborList(v).addToInList(u);
           edges.add(new Edge(u,v));
		  }
        }
        else throw new IndexOutOfBoundsException();
    }
    
    public DirectedNodeList getNeighborList(int u) {
        return dGraph.get(u);
    }
    
    public void printAdjacency(int u) {
       DirectedNodeList dnl = getNeighborList(u);
       System.out.println ("vertices going into "+u+"  "+dnl.getInList());
       System.out.println ("vertices going out of "+u+"  "+dnl.getOutList());
       System.out.println();
    }
    
    public void postOrderDepthFirstTraversal() {
       for (int i=0;i<numVertex;i++) 
       if (!marked[i])
           postOrderDFT (i);
       
    }
    public void postOrderDFT(int v){
        
        marked[v]=true;
        
        for (Integer u:dGraph.get(v).getOutList())
        if (!marked[u]) postOrderDFT(u);
    }
    
    public void depthFirstTraversal() {
       for (int i=0;i<numVertex;i++) 
       if (!marked[i])
           dFT (i);
       
    }
    public void finDFT() {
        for(int i = numVertex-1;i>=0;i--){
            int vert = finishing[i];
            if(!marked[vert]){
                comps.clear();
                comps.add(vert);
                fDFT(vert);
                components.put(vert,comps);
                if (max<=comps.size()) max = comps.size();
            }
        }
    }
    public void dFT(int v){
        marked[v]=true;
        
        for (Integer u:dGraph.get(v).getOutList())
        if (!marked[u]) dFT(u);
       
    }

    public void fDFT(int v){
        // add v to arraylist
        // mark in leader array what that vertex's leader is leaderarray[v] = arraylist.first();
        leaderSet[v] = comps.get(0);
        marked[v]=true;

        for (Integer u:dGraph.get(v).getOutList())
            if (!marked[u]) {
                comps.add(u);
                fDFT(u);
            }

    }

    public void reversePDFT(){
        for (int i=0;i<numVertex;i++)
            if (!marked[i])
                postOrderRDFT (i);
    }

    public void postOrderRDFT(int v){

        marked[v]=true;

        for (Integer u:dGraph.get(v).getInList()) {
            if (!marked[u]) postOrderRDFT(u);
        }
        finishing[counter++] = v;
    }

    public void scc(){
        reversePDFT();
        for (int i = 0;i<numVertex;i++){
            marked[i] = false;
        }
        finDFT();
    }
    
    
    public static void main(String[] args) throws FileNotFoundException {
        int n = 82168;
        Scanner scanner = new Scanner(new File("Slashdot0902.txt"));
        DirectedGraph dg = new DirectedGraph(n);
        while(scanner.hasNextInt()){
            dg.addEdge(scanner.nextInt(), scanner.nextInt());
        }
        dg.scc();
        System.out.println("There are "+dg.components.size()+" SCCs with max size "+ dg.max);

        HashDirectedGraph hDG = new HashDirectedGraph(dg.components.size());
        // use results from scc to create new directed graph that is reduced to a DAG
        for (Integer integer : dg.components.keySet().toArray(new Integer[0])) {
            hDG.addVertex(integer);
        }
        // set labels based on leaders val ascending
        for (Edge e : dg.edges){
            if (dg.leaderSet[e.v1]!=dg.leaderSet[e.v2]){
                if(!hDG.isEdgePresent(dg.leaderSet[e.v1],dg.leaderSet[e.v2])){
                    hDG.addEdge(dg.leaderSet[e.v1],dg.leaderSet[e.v2]);
                }
            }
        }
        System.out.println(hDG.hDGraph.keySet().size()+" vertices and "+ hDG.numEdges+" edges.");
            // check for edge between two scc's, add edge based on direction of edges found
        // create new DAG so vertices can be in different order -- HashDirectedGraph (labels do not need to be consecutive)
            // use hash as the values ::: hashmap(label, directed node list)
    }
    

   
}
