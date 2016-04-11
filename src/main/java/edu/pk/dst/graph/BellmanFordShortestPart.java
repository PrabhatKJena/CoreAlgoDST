package edu.pk.dst.graph;

import java.util.Optional;
import java.util.Set;

/**
 * Bellman Ford's algorithm solves Single Source Shortest Path problem including -ve weight edges.
 * <p>
 * Bellman-Ford algorithm returns a boolean value indicating whether or not there is
 * a negative-weight cycle that is reachable from the source.
 * If there is such a cycle, the algorithm indicates that no solution exists.
 * If there is no such cycle, the algorithm produces the shortest paths and their weights.
 * <p>
 * Time complexity of Bellman-Ford is O(VE), which is more than Dijkstra.
 */
public class BellmanFordShortestPart {
    public static void main(String[] args) {
        Graph<Integer> graph = GraphUtil.createSampleGraph();
        boolean isSolutionPresent = findShortestPathByBellmanFord(graph, 0);
        if (isSolutionPresent) {
            GraphUtil.printVerticesWithWeight(graph.getVertices());
        } else
            System.out.println("No Solution Exists...");
    }

    /**
     * Finds Single Source Shortest path of a graph
     *
     * @param graph
     * @param source vertex
     * @return set of vertices makes Shortest Path
     */
    public static boolean findShortestPathByBellmanFord(Graph<? extends Comparable> graph, int source) {
        // Initializing all vertices and Single Source
        GraphUtil.initializeSingleSource(graph, graph.getVertexByLabel(0));

        /* For each edge update rank and parent of the target vertex - START */
        // Printing rank and parent of each iteration - HEAD
        System.out.println("Updated Rank after each relaxation iteration : ");
        graph.getVertices().forEach(v -> {
            System.out.printf("%8s", v.getLabel());
        });
        System.out.println();
        graph.getVertices().forEach(v -> {
            System.out.printf("%8s", "--------");
        });
        System.out.println();
        for (int i = 1; i <= graph.getVCount() - 1; i++) {
            graph.getEdges().forEach(edge -> {
                GraphUtil.relax(edge.getSrc(), edge.getDest(), edge.getWeight());
            });
            // Printing rank and parent of each iteration - EACH ROW
            graph.getVertices().forEach(v -> {
                System.out.printf("%8s", v.getRank() + "(" + (v.getParent() == null ? "X" : v.getParent().getLabel()) + ")");
            });
            System.out.println();
        }
        /* For each edge update rank and parent of the target vertex - END */

        // Find if any edge such that, after adding the weight with the rank of the src vertex s less than
        // the the rank of the target. i.e the weight is -ve since all the vertices are already relaxed |V|-1 times
        // and still the rank of target vertex is decreasing.
        Optional<Edge> edgeOptional = graph.getEdges().stream().filter(e -> {
            Vertex u = e.getSrc();
            Vertex v = e.getDest();
            if (u.getRank() + e.getWeight() < v.getRank())
                return true;
            else
                return false;
        }).findFirst();
        // If any such edge found, then there is a -ve edge loop. So solution does not exist
        if (edgeOptional.isPresent())
            return false;

        return true;
    }
}
