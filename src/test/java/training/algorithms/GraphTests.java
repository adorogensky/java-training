package training.algorithms;

import org.junit.jupiter.api.Test;

import java.util.List;

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

    @Test
    public void test3() {
        Graph<String> g = new Graph<>();
        g.connect("A", "B");
        g.connect("A", "D");
        g.connect("B", "C");
        g.connect("B", "D");
        g.connect("C", "D");
        g.connect("D", "E");
        g.connect("D", "F");

        assertTrue(g.contains("A"));
        assertTrue(g.contains("B"));
        assertTrue(g.contains("C"));
        assertTrue(g.isConnected("A", "B"));
        assertTrue(g.isConnected("B", "A"));
    }

    @Test
    public void test4() {
        Graph<String> g = new Graph<>(Graph.Direction.UNI);
        g.connect("A", "B");
        g.connect("A", "D");
        g.connect("B", "C");
        g.connect("B", "D");
        g.connect("C", "D");
        g.connect("D", "E");
        g.connect("D", "F");

        assertTrue(g.contains("A"));
        assertTrue(g.contains("B"));
        assertTrue(g.contains("C"));
        assertTrue(g.isConnected("A", "B"));
        assertFalse(g.isConnected("B", "A"));
    }

    @Test
    public void shortestPath_1_node_linear_graph() {
        Graph<String> g = new Graph<>();
        g.addNode("A");
        List<String> shortestPaths = g.shortestPaths("A", "A");
        assertTrue(shortestPaths.contains("A -> A"));
        assertEquals(1, shortestPaths.size());
    }

    @Test
    public void shortestPath_2_node_linear_graph() {
        Graph<String> g = new Graph<>();
        g.connect("A", "B");
        List<String> shortestPaths = g.shortestPaths("A", "B");
        assertTrue(shortestPaths.contains("A -> B"));
        assertEquals(1, shortestPaths.size());
    }

    @Test
    public void shortestPath_3_node_linear_graph() {
        Graph<String> g = new Graph<>();
        g.connect("A", "B");
        g.connect("B", "C");
        List<String> shortestPaths = g.shortestPaths("A", "B");
        assertTrue(shortestPaths.contains("A -> B"));
        assertEquals(1, shortestPaths.size());
    }

    @Test
    public void shortestPath_3_node_linear_graph_2() {
        Graph<String> g = new Graph<>();
        g.connect("A", "B");
        g.connect("B", "C");
        List<String> shortestPaths = g.shortestPaths("A", "C");
        assertTrue(shortestPaths.contains("A -> B -> C"));
        assertEquals(1, shortestPaths.size());
    }

}
