package edu.pk.dst.graph;

import edu.pk.dst.graph.adt.AdjVertex;
import edu.pk.dst.graph.adt.Edge;
import edu.pk.dst.graph.adt.Graph;
import edu.pk.dst.graph.adt.Vertex;
import edu.pk.dst.graph.util.GraphUtil;

import java.util.Optional;

/**
 * Detect Cycle in a an Undirected Graph.
 * A union-find algorithm is an algorithm that performs two useful operations on such a data structure:
 * Find: Determine which subset a particular element is in. This can be used for determining
 * if two elements are in the same subset.
 * Union: Join two subsets into a single subset.
 */
public class UndirectedGraphCycleDetection {
    public static void main(String[] args) {
        Graph<Integer> graph = new Graph<>();
        graph.addEdge(1, 0);
        graph.addEdge(1, 1); // self-loop
        graph.addEdge(1, 2);
//        graph.addEdge(2, 0); // cyclic
        graph.addEdge(3, 0);
        graph.addEdge(4, 3);

        System.out.print("By Edge : ");
        if (isCyclicByEdge(graph)) {
            System.out.println("Cyclic");
        } else {
            System.out.println("Not Cyclic");
        }

        System.out.print("By DFS : ");
        if (isCyclicByDFS(graph)) {
            System.out.println("Cyclic");
        } else
            System.out.println("Not Cyclic");
    }

    /**
     * This method will work for self-loop also
     *
     */
    public static boolean isCyclicByDFS(Graph<Integer> graph) {
        // Setting State of all vertices to INITIAL
        graph.getVertices().forEach(v -> v.setState(Vertex.State.INITIAL));

        // Checking if there is any cycle present in DFS starting from a particular vertex
        Optional<Vertex<Integer>> first = graph.getVertices().stream().filter(v -> {
            if (v.getState() != Vertex.State.COMPLETED) {
                if (isCycleInDFS(v, null))
                    return true;
            }
            return false;
        }).findFirst();

        if (first.isPresent())
            return true;
        return false;
    }

    /**
     Checking in DFS, any adjacent vertex is already visited but not parent of the current vertex
     i.e. In DFS the vertices are traversed in depth wise(downward); if any visited adjacent vertex comes in DFS path and not
            the parent, then there is a cycle

     e.g 1----2---3
          \     /
           \   /     Here in DFS it moves like 1-->2, 2-->3, 3-->4(here 2 is visited and parent), 4-->1
            \ /      But during 4-->1 (3 is visited ad parent, but 1 is visited and not parent).
             4       So 4-->1 is creating the cycle
     */
    private static boolean isCycleInDFS(Vertex<Integer> v, Vertex<Integer> parent) {
        // Marking state as VISITED
        v.setState(Vertex.State.COMPLETED);

        // Find if any adjacent vertex is Not Visited and not Parent
        Optional<AdjVertex> first = v.getAdjacents().stream().filter(av -> {
            if (av.getState() != Vertex.State.COMPLETED) {
                if (isCycleInDFS(av.getVertex(), v))
                    return true;
            }
            // checking visited and not parent and not self loop
            else if (!av.getVertex().equals(parent) && !av.getVertex().equals(v))
                return true;
            return false;
        }).findFirst();

        if (first.isPresent())
            return true;
        return false;
    }
    /**
     * This method assumes that graph does n’t contain any self-loops.
     * T(E,V) = O(ELogV)
     */
    public static boolean isCyclicByEdge(Graph<Integer> graph) {
        // Making each vertex as Disjoint set
        graph.getVertices().forEach(v -> {
            v.setParent(v);
        });

        /*For each edge, find the set of the src and dest and check whether they are belongs to same same set
          If, same then it is cyclic
          else union them
        */
        Optional<Edge> edge = graph.getEdges().stream().filter(e -> {
            Vertex<? extends Comparable> srcSet = GraphUtil.findSet(graph, e.getSrc().getLabel());
            Vertex<? extends Comparable> destSet = GraphUtil.findSet(graph, e.getDest().getLabel());
            // if same subset then cyclic
            if (srcSet.equals(destSet))
                return true;
            GraphUtil.union(graph, srcSet.getLabel(), destSet.getLabel());
            return false;
        }).findFirst();

        if (edge.isPresent())
            return true;
        return false;
    }
}
