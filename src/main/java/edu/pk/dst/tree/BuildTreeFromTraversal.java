package edu.pk.dst.tree;

public class BuildTreeFromTraversal {
    public static void main(String[] args) {
        String postOrder = "452869731";
        String inOrder = "425186379";
        String preOrder = "124536879";
        BNode<Character> bNode = buildFromInAndPost(inOrder, 0, inOrder.length() - 1, postOrder, 0, postOrder.length() - 1);
        printPreorder(bNode);
        System.out.println();

        bNode = buildFromInAndPre(inOrder, 0, inOrder.length() - 1, preOrder, 0, preOrder.length() - 1);
        printPreorder(bNode);

        //bNode = buildFromPreAndPost(inOrder, 0, inOrder.length() - 1, preOrder, 0, preOrder.length() - 1);
    }

    public static BNode<Character> buildFromPreAndPost(String inOrder, int inStart, int inEnd, String postOrder, int postStart, int postEnd) {
        return null;
    }

    private static void printPreorder(BNode<Character> bNode) {
        if (bNode == null)
            return;
        System.out.print(bNode.getData() + "  ");
        printPreorder(bNode.getLeft());
        printPreorder(bNode.getRight());
    }

    public static BNode<Character> buildFromInAndPost(String inOrder, int inStart, int inEnd, String postOrder, int postStart, int postEnd) {
        if (inEnd < inStart || postEnd < postStart) {
            return null;
        }
        // Taking last from Post-order as Root
        char rootData = postOrder.charAt(postEnd);
        BNode<Character> root = new BNode(rootData);

        // If only one node in in-order then return the root as the tree contains only root node
        if (inStart == inEnd)
            return root;

        // Find the root index from in-order
        int rootIndex = searchRootIndex(inOrder, inStart, inEnd, rootData);
        if (rootIndex != -1) {
            // left subtree will be from inStart to rootIndex-1 in in-order and from postStart to postStart+rootIndex-(inStart+1) in post-order
            // Inorder = 425186379, here rootIndex is (3). So left subtree is from inStart to rootIndex-1 i.e 425
            // Postorder = 452869731,  and here left subtree will be from 452 i.e postStart to postStart+rootIndex-(inStart+1)
            root.setLeft(buildFromInAndPost(inOrder, inStart, rootIndex - 1, postOrder, postStart, postStart + rootIndex - (inStart + 1)));
            root.setRight(buildFromInAndPost(inOrder, rootIndex + 1, inEnd, postOrder, postStart + rootIndex - inStart, postEnd - 1));
        }
        return root;
    }

    public static BNode<Character> buildFromInAndPre(String inOrder, int inStart, int inEnd, String preOrder, int preStart, int preEnd) {
        if (inEnd < inStart || preEnd < preStart) {
            return null;
        }
        char rootData = preOrder.charAt(preStart);
        BNode<Character> root = new BNode(rootData);

        if (inStart == inEnd)
            return root;

        int rootIndex = searchRootIndex(inOrder, inStart, inEnd, rootData);
        if (rootIndex != -1) {
            root.setLeft(buildFromInAndPre(inOrder, inStart, rootIndex - 1, preOrder, preStart + 1, preStart + rootIndex - inStart));
            root.setRight(buildFromInAndPre(inOrder, rootIndex + 1, inEnd, preOrder, preStart + rootIndex - inStart + 1, preEnd));
        }
        return root;
    }

    private static int searchRootIndex(String inOrder, int inStart, int inEnd, char rootData) {
        char nodes[] = inOrder.toCharArray();
        for (int i = inStart; i <= inEnd; i++) {
            if (nodes[i] == rootData)
                return i;
        }
        return -1;
    }
}
