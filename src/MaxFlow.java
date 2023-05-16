import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MaxFlow {


    public MaxFlow() {

    }


    private Digraph buildNetwork(Digraph digraph) throws CloneNotSupportedException {
        Digraph graph = (Digraph) digraph.clone();
        for(Edge edge : digraph.getEdges()) {
            int node1 = edge.getNode1();
            int node2 = edge.getNode2();
            if (!digraph.edgeExists(node2, node1))
                graph.addNormalEdge(node2, node1, 0);
        }
        return graph;
    }
    public int edmondsKarp(int source,int sink,Digraph digraph) throws CloneNotSupportedException {
        Digraph graph = buildNetwork(digraph);
        int numNodes = graph.nrOfNodes();
        int[][] flow = new int[numNodes][numNodes];
        int[] via = new int[numNodes];
        int flowValue = 0;
        int increment;
        while((increment = bfs(graph,flow,via,source,sink)) != 0){
            flowValue += increment;
            int node = sink;
            while(node != source){
                int origin = via[node];
                flow[origin][node] += increment;
                flow[node][origin] -= increment;
                node = origin;
            }
        }
        return flowValue;
    }

    int bfs(Digraph graph, int[][] flow, int[] via, int source,int sink) {
        Queue<Integer> queue = new LinkedList<>();
        int numNodes = graph.nrOfNodes();
        boolean[] found = new boolean[numNodes];
        int[] pathIncr = new int[numNodes];
        queue.add(source);
        found[source] = true;
        via[source] = source;
        pathIncr[source] = Integer.MAX_VALUE;
        do{
            int origin = queue.poll();
            for(Edge edge : graph.getEdges(origin)){
               int destin = edge.getNode2();
               int residue = edge.getCapacity() - flow[origin][destin];
               if(!found[destin] && residue>0){
                   via[destin] = origin;
                   pathIncr[destin] = Math.min(pathIncr[origin],residue);
                   if(destin==sink)
                       return pathIncr[destin];
                   queue.add(destin);
                   found[destin] = true;
               }


            }
        }while(!queue.isEmpty());
        return 0;
    }
}
