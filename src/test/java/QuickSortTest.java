import edu.pk.algo.sort.QuickSort;
import edu.pk.dst.Node;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.lang.reflect.Array;

public class QuickSortTest {

    @Test(enabled = false)
    public void testQuickSort() {
        int input[] = new int[]{3, 2, 4, 11, 5, 77, 8};
        int output[] = new int[]{2, 3, 4, 5, 8, 11, 77};
        QuickSort  quickSort = new QuickSort();
        quickSort.quickSortRecursive(input, 0, input.length - 1);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(input.length, output.length - 1);
        softAssert.assertEquals(input, output);
        softAssert.assertEquals(Array.get(input, 0), Array.get(output, 0));
        System.out.println("Finished......");
        Mockito.verify(quickSort).quickSortRecursive(input, 0, input.length-1);
        //softAssert.assertAll();
    }

    @Test
    public void testMock(){
        Node node = Mockito.mock(Node.class);
        Mockito.when(node.getData()).thenReturn("1000");
        System.out.println(node.getData1());
        System.out.println(node.getData());
    }

}
