package edu.pk.dst.tree;

/**
 * Created by prabhat on 12/11/16.
 */
public class TreeUtil {

    /**
     * Do not change this
     *                    1
     *                  /  \
     *                 2    3
     *                / \  /
     *               4  5 6
     *                 /
     *                7
     */
    public static BTree createBTree(){
        BTree<Integer> root = new BTree<>();
        root.root = new BNode<Integer>(1);

        root.root.setLeft(new BNode<Integer>(2));
        root.root.setRight(new BNode<Integer>(3));

        root.root.getLeft().setLeft(new BNode<Integer>(4));
        root.root.getLeft().setRight(new BNode<Integer>(5));

        root.root.getRight().setLeft(new BNode<Integer>(6));
        root.root.getLeft().getRight().setLeft(new BNode<Integer>(7));;

        return root;
    }
}
