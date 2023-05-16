import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Digraph implements Cloneable{

    List<Edge>[] graph;




    @SuppressWarnings("unchecked")
    public Digraph(int R) {
        graph = new List[R*2+1];
        for(int i = 0; i <= R*2; i++){
            graph[i] = new LinkedList<>();
        }

    }
    public void addEdge(int x, int y,int capacity){
        graph[x].add(new Edge(x,y-1,capacity));
        graph[y].add(new Edge(y,x-1,capacity));

    }
    public void addNormalEdge(int x, int y,int capacity){
        graph[x].add(new Edge(x,y,capacity));
    }
    public void addNode(int x, int departure){
        graph[x*2-1].add(new Edge(x*2-1,x*2,departure));
    }

    public void addFont(int x, int population){
        graph[0].add(new Edge(0,x*2-1,population));
        //graph[x*2-1].add(new Edge(x*2-1,0,population));
    }

    public List<Edge> getEdges(){
        List<Edge> edges = new ArrayList<>();
        for(List<Edge> list : graph){
            edges.addAll(list);
        }
        return edges;
    }

    public int nrOfNodes(){
        return graph.length;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public List<Edge> getEdges(int i){
        return graph[i];
    }

    public boolean edgeExists(int node1,int node2){
        for(Edge edge : graph[node1]){
            if(edge.getNode2() == node2){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < graph.length; i++) {
            sb.append("Vertex ").append(i).append(": ");
            List<Edge> edges = graph[i];
            for (Edge edge : edges) {
                sb.append("(").append(edge.getNode1()).append(", ")
                        .append(edge.getNode2()).append(", ")
                        .append(edge.getCapacity()).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
