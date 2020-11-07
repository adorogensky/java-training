package training.algorithms;

import java.util.Random;

public class RehearseQuickSort {

    public int[] sort(int... array) {
        return sort(array, 0, array.length - 1);
    }

    private int[] sort(int[] array, int l, int r) {
        if (l >= r) return array;
        // assuming here l < r
        int pIdx = partition(array, l, r);
        sort(array, 0, pIdx - 1);
        sort(array, pIdx + 1, r);
        return array;
    }

    private int partition(int[] a, int l, int r) {
        swap(a, l, l + new Random().nextInt(r - l));
        // we can assume here l < r
        // let choose pivot element as first element of sub array

        // at the end we want this index to point to the first element that is greater than the pivot
        // if there are no elements that are greater than the pivot, it will be pointing to the lastSubIdx + 1
        int pIdx = l + 1;

        // rearrange the subarray from l to r in such a way that
        // it looks like [< p, p, >=p]

        for (int i = l + 1; i <= r; i++) {
            if (a[i] < a[l]) {
                // redundant swaps?
                swap(a, i, pIdx++);
            }
        }

        swap(a, l, --pIdx);
        return pIdx;

        // better understanding of the correctness of the algorithm
    }

    private void swap(int[] a, int l, int r) {
        if (l == r) return;

        int temp = a[l];
        a[l] = a[r];
        a[r] = temp;
    }
}
