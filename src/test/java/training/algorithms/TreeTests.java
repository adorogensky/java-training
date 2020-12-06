package training.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

    @Test
    public void testPrintLayers2() {
        Tree2.Node node1 = new Tree2.Node("1");
        Tree2.Node node2 = new Tree2.Node("2");
        Tree2.Node node3 = new Tree2.Node("3");
        Tree2.Node node4 = new Tree2.Node("4");
        Tree2.Node node5 = new Tree2.Node("5");
        Tree2.Node node6 = new Tree2.Node("6");
        Tree2.Node node7 = new Tree2.Node("7");

        node1.leaves = Arrays.asList(node2, node3);
        node2.leaves = Arrays.asList(node4, node5);
        node3.leaves = Arrays.asList(node6, node7);

        Tree2 tree2 = new Tree2(node1);
        //tree2.print(2, Tree2.PrintMode.MANY);

        tree2.print(2, Tree2.PrintMode.ONE_LAYER);
    }
}
