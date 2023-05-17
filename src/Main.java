import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String[] inputs = in.readLine().split(" ");
        int R = Integer.parseInt(inputs[0]);
        int L = Integer.parseInt(inputs[1]);
        Digraph graph  = new Digraph(R);
        for(int i= 1; i<=R; i++){
            String[] inputs2 = in.readLine().split(" ");
            int population = Integer.parseInt(inputs2[0]);
            int capacity = Integer.parseInt(inputs2[1]);
            graph.addNode(i,capacity);
            graph.addFont(i,population);
        }
        for(int i =0;i<L;i++){
            String[] inputs2 = in.readLine().split(" ");
            int x = Integer.parseInt(inputs2[0]);
            int y = Integer.parseInt(inputs2[1]);
            graph.addEdge(x*2,y*2,Integer.MAX_VALUE);
        }
        int safe = Integer.parseInt(in.readLine());
        MaxFlow m = new MaxFlow();
        int maxFlow = m.edmondsKarp(0,safe*2 -1,graph);
        System.out.println(maxFlow);
    }
}