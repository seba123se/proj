package GraphFramework;

import PhoneNetworkApp.BluePrGraph;
import java.util.*;

abstract class MSTAlgorithm extends BluePrGraph {

    BluePrGraph VEcreator = new BluePrGraph();
    
    Graph graph;
    
    ArrayList<Edge> MSTresultList = new ArrayList<>();

    public MSTAlgorithm(Graph graph) {
        this.graph = graph;
    }
    
    public abstract void DisplayResultingMST(ArrayList<Edge> MSTresultList);
}