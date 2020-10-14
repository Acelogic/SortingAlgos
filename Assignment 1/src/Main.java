import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    public static Hashtable<String, ArrayList<Long>>  timingTable = new Hashtable<>();

    public static ArrayList<Long> getTimingTable(String algo, String dataSet) {
        return timingTable.get(algo +" "+dataSet);
    }

    public static String sorterRunner(Sorter sorter, Long[] arr, String algo, String dataSet) {
        final Long[] cachedUnsorted = arr.clone();

        ArrayList<Long> timingStoreSorted = new ArrayList<>();


        if (dataSet.equalsIgnoreCase("Sorted")) {
            //Gets Average of Sorted Data

            //Initial Sort to make arr sorted
            sorter.quickSort(arr, 0, arr.length - 1);

            // Gets Average of sorted Data
            for (int i = 0; i < 16; i++) {
                if (algo.equalsIgnoreCase("QuickSort")){
                    Long begin = System.currentTimeMillis();
                    sorter.quickSort(arr, 0, arr.length - 1);
                    Long end = System.currentTimeMillis() - begin;
                    timingStoreSorted.add(end);
                }
                if (algo.equalsIgnoreCase("RadixSort")) {
                    Long begin = System.currentTimeMillis();
                    sorter.radixSort(arr, arr.length);
                    Long end = System.currentTimeMillis() - begin;
                    timingStoreSorted.add(end);
                }
                if (algo.equalsIgnoreCase("HeapSort")) {
                    Long begin = System.currentTimeMillis();
                    sorter.heapSort(arr);
                    Long end = System.currentTimeMillis() - begin;
                    timingStoreSorted.add(end);

                }


            }

            timingTable.put(sorter.toString().toLowerCase() + " " + dataSet, timingStoreSorted);

            OptionalDouble average = timingStoreSorted.stream().mapToDouble(a -> a).average();
            return sorter.toString() + " Average " + average.getAsDouble() + "ms" + " on Sorted Data";

        } else if (dataSet.equalsIgnoreCase("Unsorted")) {
            // Gets Average of Unsorted Data
            for (int i = 0; i < 16; i++) {
                if (algo.equalsIgnoreCase("QuickSort")) {
                    Long begin = System.currentTimeMillis();
                    sorter.quickSort(arr, 0, arr.length - 1);
                    Long end = System.currentTimeMillis() - begin;
                    timingStoreSorted.add(end);
                    arr = cachedUnsorted.clone();
                }
                if (algo.equalsIgnoreCase("RadixSort")) {
                    Long begin = System.currentTimeMillis();
                    sorter.radixSort(arr, arr.length);
                    Long end = System.currentTimeMillis() - begin;
                    timingStoreSorted.add(end);
                    arr = cachedUnsorted.clone();
                }
                if (algo.equalsIgnoreCase("HeapSort")) {
                    Long begin = System.currentTimeMillis();
                    sorter.heapSort(arr);
                    Long end = System.currentTimeMillis() - begin;
                    timingStoreSorted.add(end);
                    arr = cachedUnsorted.clone();
                }

            }

            timingTable.put(sorter.toString().toLowerCase() + " " + dataSet, timingStoreSorted);

            OptionalDouble average = timingStoreSorted.stream().mapToDouble(a -> a).average();
            return sorter.toString() + " Average " + average.getAsDouble() + "ms" + " on Unsorted Data";
        }
        return null;
    }


    public static void main(String[] args) throws IOException {
        CSVReader csvReader = new CSVReader();
        Sorter sorter = new Sorter();

        Long[] bigData = csvReader.getDateList().toArray(new Long[0]);

        // The Array to be Sorted
        Long[] arr = bigData.clone();

        //Sorting Begins
        String sortingResultsQSS = sorterRunner(sorter, arr, "quicksort", "sorted");
        // resets sorted list
        arr = bigData.clone();

        String sortingResultsQSUS = sorterRunner(sorter, arr, "quicksort", "unsorted");
        arr = bigData.clone();

        String sortingResultsRSS = sorterRunner(sorter, arr, "radixsort", "sorted");
        arr = bigData.clone();

        String sortingResultsRSUS = sorterRunner(sorter, arr, "radixsort", "unsorted");
        arr = bigData.clone();

        String sortingResultsHSS = sorterRunner(sorter, arr, "heapsort", "sorted");
        arr = bigData.clone();

        // This last algo sorts the actual data getting put in output.txt
        // since arr is not being reset after this
        String sortingResultsHSUS = sorterRunner(sorter, arr, "heapsort", "unsorted");


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

        bw.write("==============================================================\n");
        bw.write("\t\t\t\tQuick Sort Ran 16 times\n");
        for (Long runtime : getTimingTable("quicksort", "sorted")) {
            bw.write(runtime + "ms\n");
        }
        bw.write(sortingResultsQSS + "\n");
        bw.write("\n\t\t\t\tQuick Sort Ran 16 times\n");
        for (Long runtime : getTimingTable("quicksort", "unsorted")) {
            bw.write(runtime + "ms\n");
        }
        bw.write(sortingResultsQSUS + "\n");
        bw.write("==============================================================\n");
        bw.write("\t\t\t\tRadix Sort Ran 16 times\n");
        for (Long runtime : getTimingTable("radixsort", "sorted")) {
            bw.write(runtime + "ms\n");
        }
        bw.write(sortingResultsRSS + "\n");
        bw.write("\n\t\t\t\tRadix Sort Ran 16 times\n");
        for (Long runtime : getTimingTable("radixsort", "unsorted")) {
            bw.write(runtime + "ms\n");
        }
        bw.write(sortingResultsRSUS + "\n");
        bw.write("==============================================================\n");
        bw.write("\t\t\t\tHeap Sort Ran 16 times\n");
        for (Long runtime : getTimingTable("heapsort", "sorted")) {
            bw.write(runtime + "ms\n");
        }
        bw.write(sortingResultsHSS + "\n");
        bw.write("\n\t\t\t\tHeap Sort Ran 16 times\n");
        for (Long runtime : getTimingTable("heapsort", "unsorted")) {
            bw.write(runtime + "ms\n");
        }
        bw.write(sortingResultsHSUS + "\n");
        bw.write("==============================================================");
        bw.flush();
        bw.close();

    }
}
