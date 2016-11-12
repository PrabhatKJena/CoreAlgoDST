package edu.pk.dst.tree;

import java.util.Stack;

/**
 * Created by Prabhat on 12/11/16.
 *                    1
 *                  /  \
 *                 2    3
 *                / \  /
 *               4  5 6
 *                 /
 *                7
 */
public class TreeTraversal {
    public static void main(String[] args) {
        BTree bTree = TreeUtil.createBTree();
        System.out.println("Pre-Order :");
        preOrder(bTree.root);
        System.out.println();
        preOrderNonRecurssive(bTree.root);
        System.out.println();

        System.out.println("In-Order :");
        inOrder(bTree.root);
        System.out.println();
        inOrderNonRecurssive(bTree.root);
        System.out.println();

        System.out.println("Post-Order :");
        postOrder(bTree.root);
        System.out.println();
        postOrderNonRecurssive(bTree.root);
        System.out.println();
    }

    private static void postOrderNonRecurssive(BNode root) {
        if (root != null) {
            Stack<BNode> stack = new Stack<>();
            BNode ptr = root;
            BNode lastVisitedNode = null;
            while (true) {
                // Push all left children except the leaf node
                while (ptr.getLeft() != null) {
                    stack.push(ptr);
                    ptr = ptr.getLeft();
                }

                // If right child is NULL or visited, then print the current node and point to its parent
                while (ptr.getRight() == null || ptr.getRight() == lastVisitedNode) {
                    System.out.print(ptr.getData() + "  ");
                    lastVisitedNode = ptr; // Track the last visited node
                    if (stack.isEmpty())
                        return;
                    ptr = stack.pop();
                }

                // Here, ptr has right child, so push it back to stack as this will be processed after its right child
                stack.push(ptr);
                ptr = ptr.getRight();
            }
        }
    }

    public static void postOrder(BNode root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            System.out.print(root.getData() + "  ");
        }
    }

    public static void inOrderNonRecurssive(BNode root) {
        if (root != null) {
            Stack<BNode> stack = new Stack<>();
            BNode ptr = root;
            while (true) {
                // Push the left children except the leaf node
                while (ptr.getLeft() != null) {
                    stack.push(ptr);
                    ptr = ptr.getLeft();
                }

                // If no Right child, then print current node and process its parent node
                while (ptr.getRight() == null) {
                    System.out.print(ptr.getData() + "  ");
                    if (stack.isEmpty())
                        return;
                    ptr = stack.pop();
                }

                // If current node has right child, then print the current node and process its right child
                System.out.print(ptr.getData() + "  ");
                ptr = ptr.getRight();
            }
        }
    }

    public static void inOrder(BNode root) {
        if (root != null) {
            inOrder(root.getLeft());
            System.out.print(root.getData() + "  ");
            inOrder(root.getRight());
        }
    }

    public static void preOrder(BNode root) {
        if (root != null) {
            System.out.print(root.getData() + "  ");
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }

    public static void preOrderNonRecurssive(BNode root) {
        if (root != null) {
            Stack<BNode> stack = new Stack<>();
            stack.push(root);
            while (!stack.isEmpty()) {
                BNode currentNode = stack.pop();
                System.out.print(currentNode.getData() + "  ");
                if (currentNode.getRight() != null) {
                    stack.push(currentNode.getRight());
                }
                if (currentNode.getLeft() != null) {
                    stack.push(currentNode.getLeft());
                }
            }
        }
    }
}