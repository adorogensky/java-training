package training.algorithms;

import java.util.*;

public class Tree2 {
    private Node root;

    static class Node {
        String value;
        Collection<Node> leaves;

        Node(String value) {
            this.value = value;
        }
    }

    static enum PrintMode {
        ONE_LAYER, MANY_LAYERS
    };

    public Tree2(Node root) {
        this.root = root;
    }

    /**
     * Layers enumeration starts with 1.
     * layerNo > 0 - print all layers up to layerNo
     * layerNo <= 0 - print all layers
     * @param layerNo
     */
    public void print(int layerNo, PrintMode printMode) {
        Deque<Node> currentLayerQ = new LinkedList<>(Collections.singletonList(root));
        Deque<Node> nextLayerQ = new LinkedList<>();
        int currentLayerNo = 1;

        while (!currentLayerQ.isEmpty()) {
            nextLayerQ.clear();

            for (Node node : currentLayerQ) {
                if (printMode == PrintMode.MANY_LAYERS || printMode == PrintMode.ONE_LAYER && currentLayerNo == layerNo) {
                    System.out.print(node.value + " ");
                }

                if (node.leaves != null)
                    nextLayerQ.addAll(node.leaves);
            }

            System.out.println();
            currentLayerQ.clear();
            currentLayerQ.addAll(nextLayerQ);

            if (layerNo > 0 && ++currentLayerNo > layerNo) break;
        }
    }

    public void print() {
        print(-1, PrintMode.MANY_LAYERS);
    }
}
