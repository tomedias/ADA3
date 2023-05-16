public class Edge {
    private int node1;
    private int node2;

    private int capacity;

    public Edge(int node1,int node2,int capacity){
        this.node1 = node1;
        this.node2 = node2;
        this.capacity = capacity;
    }

    public int getNode1() {
        return node1;
    }

    public int getNode2() {
        return node2;
    }

    public int getCapacity() {
        return capacity;
    }
}
