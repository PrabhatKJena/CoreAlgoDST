import org.supercsv.cellprocessor.constraint.NotNull;
import org.supercsv.cellprocessor.ift.CellProcessor;
import org.supercsv.io.CsvBeanReader;
import org.supercsv.io.ICsvBeanReader;
import org.supercsv.prefs.CsvPreference;

import java.io.FileReader;
import java.io.IOException;

/**
 * Created by prabhat on 9/9/16.
 */
public class CSVTest {
    public static void main(String[] args) throws IOException {
        ICsvBeanReader beanReader = new CsvBeanReader(new FileReader("/home/prabhat/Documents/books.csv"),
                CsvPreference.STANDARD_PREFERENCE);

        String[] header = beanReader.getHeader(true);
        Book bookBean = null;

        CellProcessor[] processors = new CellProcessor[] {
                new NotNull(), // ISBN
                new NotNull() // title
        };

        while ((bookBean = beanReader.read(Book.class, header, processors)) != null) {
            System.out.println(bookBean);
        }
    }
}

