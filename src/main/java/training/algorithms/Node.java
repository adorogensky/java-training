package training.algorithms;

import java.util.*;
import java.util.stream.Collectors;

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
        return bfs(this, value);
    }

    private boolean bfs(Node node, String value) {
        if (Objects.equals(node.value, value)) {
            return true;
        }

        node.visited = true;
        Queue<Node> queue = new LinkedList<>();
        queue.add(node);

        Node currentNode;

        while (!queue.isEmpty()) {
            currentNode = queue.remove();

            for (Node neighbor : currentNode.neighbors) {
                if (!neighbor.visited) {
                    if (Objects.equals(neighbor.value, value)) {
                        return true;
                    }
                    neighbor.visited = true;
                    queue.addAll(
                        neighbor.neighbors.stream().filter(
                            n -> !n.visited
                        ).collect(
                            Collectors.toSet()
                        )
                    );
                }
            }
        }

        return false;
    }

    private boolean dfs(Node node, String value) {
        if (Objects.equals(node.value, value)) {
            return true;
        }

        node.visited = true;

        for (Node neighbor : node.neighbors) {
            if (!neighbor.visited) {
                return dfs(neighbor, value);
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
