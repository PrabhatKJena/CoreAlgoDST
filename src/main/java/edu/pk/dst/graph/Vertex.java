package edu.pk.dst.graph;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Vertex<E extends Comparable> {
    private Vertex parent;
    private E label;
    private int rank;
    private State state;
    private List<AdjVertex> adjacents;

    public Vertex(E label) {
        this.label = label;
        this.rank = 0;
        adjacents = new ArrayList<>();
    }

    public Vertex<E> getParent() {
        return parent;
    }

    public void setParent(Vertex<E> parent) {
        this.parent = parent;
    }

    public E getLabel() {
        return label;
    }

    public void setLabel(E label) {
        this.label = label;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "parent=" + (parent == null ? "NIL" : parent.getLabel()) +
                ", label=" + label +
                ", rank=" + rank +
                ", state=" + state +
                '}';
    }

    public List<AdjVertex> getAdjacents() {
        return adjacents;
    }

    public boolean addAdjacent(AdjVertex v) {
        if (!adjacents.contains(v.getVertex())) {
            adjacents.add(v);
            return true;
        }
        return false;
    }

    public AdjVertex getAdjVertexByLabel(final E label) {
        return adjacents.stream().filter(adjVertex -> adjVertex.getVertex().getLabel() == label).findFirst().orElse(null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex)) return false;
        Vertex vertex = (Vertex) o;
        return label == vertex.label;
    }

    @Override
    public int hashCode() {
        return Objects.hash(label);
    }

    static enum State {
        INITIAL(0),
        IN_PROGRESS(1),
        COMPLETED(2);
        int value;

        State(int i) {
            this.value = i;
        }
    }

}

