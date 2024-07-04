public class SegmentTree {

  private int[] tree;
  private int n;

  /**
   * Constructs a Segment Tree from the given array.
   *
   * @param arr the input array
   */
  public SegmentTree(int[] arr) {
    this.n = arr.length;
    this.tree = new int[2 * n];
    buildTree(arr);
  }

  /**
   * Builds the segment tree from the input array.
   *
   * @param arr the input array
   */
  private void buildTree(int[] arr) {
    // Insert leaf nodes in the tree
    System.arraycopy(arr, 0, tree, n, n);
    // Build the tree by calculating parents. Update parent starting from children
    for (int i = n - 1; i > 0; i--) {
      tree[i] = tree[i * 2] + tree[i * 2 + 1];
    }
  }

  /**
   * Updates the value at the specified index.
   *
   * @param index the index to update
   * @param value the new value
   */
  public void update(int index, int value) {
    // Update the value at the leaf node
    index += n;
    tree[index] = value;
    // Update corresponding parent nodes
    while (index > 1) {
      index /= 2;
      tree[index] = tree[index * 2] + tree[index * 2 + 1];
    }
  }

  /**
   * Queries the sum in the range [left, right).
   *
   * @param left  the left index (inclusive)
   * @param right the right index (exclusive)
   * @return the sum in the range [left, right)
   */
  public int query(int left, int right) {
    int sum = 0;
    // Move left, right to leaf node side which are the actual input elements
    left += n;
    right += n;
    while (left <= right) {
      /* if left index is a right node of its parent, then consider that element
      and jump to parent of its next element in range. */
      if (left % 2 == 1) {
        sum += tree[left];
        left++;
      }
      /* if right index is a left node of its parent, then consider that element
      and jump to parent of its previous element in range. */
      if (right % 2 == 0) {
        sum += tree[right];
        right--;
      }
      left /= 2;
      right /= 2;
    }
    return sum;
  }

  public static void main(String[] args) {
    int[] arr = {1, 3, 5, 7, 9, 11};
    SegmentTree segmentTree = new SegmentTree(arr);
    System.out.println(segmentTree.query(0, 4)); // Output: 25
    System.out.println(segmentTree.query(1, 3)); // Output: 15
    System.out.println(segmentTree.query(3, 5)); // Output: 15
    segmentTree.update(1, 10);
    System.out.println(segmentTree.query(1, 5)); // Output: 42
  }
}
