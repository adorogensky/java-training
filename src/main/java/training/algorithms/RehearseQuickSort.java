package training.algorithms;

import java.util.Random;

public class RehearseQuickSort {

    public int[] sort(int... a) {
        return sort(a, 0, a.length - 1);
    }

    private int[] sort(int[] a, int l, int r) {
        if (l >= r) return a;
        int pIdx = partition(a, l, r);
        sort(a, l, pIdx - 1);
        sort(a, pIdx + 1, r);
        return a;
    }

    private int partition(int[] a, int l, int r) {
        swap(a, l, new Random().nextInt(r - l) + l);
        // we want to rearrange the subarray from l+1 to r in a way so that we have
        // all the elements that are less than the pivot followed by the elements that
        // are greater than or equal than the pivot
        // we want to set pIdx to the 1st element that is greater than the pivot
        // therefore incrementing it every time we find an element that is less than the pivot
        // at the end pIdx - 1 will point to the last element that is less than the pivot,
        // we're going to swap it with the leftmost element and return
        int pIdx = l + 1;

        // l = 0, r = 2
        // a[l] is the pivot
        // [1, 0, 0]: a[i] < a[l] where l+1 <= i <= r
        // [1, 1, 1]: a[i] >= a[l] where l+1 <= i <= r
        // [1, 0, 1]
        for (int i = l; i <= r; i++) {
            if (a[i] < a[l]) {
                swap(a, i, pIdx++);
            }
        }

        swap(a, l, --pIdx);
        return pIdx;
    }

    private void swap(int[] a, int l, int r) {
        if (l == r) return;
        int al = a[l];
        a[l] = a[r];
        a[r] = al;
    }
}
