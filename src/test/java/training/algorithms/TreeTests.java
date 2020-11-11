package training.algorithms;

import org.junit.jupiter.api.Test;

public class TreeTests {

    @Test
    public void testPrintLayers() {
        Tree.Node nodeA = new Tree.Node("A");
        Tree.Node nodeB = new Tree.Node("B");
        Tree.Node nodeC = new Tree.Node("C");
        Tree.Node nodeD = new Tree.Node("D");
        Tree.Node nodeE = new Tree.Node("E");
        Tree.Node nodeF = new Tree.Node("F");

        nodeA.children.add(nodeB);
        nodeA.children.add(nodeC);

        nodeB.children.add(nodeD);
        nodeB.children.add(nodeE);

        nodeC.children.add(nodeF);

        Tree tree = new Tree(nodeA);
        // A
        // B C
        // D E F
        tree.printLayers();

        // D E F
        tree.printLayer(2);
    }
}
