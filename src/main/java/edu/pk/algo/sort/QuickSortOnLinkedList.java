package edu.pk.algo.sort;

import edu.pk.PKUtil;
import edu.pk.dst.LinkedList;
import edu.pk.dst.Node;

import java.util.Arrays;

public class QuickSortOnLinkedList {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>(Arrays.asList(12, 8, -3, 111, 23, -90, 304, 2, 56, -34, 0));
        System.out.println(list);
        quickSort(list, list.getFirst(), list.getLast());
        System.out.println(list);
    }

    public static void quickSort(LinkedList<Integer> list, Node first, Node last) {
        if (first != last) {
            Node pivot = findPivotNode(list, first, last);
            if (first != pivot && first.getNext() != pivot) // Sort, if there is eny element present between first and pivot
                quickSort(list, first, prevNode(list, pivot));
            if (pivot != last && pivot.getNext() != last)// Sort, if there is eny element present between pivot and last
                quickSort(list, pivot.getNext(), last);
        }
    }

    private static Node prevNode(LinkedList<Integer> list, Node target) {
        if (target == null || target == list.getFirst())
            return null;
        Node node = list.getFirst();
        while (node.getNext() != target)
            node = node.getNext();
        return node;
    }

    private static Node findPivotNode(LinkedList<Integer> list, Node first, Node last) {
        Node<Integer> pivot = last;
        Node<Integer> i = prevNode(list, first); // let i is the out of the range first-last
        Node<Integer> j = first;
        while (j != last) { // repeat for j : first -> pivot.prev
            if (j.getData().intValue() < pivot.getData().intValue()) {
                if (i == null) // for 1st move of i
                    i = first;
                else
                    i = i.getNext();
                if (i != j)
                    swap(i, j);
            }
            j = j.getNext();
        }
        swap(i == null ? first : i.getNext(), pivot); // If i is NULL then i+1 will be the first node else i.next()
        return (i == null ? first : i.getNext()); // If i is NULL then i+1 will be the first node else i.next()
    }

    private static void swap(Node<Integer> i, Node<Integer> j) {
        Node<Integer> t = new Node<Integer>();
        t.setData(i.getData());
        i.setData(j.getData());
        j.setData(t.getData());
    }

}
