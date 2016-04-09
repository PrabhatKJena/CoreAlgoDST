package edu.pk.dst.graph;

import java.awt.event.ComponentAdapter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kruskal’s Minimum Spanning Tree
 */
public class KruskalMST {
    public static void main(String[] args) {
        Graph graph = new Graph();
        // Adding edges (src, dest, weight)
        graph.addEdge(0, 1, 10);
        graph.addEdge(0, 2, 6);
        graph.addEdge(0, 3, 5);
        graph.addEdge(1, 3, 15);
        graph.addEdge(2, 3, 4);

        List<Edge> edgeList = findMST(graph);
        printTree(edgeList);
    }

    /**
     * Printing all the edges those part of MST
     *
     * @param edgeList
     */
    private static void printTree(List<Edge> edgeList) {
        for (Edge e : edgeList) {
            System.out.println(e.getSrc().getLabel() + "----" + e.getWeight() + "--->" + e.getDest().getLabel());
        }
    }

    private static List<Edge> findMST(Graph<? extends Comparable> graph) {
        // Resultant Edges
        List<Edge> mstEdges = new ArrayList<>();

        // Making each vertex as Disjoint set
        for (Vertex<? extends Comparable> v : graph.getVertices()) {

        }
        graph.getVertices().forEach(v -> {
            v.setParent(v);
            v.setRank(0);
        });

        // Sorting Edges based on weight
        List<Edge> edges = graph.getEdges();
        Collections.sort(edges);

        int mstEdgeCount = 1;
        // MST will contain only V-1 edges
        while (mstEdgeCount <= graph.getVCount() - 1) {
            // Take the edge with minimum weight, as the edge list is already sorted
            // in ascending order of their weight
            Edge edge = edges.remove(0);

            // If not cyclic then add the edge to result
            if (!isCyclic(graph, edge)) {
                mstEdges.add(edge);
                GraphUtil.union(graph, edge.getSrc().getLabel(), edge.getDest().getLabel());
                mstEdgeCount++;
            }
        }
        return mstEdges;
    }

    private static boolean isCyclic(Graph<? extends Comparable> graph, Edge edge) {
        Vertex<? extends Comparable> src = GraphUtil.findSet(graph, edge.getSrc().getLabel());
        Vertex<? extends Comparable> dst = GraphUtil.findSet(graph, edge.getDest().getLabel());
        return src.getLabel() == dst.getLabel();
    }

}
