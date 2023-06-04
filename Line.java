package PhoneNetworkApp;

import GraphFramework.Edge;

/**
 * Represents a line between two offices, equivalent of an edge with modified displayInfo() method.
 */
public class Line extends Edge {

    private Integer iLength;

    public Line() {
        // Default constructor
    }

    @Override
    public void displayInfo() {
        setiLength(weight);
        
        super.displayInfo();
        System.out.println(" | line length: " + iLength);
    }

   //-------------------------- Getters and Setters --------------------------//
    public Integer getiLength() {
        return iLength;
    }

    public void setiLength(Integer weight) {
        this.iLength = weight * 5;
    }
}
