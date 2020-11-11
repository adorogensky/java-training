package training.algorithms;

import org.junit.jupiter.api.Test;

public class GraphTests {

    @Test
    public void testPrintLayers() {
        Graph.Node nodeA = new Graph.Node("A");
        Graph.Node nodeB = new Graph.Node("B");
        Graph.Node nodeC = new Graph.Node("C");
        Graph.Node nodeD = new Graph.Node("D");
        Graph.Node nodeE = new Graph.Node("E");
        Graph.Node nodeF = new Graph.Node("F");

        nodeA.children.add(nodeB);
        nodeA.children.add(nodeC);

        nodeB.children.add(nodeD);
        nodeB.children.add(nodeE);

        nodeC.children.add(nodeF);

        Graph graph = new Graph(nodeA);
        // A
        // B C
        // D E F
        graph.printLayers();

        // D E F
        graph.printLayer(2);
    }
}
