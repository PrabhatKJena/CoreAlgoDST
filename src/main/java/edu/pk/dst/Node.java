package edu.pk.dst;

public class Node<E> {
    protected E data;
    protected Node<E> next;

    public Node(){

    }

    public Node(E data) {
        this.data = data;
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public Node<E> getNext() {
        return next;
    }

    public void setNext(Node<E> next) {
        this.next = next;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", next=" + (next == null ? "X" : next.getData()) +
                '}';
    }
}