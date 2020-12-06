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

        @Override
        public String toString() {
            return value;
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
        Queue<Node> layer = new LinkedList<>(Collections.singletonList(root));
        int currentLayerNo = 1;

        while (!layer.isEmpty()) {
            Node node;
            int layerElementCount = 0;
            int layerSize = layer.size();

            do {
                node = layer.poll();
                if (node != null) {
                    if (printMode == PrintMode.MANY_LAYERS || printMode == PrintMode.ONE_LAYER && currentLayerNo == layerNo) {
                        System.out.print(node.value + " ");
                    }
                    if (++layerElementCount == layerSize)
                        System.out.println();
                    if (node.leaves != null) {
                        layer.addAll(node.leaves);
                        if (layerElementCount == layerSize) {
                            layerElementCount = 0;
                            layerSize = layer.size();
                        }
                    }
                }
            } while (node != null);

            if (layerNo > 0 && ++currentLayerNo > layerNo) break;
        }
    }

    public void print() {
        print(-1, PrintMode.MANY_LAYERS);
    }
}
