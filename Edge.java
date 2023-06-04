package GraphFramework;

public class Edge {

    protected Integer weight;
    
    protected Vertex source;
    protected Vertex target;
    protected Vertex parent;

    // No constructor to ensure no Edge object has been created

    public void displayInfo() {
        System.out.print("    Office No. " + source.getLabel() + " - "
                + "Office No. " + target.getLabel());
    }

   //-------------------------- Getters and Setters --------------------------//
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Vertex getSource() {
        return source;
    }

    public void setSource(Vertex source) {
        this.source = source;
    }

    public Vertex getTarget() {
        return target;
    }

    public void setTarget(Vertex target) {
        this.target = target;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

}
