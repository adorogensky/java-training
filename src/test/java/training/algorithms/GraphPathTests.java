package training.algorithms;

import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Stream;

import static java.util.Collections.singleton;
import static java.util.stream.Collectors.toSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GraphPathTests {

    @Test
    public void verifyOneNodeGraphOnlyPath_A() {
        GraphNode node = new GraphNode("A");
        assertEquals(singleton("A-A"), node.getPaths(node));
    }

    @Test
    public void verifyOneNodeGraphOnlyPath_B() {
        GraphNode node = new GraphNode("B");
        assertEquals(singleton("B-B"), node.getPaths(node));
    }

    @Test
    public void verifyOneNodeGraphOnlyPath_C() {
        GraphNode node = new GraphNode("C");
        assertEquals(singleton("C-C"), node.getPaths(node));
    }

    @Test
    public void verifyTwoNodeGraph_PathFrom_nodeA_to_nodeA_and_nodeB_to_nodeB() {
        GraphNode nodeA = new GraphNode("A");
        GraphNode nodeB = new GraphNode("B");
        nodeA.addAdjacentNode(nodeB);
        nodeB.addAdjacentNode(nodeA);

        assertEquals(singleton("A-A"), nodeA.getPaths(nodeA));
        assertEquals(singleton("B-B"), nodeB.getPaths(nodeB));
    }

    @Test
    public void verifyTwoNodeGraph_PathFrom_nodeA_to_nodeB_and_nodeB_to_nodeA() {
        GraphNode nodeA = new GraphNode("A");
        GraphNode nodeB = new GraphNode("B");
        nodeA.addAdjacentNode(nodeB);
        nodeB.addAdjacentNode(nodeA);

        assertEquals(singleton("A-B"), nodeA.getPaths(nodeB));
        assertEquals(singleton("B-A"), nodeB.getPaths(nodeA));
    }

    @Test
    public void verifyThreeNodeGraphLine_PathFrom_nodeA_to_nodeB_and_nodeB_to_nodeC() {
        GraphNode nodeA = new GraphNode("A");
        GraphNode nodeB = new GraphNode("B");
        GraphNode nodeC = new GraphNode("C");

        nodeA.addAdjacentNode(nodeB);
        nodeB.addAdjacentNode(nodeA);
        nodeB.addAdjacentNode(nodeC);
        nodeC.addAdjacentNode(nodeB);

        assertEquals(singleton("A-B"), nodeA.getPaths(nodeB));
        assertEquals(singleton("B-C"), nodeB.getPaths(nodeC));
    }

    @Test
    public void verifyThreeNodeGraphLine_PathFrom_nodeA_to_nodeC() {
        GraphNode nodeA = new GraphNode("A");
        GraphNode nodeB = new GraphNode("B");
        GraphNode nodeC = new GraphNode("C");

        nodeA.addAdjacentNode(nodeB);
        nodeB.addAdjacentNode(nodeA);
        nodeB.addAdjacentNode(nodeC);
        nodeC.addAdjacentNode(nodeB);

        assertEquals(singleton("A-B-C"), nodeA.getPaths(nodeC));
    }

    @Test
    public void verifyThreeNodeGraphTriangle_PathFrom_nodeA_to_nodeC() {
        GraphNode nodeA = new GraphNode("A");
        GraphNode nodeB = new GraphNode("B");
        GraphNode nodeC = new GraphNode("C");

        nodeA.addAdjacentNodes(nodeB, nodeC);
        nodeB.addAdjacentNodes(nodeA, nodeC);
        nodeC.addAdjacentNodes(nodeA, nodeB);

        assertEquals(asSet("A-C", "A-B-C"), nodeA.getPaths(nodeC));
    }

    @Test
    public void verifyFourNodeGraphLinedTriangle_PathFrom_nodeA_to_nodeD() {
        GraphNode nodeA = new GraphNode("A");
        GraphNode nodeB = new GraphNode("B");
        GraphNode nodeC = new GraphNode("C");
        GraphNode nodeD = new GraphNode("D");

        nodeA.addAdjacentNode(nodeB);
        nodeB.addAdjacentNode(nodeC);
        nodeB.addAdjacentNode(nodeD);
        nodeC.addAdjacentNode(nodeB);
        nodeC.addAdjacentNode(nodeD);
        nodeD.addAdjacentNode(nodeC);
        nodeD.addAdjacentNode(nodeB);

        assertEquals(asSet("A-B-D", "A-B-C-D"), nodeA.getPaths(nodeD));
    }

    @Test
    public void verifyFiveNodeGraphLinedDoubleTriangle_PathFrom_nodeA_to_nodeD() {
        GraphNode nodeA = new GraphNode("A");
        GraphNode nodeB = new GraphNode("B");
        GraphNode nodeC = new GraphNode("C");
        GraphNode nodeD = new GraphNode("D");
        GraphNode nodeE = new GraphNode("E");

        nodeA.addAdjacentNode(nodeB);
        nodeB.addAdjacentNodes(nodeC, nodeE, nodeD);
        nodeC.addAdjacentNodes(nodeB, nodeD);
        nodeD.addAdjacentNodes(nodeC, nodeE, nodeB);
        nodeE.addAdjacentNodes(nodeB, nodeD);

        assertEquals(asSet("A-B-C-D", "A-B-D", "A-B-E-D"), nodeA.getPaths(nodeD));
    }

    @Test
    public void verify4NodeGraphSquareWithOneDiagonal_PathFrom_nodeA_to_nodeD() {
        GraphNode nodeA = new GraphNode("A");
        GraphNode nodeB = new GraphNode("B");
        GraphNode nodeC = new GraphNode("C");
        GraphNode nodeD = new GraphNode("D");

        nodeA.addAdjacentNodes(nodeB, nodeC, nodeD);
        nodeB.addAdjacentNodes(nodeA, nodeC);
        nodeC.addAdjacentNodes(nodeB, nodeA, nodeD);
        nodeD.addAdjacentNodes(nodeA, nodeC);

        assertEquals(asSet("A-D", "A-C-D", "A-B-C-D"), nodeA.getPaths(nodeD));
    }

    @Test
    public void verify5NodeGraphSquareWithTwoDiagonales_PathFrom_nodeA_to_nodeD() {
        GraphNode nodeA = new GraphNode("A");
        GraphNode nodeB = new GraphNode("B");
        GraphNode nodeC = new GraphNode("C");
        GraphNode nodeD = new GraphNode("D");
        GraphNode nodeE = new GraphNode("E");

        nodeA.addAdjacentNodes(nodeB, nodeD, nodeE);
        nodeB.addAdjacentNodes(nodeA, nodeC, nodeE);
        nodeC.addAdjacentNodes(nodeB, nodeD, nodeE);
        nodeD.addAdjacentNodes(nodeA, nodeC, nodeE);
        nodeE.addAdjacentNodes(nodeA, nodeB, nodeC, nodeD);

        assertEquals(asSet("A-D", "A-E-D", "A-E-C-D", "A-E-B-C-D", "A-B-C-E-D", "A-B-E-C-D", "A-B-E-D", "A-B-C-D"), nodeA.getPaths(nodeD));
    }

    @Test
    public void verify15NodeGraph_PathsFrom_nodeA_to_nodeB() {
        GraphNode graphNode1 = new GraphNode("1");
        GraphNode graphNode2 = new GraphNode("2");
        GraphNode graphNode3 = new GraphNode("3");
        GraphNode graphNode4 = new GraphNode("4");
        GraphNode graphNode5 = new GraphNode("5");
        GraphNode graphNode6 = new GraphNode("6");
        GraphNode graphNode7 = new GraphNode("7");
        GraphNode graphNode8 = new GraphNode("8");
        GraphNode graphNode9 = new GraphNode("9");
        GraphNode graphNode10 = new GraphNode("10");
        GraphNode graphNode11 = new GraphNode("11");
        GraphNode graphNode12 = new GraphNode("12");
        GraphNode graphNode13 = new GraphNode("13");
        GraphNode graphNodeA = new GraphNode("A");
        GraphNode graphNodeB = new GraphNode("B");


        graphNode1.addAdjacentNodes(graphNodeA);
        graphNode2.addAdjacentNodes(graphNodeA);
        graphNode3.addAdjacentNodes(graphNodeA, graphNode9);
        graphNode4.addAdjacentNodes(graphNodeA);
        graphNode5.addAdjacentNodes(graphNodeA, graphNode6, graphNode9);
        graphNode6.addAdjacentNodes(graphNode5, graphNode7, graphNode8, graphNode9);
        graphNode7.addAdjacentNodes(graphNode6);
        graphNode8.addAdjacentNodes(graphNode6);
        graphNode9.addAdjacentNodes(graphNodeA, graphNodeB, graphNode3, graphNode5, graphNode6, graphNode10, graphNode11, graphNode12);
        graphNode10.addAdjacentNodes(graphNode9);
        graphNode11.addAdjacentNodes(graphNode9);
        graphNode12.addAdjacentNodes(graphNode9, graphNode13);
        graphNode13.addAdjacentNodes(graphNodeB, graphNode12);
        graphNodeA.addAdjacentNodes(graphNode1, graphNode2, graphNode3, graphNode4, graphNode5, graphNode9);
        graphNodeB.addAdjacentNodes(graphNode9, graphNode13);

        assertEquals(asSet("A-5-9-B", "A-5-6-9-B", "A-9-B", "A-9-12-13-B", "A-3-9-B", "A-3-9-12-13-B", "A-5-9-12-13-B", "A-5-6-9-12-13-B"), graphNodeA.getPaths(graphNodeB));

    }

    private Set<String> asSet(String... values) {
        return Stream.of(values).collect(toSet());
    }
}