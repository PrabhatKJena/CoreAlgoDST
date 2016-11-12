package edu.pk.dst.tree;

public class BNode<E> {
    private BNode<E> left;
    private BNode<E> right;
    private E data;

    public BNode(BNode<E> left, BNode<E> right, E data) {
        this.left = left;
        this.right = right;
        this.data = data;
    }

    public BNode(E data) {
        this.data = data;
    }

    public BNode<E> getLeft() {
        return left;
    }

    public void setLeft(BNode<E> left) {
        this.left = left;
    }

    public BNode<E> getRight() {
        return right;
    }

    public void setRight(BNode<E> right) {
        this.right = right;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "{" +
                "[" + (left == null ? "X" : left.getData()) + "]" +
                "," + data +
                ",[" + (right == null ? "X" : right.getData()) + "]" +
                '}';
    }
}
