package edu.pk.dst.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Kruskal’s Minimum Spanning Tree
 */
public class KruskalMST {
    public static void main(String[] args) {
        Graph graph = new Graph(4, 5);
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
     * @param edgeList
     */
    private static void printTree(List<Edge> edgeList) {
        for (Edge e : edgeList) {
            System.out.println(e.getSrc() + "----" + e.getWeight() + "--->" + e.getDest());
        }
    }

    private static List<Edge> findMST(Graph graph) {
        // Resultant Edges
        List<Edge> mstEdges = new ArrayList<>();

        // Making each vertex as Disjoint set
        for (int v = 0; v < graph.getvCount(); v++) {
            graph.getVertices().add(new Vertex(v));
        }

        // Sorting Edges based on weight
        List<Edge> edges = graph.getEdges();
        Collections.sort(edges);

        int mstEdgeCount = 1;
        // MST will contain only V-1 edges
        while (mstEdgeCount <= graph.getvCount() - 1) {
            // Take the edge with minimum weight, as the edge list is already sorted
            // in ascending order of their weight
            Edge edge = edges.remove(0);

            // If not cyclic then add the edge to result
            if (!isCyclic(graph, edge)) {
                mstEdges.add(edge);
                GraphUtil.union(graph, edge.getSrc(), edge.getDest());
                mstEdgeCount++;
            }
        }
        return mstEdges;
    }

    private static boolean isCyclic(Graph graph, Edge edge) {
        Vertex src = GraphUtil.findSet(graph, edge.getSrc());
        Vertex dst = GraphUtil.findSet(graph, edge.getDest());
        return src.getLabel() == dst.getLabel();
    }

}
