import jdk.swing.interop.SwingInterOpUtils;

import java.io.IOException;
import java.util.*;

/*
@author  Miguel Cruz (mcruz@mcruz.me)
 */


public class Sorter {

    public Sorter() {

    }

    // Generic methods makes use of method overloading
    // Quick Sort
    public <T extends Number & Comparable<T>> int quickSort(T[] data, int first, int last) {
        int left, right, count;
        T pivot;
        // initialize count
        count = 0;
        if (first >= last) {
            return count;
        }
        left = first;
        right = last;
        // chose a pivot between the left and right
        pivot = data[((first + last) / 2)];
        do {
            // Find a left value in the wrong place  (if data value is less than pivot)
            while (data[left].compareTo(pivot) < 0) {
                left++;
            }
            // Find a right value in the wrong place (if data value is more than pivot)
            while (data[right].compareTo(pivot) > 0) {
                right--;
            }
            // swap the values
            if (left <= right) {
                count++;  // increment the swap count
                // swap the values
                T temp = data[left];
                data[left] = data[right];
                data[right] = temp;
                left++;
                right--;
            }
        }
        while (left <= right);
        // Recursive call Quick Sort on the left and right sub arrays
        int rCount = quickSort(data, first, right);
        int lCount = quickSort(data, left, last);
        // return the total swap count
        return count + rCount + lCount;
    }

    //Radix Sort (FIFO)
    public void radixSort(Long[] data) {

        // Getting Max Value
        Long currentMax = data[0];
        for (Long maxCandidate : data) {
            if (currentMax < maxCandidate) {
                currentMax = maxCandidate;
            }
        }

        int countingSortIterations = currentMax.toString().length();

        Queue<Long>[] buckets = new Queue[10];
        for (int exp = 0; exp < countingSortIterations; exp++) {
            for (int i = 0; i < buckets.length; i++) {
                buckets[i] = new LinkedList<Long>();
            }
            int[] placeValList = new int[data.length];

            for (int i = 0; i < data.length; i++) {
                int singleNumPlaceValue = (int) Math.floor(data[i] / Math.pow(10, exp) % 10);

                placeValList[i] = singleNumPlaceValue;
                switch (singleNumPlaceValue) {
                    case 0:
                        buckets[0].add(data[i]);
                        break;
                    case 1:
                        buckets[1].add(data[i]);
                        break;
                    case 2:
                        buckets[2].add(data[i]);
                        break;
                    case 3:
                        buckets[3].add(data[i]);
                        break;
                    case 4:
                        buckets[4].add(data[i]);
                        break;
                    case 5:
                        buckets[5].add(data[i]);
                        break;
                    case 6:
                        buckets[6].add(data[i]);
                        break;
                    case 7:
                        buckets[7].add(data[i]);
                        break;
                    case 8:
                        buckets[8].add(data[i]);
                        break;
                    case 9:
                        buckets[9].add(data[i]);
                        break;
                }
            }

            // Temporary Space to store passes
            ArrayList<Long> temp = new ArrayList<>();
            // Flush Queues onto main data array starting from 0 to 9
            for (Queue<Long> bucket : buckets) {
                temp.addAll(bucket);
            }
            // Copy results from previous pass and make it new set of data
            System.arraycopy(temp.toArray(), 0, data, 0, data.length);

        }


    }


    public int[] countingSort(int[] data) {
        int[] counts = new int[10]; // Counting Array

        // Counting occurrences of numbers in array from 0 to 9 and storing them
        for (int num : data) {
            counts[num] = counts[num] + 1;
        }

        /*System.out.println("Data:   " + Arrays.toString(data));
        System.out.println("        -------------------------------");
        System.out.println("Counts: " + Arrays.toString(counts));
        System.out.println("Places:  0  1  2  3  4  5  6  7  8  9");
        System.out.println("        -------------------------------");*/

        // Builds sorting array
        ArrayList<Integer> sorted = new ArrayList<>();
        // Whole Number Pointer Starts at zero
        int wholeNumberPointer = 0;
        for (int wholeNumberPlace : counts) {
            // Iterates and applies the number of whole numbers counted to the array
            for (int i = 0; i < wholeNumberPlace; i++) {
                sorted.add(wholeNumberPointer);
            }
            // Increment Whole Number Pointer
            wholeNumberPointer++;
        }

        // Converts Integers in arraylist to primitive types and copies the sorted data to the original data array
        System.arraycopy(sorted.stream().mapToInt(Integer::valueOf).toArray(), 0, data, 0, data.length);
        return data;

    }

    public static void main(String[] args) throws IOException {
        CSVReader r = new CSVReader();
        Sorter s = new Sorter();

        Long[] bigData = r.getUpc14List().toArray(new Long[0]);
        Long smallData[] = {3221L, 1L, 10L, 9680L, 577L, 9420L, 7L, 5622L, 4793L, 2030L, 3138L, 82L, 2599L, 743L, 4127L};


        int dataLength = r.getUpc14List().toArray().length - 1;

        int[] countingSortTest = new int[20];
        for (int i = 0; i < countingSortTest.length; i++) {
            countingSortTest[i] = (int) (Math.random() * 10);
        }




        Long[] testingArray = bigData;
        //Sort begins

        Long begin = System.currentTimeMillis();
        s.quickSort(testingArray, 0, dataLength);
        //s.countingSort(countingSortTest);
        //s.radixSort(testingArray);
        Long end = System.currentTimeMillis() - begin;
        System.out.println("Result: " + Arrays.toString(testingArray));
        System.out.println(" FINISHED IN : " + end + "ms");


    }

}


