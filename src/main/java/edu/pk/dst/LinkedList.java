package edu.pk.dst;

import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

public class LinkedList<E> implements Iterable<Node> {
    private Node first;
    private Node last;
    private int size;

    public LinkedList() {
    }

    public LinkedList(Collection<E> elements) {
        addAll(elements);
    }

    public Node getFirst() {
        return first;
    }

    public void setFirst(Node first) {
        this.first = first;
    }

    public Node getLast() {
        return last;
    }

    public void setLast(Node last) {
        this.last = last;
    }

    public void addLast(E e) {
        Node node = new Node(e);
        if (first == null) {
            first = last = node;
        } else {
            last.next = node;
            last = node;
        }
        size++;
    }

    public void addFirst(E e) {
        Node node = new Node(e);
        node.next = first;
        first = node;
        if (last == null)
            last = node;
        size++;
    }

    public Node removeFirst() {
        if (size == 0)
            throw new NoSuchElementException("No Element found to Remove.");
        Node del = first;
        if (first == last)//last element
            first = last = null;
        else
            first = first.next;
        size--;
        return del;
    }

    public Node removeLast() {
        if (size == 0)
            throw new NoSuchElementException("No Element found to Remove.");
        Node del = last;
        if (first == last)//last element
            first = last = null;
        else {
            Node curr = first;
            while (curr.next != last) {
                curr = curr.next;
            }
            last = curr;
        }
        size--;
        return del;
    }

    public void addAll(Collection<E> e) {
        e.forEach(el -> addLast(el));
    }

    public int size() {
        return size;
    }

    @Override
    public Iterator<Node> iterator() {
        return new Itr();
    }

    @Override
    public void forEach(Consumer<Node> consumer) {
        Iterator<Node> iterator = iterator();
        while (iterator.hasNext()) {
            consumer.accept(iterator.next());
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        forEach(e -> {
            sb.append(e.getData());
            if (e.getNext() != null)
                sb.append(", ");
        });
        sb.append("]");
        return sb.toString();
    }

    private class Itr implements Iterator {
        Node curr;
        int size;

        Itr() {
            curr = first;
            this.size = size();
        }

        @Override
        public boolean hasNext() {
            return size != 0;//
        }

        @Override
        public Node next() {
            Node next = curr;
            curr = curr.next;
            size--;
            return next;
        }
    }


}
