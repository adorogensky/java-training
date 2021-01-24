package training.algorithms;

public class RehearseQuickSort {

    public int[] sort(int... a) {
        return sort(a, 0, a.length - 1);
    }

    private int[] sort(int[] a, int startIdx, int endIdx) {
        if (startIdx >= endIdx) return a;
        int pivotIdx = partition(a, startIdx, endIdx);
        sort(a, startIdx, pivotIdx - 1);
        sort(a, pivotIdx + 1, endIdx);
        // let's chose a pivot and get idx for that element
        // rearrange array in such a way that all elements preceding pivot element are less than it
        // and all elements following the pivot element are greater than or equal to the pivot element
        // repeat sort for the part of the array before the pivot element and after the pivot element
        return a;
    }

    private int partition(int[] a, int startIdx, int endIdx) {
        // choosing a pivot element, let it be the rightmost element of the array;
        int pivot = a[startIdx];
        int pivotIdx = startIdx + 1;

        for (int i = startIdx + 1; i <= endIdx; i++) {
            if (a[i] < pivot) {
                swap(a, i, pivotIdx++);
            }
        }

        swap(a, startIdx, --pivotIdx);
        return pivotIdx;
    }

    private void swap(int[] a, int i, int j) {
        if (i != j) {
            int k = a[i];
            a[i] = a[j];
            a[j] = k;
        }
    }
}
