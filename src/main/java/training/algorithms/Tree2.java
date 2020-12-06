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

    public Tree2(Node root) {
        this.root = root;
    }

    // enumerate layers starting from 1
    public void print(int layerNo) {
        Deque<Node> currentLayerQ = new LinkedList<>(Collections.singletonList(root));
        Deque<Node> nextLayerQ = new LinkedList<>();
        int currentLayerNo = 1;

        while (!currentLayerQ.isEmpty()) {
            nextLayerQ.clear();

            for (Node node : currentLayerQ) {
                System.out.print(node.value + " ");

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
        print(-1);
    }
}
