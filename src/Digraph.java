import java.util.LinkedList;
import java.util.List;

public class Digraph{

    List<Edge>[] graph;

    @SuppressWarnings("unchecked")
    public Digraph(int R) {
        this.graph = new List[R*2+1];
        for(int i = 0; i <= R*2; i++){
            this.graph[i] = new LinkedList<>();
        }

    }
    public void addEdge(int x, int y,int capacity){
        graph[x].add(new Edge(x,y-1,capacity));
        graph[y].add(new Edge(y,x-1,capacity));
        graph[y-1].add(new Edge(y-1,x,0));
        graph[x-1].add(new Edge(x-1,y,0));

    }

    public void addNode(int x, int departure){
        graph[x*2-1].add(new Edge(x*2-1,x*2,departure));
        graph[x*2].add(new Edge(x*2,x*2-1,0));
    }

    public void addFont(int x, int population){
        graph[0].add(new Edge(0,x*2-1,population));
        graph[x*2-1].add(new Edge(x*2-1,0,0));
    }

    public int nrOfNodes(){
        return graph.length;
    }
    public List<Edge> getEdges(int i){
        return graph[i];
    }

    @Override
    /*
     * For Debug reasons
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < graph.length; i++) {
            sb.append("Vertex ").append(i).append(": ");
            List<Edge> edges = graph[i];
            for (Edge edge : edges) {
                sb.append("(").append(edge.node1()).append(", ")
                        .append(edge.node2()).append(", ")
                        .append(edge.capacity()).append(") ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
record Edge(int node1, int node2, int capacity){}
