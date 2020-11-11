package training.algorithms;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Graph {

    static class Node {
        private final String value;
        List<Node> children = new ArrayList<>();

        Node(String value) {
            this.value = value;
        }
    }

    private final Node node;

    public Graph(Node node) {
        this.node = node;
    }

    public void printLayers() {
        Queue<Node> layer = new LinkedList<>();
        layer.add(node);
        printLayers(layer, -1);
    }

    public void printLayer(int layerNo) {
        Queue<Node> layer = new LinkedList<>();
        layer.add(node);
        printLayers(layer, layerNo);
    }

    private void printLayers(Queue<Node> layer, int layerNo) {
       Queue<Node> nextLayer = new LinkedList<>();
       int currentLayerNo = 0;
       while (!layer.isEmpty()) {
           Node node = layer.remove();
           nextLayer.addAll(node.children);

           if (layerNo < 0 || layerNo == currentLayerNo) {
               System.out.print(node.value + " ");
           }

           if (layer.isEmpty()) {
               if (layerNo < 0 || layerNo == currentLayerNo) {
                   System.out.println();
               }

               layer.addAll(nextLayer);
               currentLayerNo++;
               nextLayer.clear();
           }
       }
    }
}
