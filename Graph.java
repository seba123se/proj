package GraphFramework;

import java.util.*;

public abstract class Graph {

    int verticesNo;
    int edgeNo;

    boolean isDigraph;

    Vertex[] vertices;

    HashMap<Integer, String> names = new HashMap<>();
    HashMap<String, Integer> verticeNumber = new HashMap<>();

    public Graph() {
        // Default constructor
    }

    public Graph(int verticesNo, int edgeNo) {
        this.verticesNo = verticesNo;
        this.edgeNo = edgeNo;
        fillLists(verticesNo, edgeNo);
    }

    public void readGraphFromFile(List<String> reader) {
        // Declarations
        String[] content; // Split per line

        // Determine if graph is un/directed
        String line = reader.remove(0);
        line = line.substring(line.indexOf(" ") + 1);
        isDigraph = (Integer.parseInt(line) == 1);

        // Determine number of edges and vertices
        line = reader.remove(0);
        content = line.split(" ");
        verticesNo = Integer.parseInt(content[0]);
        edgeNo = Integer.parseInt(content[1]);

        // Initialize all lists and arrays
        fillLists(verticesNo, edgeNo);

        // Read all vertices, edges and their weights
        int srcVertex, dstVertex, weight;

        while (!reader.isEmpty()) {
            line = reader.remove(0);
            content = line.split(" ");

            // Storing
            srcVertex = verticeNumber.get(content[0]);
            dstVertex = verticeNumber.get(content[1]);
            weight = Integer.parseInt(content[2]);

            addEdge(vertices[srcVertex], vertices[dstVertex], weight);
        }
    }

    // Used to add an edge to a vertex's neighbouring vertices
    public void addEdge(Vertex source, Vertex target, int weight) {
        Edge edge = createEdge();
        edge.setSource(source);
        edge.setTarget(target);
        edge.setWeight(weight);

        source.adjList.addFirst(edge);

        // Connect target to edge in case of an undirecred graph
        if (!isDigraph) {
            edge = createEdge();
            edge.setSource(target);
            edge.setTarget(source);
            edge.setWeight(weight);

            target.adjList.addFirst(edge);
        }
    }

    // Makes randomized graph of n vertices and m edges
    public void make_graph() {
        Random random = new Random();
        
        // Check connectivity of all vertices
        for (int i = 0; i < verticesNo - 1; i++) {
            vertices[i].setLabel();
            int RandomNum = random.nextInt(40) + 1;
            addEdge(vertices[i], vertices[i + 1], RandomNum);
        }

        // Connect remaining edges
        int remaning = edgeNo - (verticesNo - 1);

        for (int i = 0; i < remaning; i++) {
            Vertex source = vertices[random.nextInt(verticesNo)];
            Vertex Destination = vertices[random.nextInt(verticesNo)];

            int weight = random.nextInt(40) + 1;
            
            // Avoid duplicate edges
            if (source == Destination || isConnected(source, Destination)) {
                i--;
            } else {
                addEdge(source, Destination, weight);
            }
        }
    }
    
    // Check and return result for connectivity of two vertices
    public boolean isConnected(Vertex Source, Vertex target) {
        for (Edge edge : Source.adjList) {
            if (edge.target == target) {
                return true;
            }
        }
        return false;
    }

    // Declare & initiate lists/arrays used for graphs
    public void fillLists(int verticesNo, int edgeNo) {
        // Initialization of the ARRAY ITSELF, not any index in it, hence the "new Vertex"
        this.vertices = new Vertex[verticesNo];

        // Vertex letter labels read from txt file must be converted to numbers
        // as that's what the algorithms we imported use
        int ascii = 65;
        for (int i = 0; i < verticesNo; i++) {
            names.put(i, Character.toString((char) ascii++));
        }

        for (int i = 0; i < verticesNo; i++) {
            verticeNumber.put(names.get(i), i);
        }

        for (int i = 0; i < vertices.length; i++) {
            vertices[i] = createVertex();
            vertices[i].setLabelNumber(i);
            vertices[i].setLabel(names.get(i));
            vertices[i].setAdjList(new LinkedList<>());
        }
    }

    public abstract Vertex createVertex();

    public abstract Edge createEdge();
}
