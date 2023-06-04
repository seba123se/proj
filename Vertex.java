package GraphFramework;

import java.util.LinkedList;

public class Vertex {

    private String label;
    
    int labelNumber;
    
    boolean isVisited;
    
    LinkedList<Edge> adjList;
    
    // No constructor to ensure no Vertex object has been created

    public void displayInfo() {
        System.out.println(label);
    }
    
   //-------------------------- Getters and Setters --------------------------//
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
    
    public void setLabel() {
    }

    public int getLabelNumber() {
        return labelNumber;
    }

    public void setLabelNumber(int labelNumber) {
        this.labelNumber = labelNumber;
    }

    public boolean isIsVisited() {
        return isVisited;
    }

    public void setIsVisited(boolean isVisited) {
        this.isVisited = isVisited;
    }

    public LinkedList<Edge> getAdjList() {
        return adjList;
    }

    public void setAdjList(LinkedList<Edge> adjList) {
        this.adjList = adjList;
    }

    public void addAdjList(Edge addition) {
        this.adjList.add(addition);
    }
}
