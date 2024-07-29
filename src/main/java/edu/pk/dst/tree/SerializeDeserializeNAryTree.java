import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

/*
A
+---B
+---C
    +---E
    +---F
+---D
A:3,B:0,C:2,D:0,E:0,F:0,
A
+---B
+---C
    +---E
    +---F
+---D

*/

public class SerializeDeserializeNAryTree {

  static class NNode {

    String val;
    List<NNode> children = new ArrayList<>();
    int childrenSize;
    NNode next;

    public NNode(String val) {
      this.val = val;
    }

    @Override
    public String toString() {
      return '[' + val + " -> " + children.stream().map(nNode -> nNode.val).collect(Collectors.joining(",")) + ']';
    }

    public static NNode newNode(String v) {
      return new NNode(v);
    }

    public static NNode newChild(NNode parent, String child) {
      final NNode nNode = newNode(child);
      parent.children.add(nNode);
      return nNode;
    }

    public static NNode newChildren(NNode parent, List<String> children) {
      for (String child : children) {
        parent.children.add(newNode(child));
      }
      return parent;
    }

    public NNode getChild(int i) {
      return children.get(i);
    }
  }

  public static void main(String[] args) {
    NNode a = NNode.newNode("A");
    NNode.newChildren(a, Arrays.asList("B", "C", "D"));
    NNode.newChildren(a.getChild(1), Arrays.asList("E", "F"));

    printTree(a);
    final String serialize = serialize(a);
    System.out.println(serialize);
    final NNode nNode = deserialize(serialize);
    printTree(nNode);
  }

  private static NNode deserialize(String log) {
    if (log == null) {
      return null;
    }
    final String[] tokens = log.split(",");
    final NNode root = NNode.newNode(tokens[0].split(":")[0]);
    root.childrenSize = Integer.parseInt(tokens[0].split(":")[1]);
    Queue<NNode> queue = new LinkedList<>();
    queue.add(root);
    int i = 1;
    while (!queue.isEmpty()) {
      final NNode current = queue.poll();
      int n = current.childrenSize;
      for (; n > 0; n--) {
        final String[] nodeInfo = tokens[i].split(":");
        final NNode child = NNode.newNode(nodeInfo[0]);
        child.childrenSize = Integer.parseInt(nodeInfo[1]);
        i++;
        current.children.add(child);
      }
      queue.addAll(current.children);
    }
    return root;
  }

  private static String serialize(NNode root) {
    StringBuilder log = new StringBuilder();
    Queue<NNode> queue = new LinkedList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      NNode node = queue.poll();
      if (null == node) {
        log.append("null,");
        continue;
      }
      log.append(node.val).append(":");
      log.append(node.children.size()).append(",");
      queue.addAll(node.children);
    }
    return log.toString();
  }

  private static void traverOrderByLevel(NNode root) {
    if (root == null) {
      return;
    }
    List<NNode> queue = new ArrayList<>();
    queue.add(root);
    while (!queue.isEmpty()) {
      NNode node = queue.remove(0);
      if (node.val.equals("X")) {
        System.out.println();
        continue;
      } else {
        System.out.print(node.val + " ");
      }
      if (queue.isEmpty()) {
        queue.add(NNode.newNode("X"));
      }
      boolean lastChild = queue.get(0).val.equals("X");
      if (!node.children.isEmpty()) {
        queue.addAll(node.children);
      }
      if (lastChild) {
        queue.add(NNode.newNode("X"));
      }
    }
  }

  private static int diameter(NNode root) {
    if (root == null) {
      return 0;
    }
    int max1 = 0, max2 = 0;
    for (NNode child : root.children) {
      int h = height(child);
      if (h > max1) {
        max2 = max1;
        max1 = h;
      } else if (h > max2) {
        max2 = h;
      }
    }
    int maxDiameter = root.children.stream().mapToInt(SerializeDeserializeNAryTree::diameter).max().orElse(0);
    return Math.max(maxDiameter, max1 + max2 + 2);
  }

  private static int height(NNode root) {
    if (root == null || root.children.isEmpty()) {
      return 0;
    }
    return root.children.stream().mapToInt(SerializeDeserializeNAryTree::height).max().orElse(0) + 1;
  }

  private static void printTree(NNode root) {
    printNTreeAsList(root, 0, "+---");
  }

  private static void printNTreeAsList(NNode root, int level, String indent) {
    if (level == 0) {
      System.out.println(root.val);
    } else {
      System.out.println(indent + root.val);
    }
    for (Iterator<NNode> iterator = root.children.iterator(); iterator.hasNext(); ) {
      NNode child = iterator.next();
      String newIndent = indent;
      if (level > 0) {
//        if (iterator.hasNext()) {
//          newIndent = "|   " + indent;
//        } else {
        newIndent = "    " + indent;
//        }
      }
      printNTreeAsList(child, level + 1, newIndent);
    }
  }

}
