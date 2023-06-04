package GraphFramework;

import java.util.*;

public class KruskalAlg extends MSTAlgorithm {

    public KruskalAlg(Graph graph) {
        super(graph);
    }

    public void kruskal() {
        // Start time
        long startTime = System.currentTimeMillis();

        MSTresultList = new ArrayList<>(); // Add the result to linked list
        ArrayList<Edge> edgeList = new ArrayList<>();

        // Clone existing vertices' adjacent lists
        for (Vertex vertice : graph.vertices) {
            for (int j = 0; j < vertice.adjList.size(); j++) {
                edgeList.add(vertice.adjList.get(j));
            }
        }

        // Sort edges based on weight
        edgeList.sort((o1, o2) -> o1.weight.compareTo(o2.weight));

        // Array to store the Edge 
        Edge[] parent = new Edge[graph.vertices.length];

        // Loop used for stored edges
        for (int i = 0; i < graph.vertices.length; i++) {
            parent[i] = VEcreator.createEdge();
        }

        //invoke
        makeSet(parent);

        int index = 0;
        //Number of edges to be taken is equal to verticesNo-1
        while (index < graph.verticesNo - 1 && !edgeList.isEmpty()) {
            Edge edge = edgeList.remove(0);

            //check if adding this Edge creates a cycle
            int x = find(parent, edge.source.labelNumber);
            int y = find(parent, edge.target.labelNumber);

            if (x != y) {
                //add to final result
                Edge tempEdge = VEcreator.createEdge();
                tempEdge.setSource(edge.source);
                tempEdge.setTarget(edge.target);
                tempEdge.setWeight(edge.weight);

                MSTresultList.add(tempEdge);

                union(parent, x, y);
            }
        }
        //print cost
        DisplayResultingMST(MSTresultList);
        //end time
        long endTime = System.currentTimeMillis();
        //print time
        System.out.println("Total runtime of Kruskal's Algorithm: " + (endTime - startTime) + " ms.");
    }
    //creat new element with parent

    public void makeSet(Edge[] parent) {
        for (int i = 0; i < parent.length; i++) {
            parent[i].source = createVertex();
            parent[i].source.labelNumber = i;

        }
    }

    //------------------ find method ----------------------------------
    public int find(Edge[] parent, int vertex) {
        if (parent[vertex].source.labelNumber != vertex) {
            return find(parent, parent[vertex].source.labelNumber);
        }
        return vertex;
    }

    public void union(Edge[] parent, int x, int y) {
        int xp = find(parent, x);
        int yp = find(parent, y);
        //make x as parent of y
        parent[yp].source.labelNumber = xp;
    }

    @Override
    public void DisplayResultingMST(ArrayList<Edge> MSTresultList) {
        MSTresultList.sort((o1, o2) -> o1.weight.compareTo(o2.weight));

        int cost = 0;
        for (int i = 0; i < MSTresultList.size(); i++) {
            Edge edge = MSTresultList.get(i);
            cost += edge.weight;

            if (MSTresultList.size() <= 10)
                edge.displayInfo();
        }

        System.out.println("-----------------------------------------------------\n"
                + "The cost of designed phone network using Kruskal's algorithm: " + cost);

    }
}
