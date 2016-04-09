package edu.pk.dst.graph;
import java.util.*;
/**
 * Prim’s Minimum Spanning Tree (MST)
 */
public class PrimsMST {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<Integer>();
        /*graph.addEdge('A', 'B', 2);
        graph.addEdge('A', 'C', 4);
        graph.addEdge('B', 'C', 1);
        graph.addEdge('B', 'D', 3);
        graph.addEdge('C', 'D', 6);*/

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
        Set<Vertex> mstByPrimsAlgo = findMSTByPrimsAlgo(graph, graph.getVertexByLabel(0));
        printMST(graph,mstByPrimsAlgo);
    }

    // Printing MST edges with source---weight--->dest
    private static void printMST(Graph graph, Set<Vertex> vertices) {
        for (Vertex vertex : vertices) {
            Vertex parent = vertex.getParent();
            if (parent != null) {
                System.out.println(parent.getLabel() + "-----"+parent.getAdjVertexByLabel(vertex.getLabel()).getWeight()+"--->" + vertex.getLabel());
            }
        }
    }

    private static Set<Vertex> findMSTByPrimsAlgo(Graph<? extends Comparable> graph, Vertex v0) {
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
            Vertex v = extractMin(vSet);
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

    // Finding Vertex with minimum rank using stream API
    private static Vertex extractMin(Set<? extends Vertex> vertices) {
        Vertex min = vertices.stream().min((a, b) -> {
            return Integer.valueOf(a.getRank()).compareTo(b.getRank());
        }).orElse(null);
        if (min != null) {
            vertices.remove(min);
        }
        return min;
    }
}
