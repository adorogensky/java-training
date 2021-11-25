package training.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTests {

    @Test
    public void test1() {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        nodeA.connect(nodeB, nodeC, nodeD);
        nodeB.connect(nodeA, nodeC, nodeD);
        nodeC.connect(nodeA, nodeB, nodeD);
        nodeD.connect(nodeA, nodeB, nodeC);

        assertTrue(nodeA.contains("C"));
    }

    @Test
    public void test2() {
        Node nodeA = new Node("A");
        Node nodeB = new Node("B");
        Node nodeC = new Node("C");
        Node nodeD = new Node("D");
        nodeA.connect(nodeB, nodeC, nodeD);
        nodeB.connect(nodeA, nodeC, nodeD);
        nodeC.connect(nodeA, nodeB, nodeD);
        nodeD.connect(nodeA, nodeB, nodeC);

        assertFalse(nodeA.contains("E"));
    }
}
