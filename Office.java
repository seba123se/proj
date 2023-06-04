package PhoneNetworkApp;

import GraphFramework.Vertex;

/**
 * Represents an office, equivalent of a vertex with the option to give the
 * office a name.
 */
public class Office extends Vertex {

    private static int id = 1;

    public Office() {
        // Default constructor
    }

    @Override
    public void setLabel() {
        setLabel("O" + id++);
    }

    @Override
    public void displayInfo() {
        // Superclass already prints all relevant information
        super.displayInfo();
    }
}
