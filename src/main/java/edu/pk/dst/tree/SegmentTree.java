import java.util.ArrayList;
import java.util.List;
import java.util.function.BiFunction;

public class SegmentTree<T> {

  private final List<T> tree;
  private final int n;
  private final BiFunction<T, T, T> operation;

  /**
   * Constructs a Segment Tree from the given array.
   *
   * @param arr the input array
   */
  public SegmentTree(T[] arr, BiFunction<T, T, T> function) {
    this.n = arr.length;
    this.tree = new ArrayList<>(2 * n);
    this.operation = function;
    buildTree(arr, this.operation);
  }

  /**
   * Builds the segment tree from the input array.
   *
   * @param arr the input array
   */
  private void buildTree(T[] arr, BiFunction<T, T, T> func) {
    // Insert leaf nodes in the tree
    //System.arraycopy(arr, 0, tree, n, n);
    for (int i = 0; i < n; i++) {
      tree.add(i, null);
    }
    for (int i = n; i < 2 * n; i++) {
      tree.add(i, arr[i - n]);
    }
    // Build the tree by calculating parents. Update parent starting from children
    for (int i = n - 1; i > 0; i--) {
      tree.set(i, func.apply(tree.get(i * 2), tree.get(i * 2 + 1)));
    }
  }

  /**
   * Updates the value at the specified index.
   *
   * @param index the index to update
   * @param value the new value
   */
  public void update(int index, T value) {
    // Update the value at the leaf node
    index += n;
    tree.set(index, value);
    // Update corresponding parent nodes
    while (index > 1) {
      index /= 2;
      tree.set(index, operation.apply(tree.get(index * 2), tree.get(index * 2 + 1)));
    }
  }

  /**
   * Queries the required result in the range [left, right).
   *
   * @param left  the left index (inclusive)
   * @param right the right index (exclusive)
   * @return the result in the range [left, right)
   */
  public T query(int left, int right) {
    T result = null;
    // Move left, right to leaf node side which are the actual input elements
    left += n;
    right += n;
    while (left <= right) {
      /* if left index is a right node of its parent, then consider that element
      and jump to parent of its next element in range. */
      if (left % 2 == 1) {
        result = operation.apply(result, tree.get(left));
        left++;
      }
      /* if right index is a left node of its parent, then consider that element
      and jump to parent of its previous element in range. */
      if (right % 2 == 0) {
        result = operation.apply(result, tree.get(right));
        right--;
      }
      left /= 2;
      right /= 2;
    }
    return result;
  }

  public static void main(String[] args) {
    Integer[] arr = {5, 11, 1, 7, 9, 3};
    SegmentTree<Integer> maxSegmentTree = new SegmentTree<>(arr, (a, b) -> {
      if (a == null) {
        return b;
      }
      return Math.max(a, b);
    });
    System.out.println(maxSegmentTree.query(0, 4)); // Output: 11
    System.out.println(maxSegmentTree.query(1, 3)); // Output: 11
    System.out.println(maxSegmentTree.query(3, 5)); // Output: 9
    System.out.println(maxSegmentTree.query(1, 5)); // Output: 11
    maxSegmentTree.update(1, 8);
    System.out.println(maxSegmentTree.query(1, 5)); // Output: 9
  }
}
