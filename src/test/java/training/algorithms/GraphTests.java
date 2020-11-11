package training.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class GraphTests {

    @Test
    public void testGetNodes_BFS_Mode() {
        Graph.Node nodeA = new Graph.Node("A");
        Graph.Node nodeB = new Graph.Node("B");
        Graph.Node nodeC = new Graph.Node("C");
        Graph.Node nodeD = new Graph.Node("D");

        nodeA.children.add(nodeB);
        nodeB.children.add(nodeC);
        nodeC.children.add(nodeD);
        nodeD.children.add(nodeA);


        Graph graph = new Graph(nodeA);

        Set<Graph.Node> nodes = graph.getNodes(Graph.SEARCH_MODE.BFS);
        assertEquals(4, nodes.size());
        assertTrue(nodes.contains(nodeA));
        assertTrue(nodes.contains(nodeB));
        assertTrue(nodes.contains(nodeC));
        assertTrue(nodes.contains(nodeD));
    }
}
