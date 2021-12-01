package training.algorithms;

import java.util.*;

public class Graph<T> {
    public enum Direction {
        BI, UNI
    };

    Direction direction;
    Map<T, Set<T>> adjacencyMap;

    public Graph() {
        direction = Direction.BI;
        adjacencyMap = new LinkedHashMap<>();
    }

    public Graph(Direction direction) {
        this.direction = direction;
        adjacencyMap = new LinkedHashMap<>();
    }

    public void connect(T x, T y) {
        doConnect(x, y);
        if (direction == Direction.BI) {
            doConnect(y, x);
        }
    }

    public void addNode(T x) {
        adjacencyMap.put(x, null);
    }

    public boolean isConnected(T x, T y) {
        return adjacencyMap.get(x) != null && adjacencyMap.get(x).contains(y);
    }

    private void doConnect(T x, T y) {
        Set<T> adjacencyListX = adjacencyMap.computeIfAbsent(
            x, k -> new LinkedHashSet<>()
        );

        adjacencyListX.add(y);
    }

    public boolean contains(T x) {
        return adjacencyMap.get(x) != null;
    }

    public List<String> shortestPaths(T x, T y) {
        List<String> path = new ArrayList<>();
        T currentNode = x;

        Set<T> visitedNodes = new HashSet<>();
        visitedNodes.add(currentNode);
        path.add(currentNode.toString());

        while (!y.equals(currentNode)) {
            currentNode = adjacencyMap.get(currentNode).stream().filter(
                node -> !visitedNodes.contains(node)
            ).findFirst().orElse(null);

            if (currentNode != null) {
                path.add(currentNode.toString());
                visitedNodes.add(currentNode);
            };
        }

        if (path.size() == 1) {
            return Collections.singletonList(
                path.get(0) + " -> " + path.get(0)
            );
        } else {
            return Collections.singletonList(
                String.join(" -> ", path)
            );
        }
    }
}
