package training.algorithms;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.*;

public class Node {
    String value;
    Set<Node> neighbors;
    boolean visited;

    Node(String value) {
        this.value = value;
        this.neighbors = new HashSet<>();
    }

    void connect(Node... nodes) {
        Arrays.stream(nodes).forEach(
            node -> {
                neighbors.add(node);
                node.neighbors.add(this);
            }
        );
    }

    boolean contains(String value) {
        if (Objects.equals(this.value, value)) {
            return true;
        }

        this.visited = true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(this);

        Node currentNode;

        while (!queue.isEmpty()) {
            currentNode = queue.remove();

            for (Node neighbor : currentNode.neighbors) {
                if (!neighbor.visited) {
                    if (Objects.equals(neighbor.value, value)) {
                        return true;
                    }
                    neighbor.visited = true;
                    queue.addAll(neighbor.neighbors);
                }
            }
        }

        return false;
    }

    @Override
    public int hashCode() {
        return value != null ? value.hashCode() : 0;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) return true;

        Node thatNode;

        try {
            thatNode = (Node) that;
        } catch(ClassCastException ex) {
            return false;
        }

        return Objects.equals(this.value, thatNode.value);
    }
}
