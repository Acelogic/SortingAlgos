import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.LinkedHashMap;

public class Main {
    public static void main(String[] args) throws IOException {
        CSVReader csvReader = new CSVReader();
        Sorter sorter = new Sorter();

        Long[] bigData = csvReader.getUpc14List().toArray(new Long[0]);
        Long smallData[] = {12345678901234L, 11L, 13L, 5L, 16L, 7L};

        // The Array to be Sorted
        Long[] arr = smallData;


        //Sort begins
        Long begin = System.currentTimeMillis();
        //sorter.quickSort(arr, 0, arr.length-1 );
        //sorter.radixSort(arr);
        //sorter.heapSort(arr);
        Long end = System.currentTimeMillis() - begin;
        System.out.println("Result:  " + Arrays.toString(arr));


        // Populating the final map to deliver
        // LinkedHashMap to Keep Insertion order
        LinkedHashMap<Long, String> sortedProducts = new LinkedHashMap<>();
        for (int i = 0; i < arr.length; i++) {
            String name = csvReader.getUpc14ProductMap().get(arr[i]);
            sortedProducts.put(arr[i], name);
        }

        // Write Output of Sorts to File
        BufferedWriter bw = new BufferedWriter(new FileWriter("Output.txt"));
        for (Long key : sortedProducts.keySet()) {
           // System.out.println("UPC14: " + String.format("%014d", key) + " Name: " + sortedProducts.get(key));
            bw.write("UPC14: " + String.format("%014d", key) + " Name: " + sortedProducts.get(key)+"\n");
        }
        bw.write("SORTING FINISHED IN : " + end + "ms");
        System.out.println("SORTING FINISHED IN : " + end + "ms");
        bw.flush();
        bw.close();






    }

}
