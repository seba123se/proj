package GraphFramework;

import java.util.*;

public class MHPrimAlg extends MSTAlgorithm {
    
    int cost;

    public MHPrimAlg(Graph graph) {
        super(graph);
    }

    @Override
    public void DisplayResultingMST(ArrayList<Edge> MSTresultList) {
        MSTresultList.sort((o1, o2) -> o1.weight.compareTo(o2.weight));
        
        if (MSTresultList.size() <= 10) {
            for (int i = 0; i < MSTresultList.size(); i++) {
                MSTresultList.get(i).displayInfo();
            }
        }

        System.out.println("-----------------------------------------------------\n"
                         + "The cost of designed phone network using min heap Prim's algorithm: " + cost);
    }

    public void MHPrim() {

        // Start time
        long startTime = System.currentTimeMillis();

        int vertNum = graph.verticesNo;

        MSTresultList = new ArrayList<>();

        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.weight));

        boolean[] inHeap = new boolean[vertNum];

        Edge[] parent = new Edge[vertNum];

        // Store keys to know whether a min heap update is required
        int[] key = new int[vertNum];

        // Create heapNode for all vertices
        HeapNode[] heapNode = new HeapNode[vertNum];

        MinHeap minheap = new MinHeap(vertNum);

        for (int i = 0; i < vertNum; i++) {
            heapNode[i] = new HeapNode();
            heapNode[i].vertex = i;
            heapNode[i].key = Integer.MAX_VALUE;
            parent[i] = new Edge();
            parent[i].parent = new Vertex();
            parent[i].parent.labelNumber = -1;
            inHeap[i] = true;
            key[i] = Integer.MAX_VALUE;
        }

        // Decrese key of first index
        heapNode[0].key = 0;

        for (int i = 0; i < vertNum; i++) {
            minheap.insert(heapNode[i]);
        }

        while (!minheap.isEmpty()) {

            HeapNode node = minheap.MIN();

            int Vertex = node.vertex;

            inHeap[Vertex] = false;

            for (int i = 0; i < graph.vertices[Vertex].adjList.size(); i++) {
                Edge edge = graph.vertices[Vertex].adjList.get(i);
                pq.add(edge);

                if (inHeap[edge.target.labelNumber]) {
                    int destination = edge.target.labelNumber;
                    int newKey = edge.weight;

                    if (key[destination] > newKey) {
                        decreaseKey(minheap, newKey, destination);

                        parent[destination].parent.labelNumber = Vertex;
                        parent[destination].weight = newKey;
                        key[destination] = newKey;
                        
                        MSTresultList.add(edge);
                    }
                }
            }
        }

        for (int i = 1; i < graph.verticesNo; i++) {
            cost += parent[i].weight;
        }
        
        DisplayResultingMST(MSTresultList);
        
        // End time
        long endTime = System.currentTimeMillis();
        
        // Print runtime
        System.out.println("Total runtime of Min Heap Algorithm: " + (endTime - startTime) + " ms.");
    }

    public void decreaseKey(MinHeap minheap, int key, int vertex) {

        int n = minheap.decreaseKay[vertex];

        HeapNode node = minheap.heapnode[n];
        node.key = key;
        minheap.bubbleUp(n);
    }

    static class HeapNode {
        
        int vertex;
        int key;
        
    }

    static class MinHeap {

        int capacity;
        int Csize;
        HeapNode[] heapnode;
        int[] decreaseKay; // Used to decrease the key

        public MinHeap(int capacity) {
            this.capacity = capacity;
            heapnode = new HeapNode[capacity + 1];
            decreaseKay = new int[capacity];
            heapnode[0] = new HeapNode();
            heapnode[0].key = Integer.MIN_VALUE;
            heapnode[0].vertex = -1;
            Csize = 0;
        }

        public void insert(HeapNode heap) {
            Csize++;
            int i = Csize;
            heapnode[i] = heap;
            decreaseKay[heap.vertex] = i;
            bubbleUp(i);
        }

        public void bubbleUp(int i) {
            int parentI = i / 2;
            int currentI = i;
            while (currentI > 0 && heapnode[parentI].key > heapnode[currentI].key) {
                HeapNode CNode = heapnode[currentI];
                HeapNode PNode = heapnode[parentI];

                // Swap positions
                decreaseKay[CNode.vertex] = parentI;
                decreaseKay[PNode.vertex] = currentI;
                swap(currentI, parentI);
                currentI = parentI;
                parentI = parentI / 2;
            }
        }

        public HeapNode MIN() {
            HeapNode Min_Node = heapnode[1];
            HeapNode Last_Node = heapnode[Csize];

            // Update indexes and move last node to the top
            decreaseKay[Last_Node.vertex] = 1;
            heapnode[1] = Last_Node;
            heapnode[Csize] = null;
            Down(1);
            Csize--;
            return Min_Node;
        }

        public void Down(int x) {
            int small = x;
            int Lift = 2 * x;
            int Right = 2 * x + 1;
            if (Lift < heapSize() && heapnode[small].key > heapnode[Lift].key) {
                small = Lift;
            }
            if (Right < heapSize() && heapnode[small].key > heapnode[Right].key) {
                small = Right;
            }
            if (small != x) {

                HeapNode Smallest_Node = heapnode[small];
                HeapNode Node = heapnode[x];

                // Swap the positions
                decreaseKay[Smallest_Node.vertex] = x;
                decreaseKay[Node.vertex] = small;
                swap(x, small);
                Down(small);
            }
        }

        public void swap(int x, int y) {
            HeapNode temp = heapnode[x];
            heapnode[x] = heapnode[y];
            heapnode[y] = temp;
        }

        public boolean isEmpty() {
            return Csize == 0;
        }

        public int heapSize() {
            return Csize;
        }
    }

}
