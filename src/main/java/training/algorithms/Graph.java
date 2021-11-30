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

    public List<String> shortestPath(T x, T y) {
        return new ArrayList<>();
    }
}
