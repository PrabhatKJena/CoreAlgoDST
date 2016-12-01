package edu.pk.dst.tree;

import java.util.Stack;

/**
 * Created by Prabhat on 12/11/16.
 *        1
 *      /  \
 *     2    3
 *    / \  /
 *   4  5 6
 *  /
 *  7
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
        inOrderIterative(bTree.root);
        System.out.println();

        System.out.println("Post-Order :");
        postOrder(bTree.root);
        System.out.println();
        postOrderNonRecurssive(bTree.root);
        System.out.println();
        postOrderIterative(bTree.root);
        System.out.println();
    }

    private static void postOrderIterative(BNode root) {
        if (root != null) {
            Stack<BNode> s1 = new Stack<>();
            Stack<BNode> s2 = new Stack<>();
            s1.push(root);
            while (!s1.isEmpty()) {
                BNode bNode = s1.pop();
                s2.push(bNode);

                if (bNode.getLeft() != null)
                    s1.push(bNode.getLeft());
                if (bNode.getRight() != null)
                    s1.push(bNode.getRight());
            }

            while (!s2.isEmpty()) {
                System.out.print(s2.pop().getData() + "  ");
            }

        }
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

    public static void inOrderIterative(BNode root) {
        Stack<BNode> stack = new Stack<>();
        BNode currNode = root;
        while (true) {
            if (currNode != null) { // Id node != NULL, move to left
                stack.push(currNode);
                currNode = currNode.getLeft();
            } else { // When currNode == NULL, i.e. left is NULL, So process the parent from popping from Stack
                // and move to right
                if (stack.isEmpty()) // If Stack is Empty then exit
                    return;
                currNode = stack.pop();
                System.out.print(currNode.getData() + "  ");
                currNode = currNode.getRight();
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