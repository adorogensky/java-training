package training.algorithms;

import java.util.Random;

public class RehearseQuickSort {

    public int[] sort(int... a) {
        return sort(a, 0, a.length - 1);
    }

    private int[] sort(int[] a, int l, int r) {
        if (l >= r) return a;

        // assume l < r
        int pIdx = partition(a, l, r);
        sort(a, 0, pIdx - 1);
        sort(a, pIdx + 1, r);
        return a;
    }

    private int partition(int[] a, int l, int r) {
        swap(a, l, new Random().nextInt(r - l) + l);
        // a[l] as pivot
        // pIdx should point to the first element that is greater than the pivot in the rearranged array
        int pIdx = l + 1;
        // [3, 1, 5, 2, 2, 8]
        for (int i = l + 1; i <= r; i++) {
            if (a[i] < a[l]) {
                swap(a, i, pIdx++);
            }
        }

        swap(a, l, --pIdx);
        return pIdx;
    }

    private void swap(int[] a, int l, int r) {
        if (l == r) return;
        int t = a[l];
        a[l] = a[r];
        a[r] = t;
    }
}
