package PhoneNetworkApp;

import GraphFramework.Graph;
import GraphFramework.Vertex;
import GraphFramework.Edge;

public class BluePrGraph extends Graph {

    public BluePrGraph() {
        super();
    }

    public BluePrGraph(int verticesNo, int edgeNo) {
        super(verticesNo, edgeNo);
    }

    @Override
    public Vertex createVertex() {
        return new Office();
    }
    
    @Override
    public Edge createEdge() {
        return new Line();
    }

}
