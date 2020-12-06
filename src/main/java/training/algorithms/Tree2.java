package training.algorithms;

import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

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

    public void print() {
        System.out.println(root.value);

        Deque<Node> currentLayerQ = new LinkedList<>(root.leaves);
        Deque<Node> nextLayerQ = new LinkedList<>();

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
        }

    }
}
