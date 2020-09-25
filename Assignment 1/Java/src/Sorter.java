import java.util.*;

/*
@author  Miguel Cruz (mcruz@oswego.edu)
 */


public class Sorter {


    private String sortingState;

    public Sorter() {

    }

    // Generic methods makes use of method overloading
    // Quick Sort
    public <T extends Number & Comparable<T>> int quickSort(T[] data, int first, int last) {
        sortingState = "QuickSort";
        int left, right, count;
        T pivot;
        // initialize count
        count = 0;
        // breaks out of recursive function for base case
        if (first >= last) {
            return count;
        }
        left = first;
        right = last;
        // chose a pivot between the left and right
        pivot = data[((first + last) / 2)];
        do {
            // Find a left value in the wrong place  (if data value is less than pivot)
            //data[right] < pivot
            while (data[left].compareTo(pivot) < 0) {
                left++;
            }
            // Find a right value in the wrong place (if data value is more than pivot)
            // data[right] > pivot
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


    // The main function to that sorts arr[] of size n using
    // Radix Sort
    public void radixSort(Long arr[], int n) {
        sortingState = "RadixSort";
        // Find the maximum number to know number of digits
        Long max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max)
                max = arr[i];
        }

        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (Long exp = 1L; max / exp > 0; exp *= 10) {
            countSort(arr, n, exp);
            //System.out.println("========================================================");
            // System.out.println(Arrays.toString(arr));
        }

    }

    // A function to do counting sort of arr[] according to
    // the digit represented by exp (10^n).
    private void countSort(Long[] arr, int arrSize, Long exp) {
        Long[] output = new Long[arrSize]; // output array
        int i;
        int[] countingArray = new int[10];
        Arrays.fill(countingArray, 0);

        // Store counts of occurrences in countingArray[]
        for (i = 0; i < arrSize; i++) {
            // System.out.println("Number: " + arr[i] + "  Exp: " + exp + " Observed Digit: " + ((arr[i] / exp) % 10));
            countingArray[(int) ((arr[i] / exp) % 10)]++;
        }

        // Change countingArray[i] so that countingArray[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++) {
            countingArray[i] += countingArray[i - 1];

        }
        // Build the output array
        for (i = arrSize - 1; i >= 0; i--) {
            output[countingArray[(int) ((arr[i] / exp) % 10)] - 1] = arr[i];
            countingArray[(int) ((arr[i] / exp) % 10)]--;

        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to current digit
        for (i = 0; i < arrSize; i++) {
            arr[i] = output[i];
        }
    }


    //Heap Sort
    public void heapSort(Long arr[]) {
        sortingState = "HeapSort";
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

    private void maxHeapify(Long[] arr, int n, int i) {
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

    @Override
    public String toString() {
        return sortingState;
    }
}





