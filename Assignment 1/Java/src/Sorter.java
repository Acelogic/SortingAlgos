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

    // Counting Sort
    public int[] countingSort(int[] data) {
        int[] counts = new int[data.length]; // Counting Array

        // Counting occurrences of numbers in array from 0 to n and storing them
        for (int num : data) {
            counts[num] = counts[num] + 1;
        }

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

    // Insertion Sort
    public int insertionSort(int[] data) {
        int i, j, item, count;
        // initialize count
        count = 0;
        for (i = 1; i < data.length; i++) {
            // select item to place
            item = data[i];
            j = i;
            while (j > 0 && data[j - 1] > item) {
                // continue shifting items until correct position is found
                data[j] = data[j - 1];
                j--;
                count++;  // increment shift count
            }
            data[j] = item; // place item in correct location
        }
        return count;
    }

    //Heap Sort
    public void heapSort(Long arr[]) {
        int n = arr.length;

        // Build heap (rearrange array)
        for (int i = n / 2 - 1; i >= 0; i--)
            maxHeapify(arr, n, i);

        // One by one extract an element from heap
        for (int i = n - 1; i > 0; i--) {
            // Move current root to end (Swapping)
            long temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

            // call max heapify on the reduced heap
            maxHeapify(arr, i, 0);
        }
    }

    void maxHeapify(Long[] arr, int n, int i) {
        int largest = i; // Initialize largest as root
        int l = 2 * i + 1; // left = 2*i + 1
        int r = 2 * i + 2; // right = 2*i + 2

        // If left child is larger than root
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // If right child is larger than largest so far
        if (r < n && arr[r] > arr[largest])
            largest = r;

        // If largest is not root
        if (largest != i) {
            long swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

            // Recursively heapify the affected sub-tree
            maxHeapify(arr, n, largest);
        }
    }




}





