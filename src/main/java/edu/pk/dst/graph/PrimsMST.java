package edu.pk.dst.graph;
import java.util.*;
/**
 * Prim’s Minimum Spanning Tree (MST)
 */
public class PrimsMST {
    public static void main(String[] args) {
      /*  Graph<Character> graph = new Graph<Character>();
        graph.addEdge('A', 'B', 2);
        graph.addEdge('A', 'C', 4);
        graph.addEdge('B', 'C', 1);
        graph.addEdge('B', 'D', 3);
        graph.addEdge('C', 'D', 6);*/

        Graph<Integer> graph = new Graph<Integer>();
        graph.addEdge(0, 1, 4);
        graph.addEdge(0, 7, 8);
        graph.addEdge(1, 2, 8);
        graph.addEdge(1, 7, 11);
        graph.addEdge(2, 3, 7);
        graph.addEdge(2, 8, 2);
        graph.addEdge(2, 5, 4);
        graph.addEdge(3, 4, 9);
        graph.addEdge(3, 5, 14);
        graph.addEdge(4, 5, 10);
        graph.addEdge(5, 6, 2);
        graph.addEdge(6, 7, 1);
        graph.addEdge(6, 8, 6);
        graph.addEdge(7, 8, 7);

        graph.printAdjacencyList();
        Set<Vertex> mstVertexSet = findMSTByPrimsAlgo(graph, graph.getVertexByLabel(0));
        GraphUtil.printVerticesWithWeight(mstVertexSet);
    }

    /**
     * Finds MST by using Prim's Algorithm
     * @param graph
     * @param source vertex
     * @return set of vertices makes MST
     */
    public static Set<Vertex> findMSTByPrimsAlgo(Graph<? extends Comparable> graph, Vertex v0) {
        Set<Vertex> mstVertices = new HashSet<>();
        // Setting initial rank and parent as INFINITE and NULL
        graph.getVertices().forEach(v -> {
            v.setParent(null);
            v.setRank(Integer.MAX_VALUE);
        });

        // Start from v0
        v0.setRank(0);

        Set<Vertex> vSet = new HashSet<>(graph.getVertices());
        while (!vSet.isEmpty()) {
            Vertex v = GraphUtil.extractMin(vSet);
            if (v != null) {
                // For all adjacent vertices update rank and parent
                List<AdjVertex> adjacents = v.getAdjacents();
                for (AdjVertex av : adjacents) {
                    if (vSet.contains(av.getVertex()) && av.getWeight() < av.getVertex().getRank()) {
                        av.getVertex().setParent(v);
                        av.getVertex().setRank(av.getWeight());
                    }
                }
            }
            mstVertices.add(v);
        }
        return mstVertices;
    }

}
