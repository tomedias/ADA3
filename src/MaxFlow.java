import java.util.LinkedList;
import java.util.Queue;

public class MaxFlow {

    public int edmondsKarp(int source,int sink,Digraph graph){
        int numNodes = graph.nrOfNodes();
        int[][] flow = new int[numNodes][numNodes];
        int[] via = new int[numNodes];
        int flowValue = 0;
        int increment;
        while((increment = findPath(graph,flow,via,source,sink)) != 0){
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

    private int findPath(Digraph graph, int[][] flow, int[] via, int source, int sink) {
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
               int destin = edge.node2();
               int residue = edge.capacity() - flow[origin][destin];
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
