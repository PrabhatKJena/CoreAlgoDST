package edu.pk.dst.graph;

public class Vertex {
    private Vertex parent;
    private int label;
    private int rank;
    private boolean isVisited;

    public Vertex(int label) {
        this.parent = this;
        this.label = label;
        this.rank = 0;
    }

    public Vertex getParent() {
        return parent;
    }

    public void setParent(Vertex parent) {
        this.parent = parent;
    }

    public int getLabel() {
        return label;
    }

    public void setLabel(int label) {
        this.label = label;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isVisited() {
        return isVisited;
    }

    @Override
    public String toString() {
        return "Vertex{" +
                "parent=" + parent.getLabel() +
                ", label=" + label +
                ", rank=" + rank +
                ", isVisited=" + isVisited +
                '}';
    }

    public void setVisited(boolean visited) {
        isVisited = visited;
    }
}

