package training.algorithms;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GraphNode {

    private final String name;

    public GraphNode(String name) {
        this.name = name;
    }

    private final Set<GraphNode> adjacentNodes = new HashSet<>();

    public Set<String> getPaths(GraphNode targetNode) {
        return getPaths(targetNode, new ArrayList<>());
    }

    private Set<String> getPaths(GraphNode targetNode, List<GraphNode> visitedNodes) {
        List<GraphNode> adjacentNodesExceptVisited = getAdjacentNodes().stream().filter(
            graphNode -> !visitedNodes.contains(graphNode)
        ).collect(Collectors.toList());

        if (this == targetNode) {
            return Collections.singleton(
                getPath(Arrays.asList(this, this))
            );
        } else if (adjacentNodesExceptVisited.isEmpty()) {
            return Collections.emptySet();
        } else {
            Set<GraphNode> otherAdjacentNodes;
            Set<String> paths = new HashSet<>();

            if (adjacentNodesExceptVisited.contains(targetNode)) {
                otherAdjacentNodes = adjacentNodesExceptVisited.stream().filter(
                    graphNode -> !targetNode.equals(graphNode)
                ).collect(Collectors.toSet());
                paths.add(getPath(visitedNodes, this, targetNode));
            } else {
                otherAdjacentNodes = new HashSet<>(adjacentNodesExceptVisited);
            }

            visitedNodes.add(this);

            for (GraphNode otherAdjacentNode : otherAdjacentNodes) {
                paths.addAll(
                    otherAdjacentNode.getPaths(targetNode, new ArrayList<>(visitedNodes))
                );
            }

            return paths;
        }
    }

    private String getPath(List<GraphNode> visitedNodes, GraphNode... otherVisitedNodes) {
        StringBuilder path = new StringBuilder();

        for (int i = 0; i < visitedNodes.size(); i++) {
            path.append(visitedNodes.get(i).name);
            if (i < visitedNodes.size() - 1) path.append("-");
        }

        for (int i = 0; i < otherVisitedNodes.length; i++) {
            if (i == 0 && path.length() > 0) path.append("-");
            path.append(otherVisitedNodes[i].name);
            if (i < otherVisitedNodes.length - 1) path.append("-");
        }

        return path.toString();
    }

    public void addAdjacentNode(GraphNode node) {
        adjacentNodes.add(node);
    }

    public void addAdjacentNodes(GraphNode... nodes) {
        adjacentNodes.addAll(
            Stream.of(nodes).collect(Collectors.toList())
        );
    }

    public Set<GraphNode> getAdjacentNodes() {
        return adjacentNodes;
    }
}