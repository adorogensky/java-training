package training.algorithms;

import java.util.*;

public class Graph {

    static class Node {
        final String value;
        List<Node> children = new ArrayList<>();
        private boolean visited;

        Node(String value) {
            this.value = value;
        }
    }

    public enum SEARCH_MODE {
        BFS
    };

    private final Node node;

    public Graph(Node node) {
        this.node = node;
    }

    public Set<Node> getNodes(SEARCH_MODE mode) {
        node.visited = true;
        Queue<Node> queue = new LinkedList<>();
        Set<Node> returnNodes = new HashSet<>();
        queue.add(node);

        while (!queue.isEmpty()) {
            Node currentNode = queue.remove();
            returnNodes.add(currentNode);

            for (Node childNode : currentNode.children) {
                if (!childNode.visited) {
                    childNode.visited = true;
                    queue.add(childNode);
                }
            }
        }

        return returnNodes;
    }
}
