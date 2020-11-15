package training.algorithms;

import java.util.Random;

public class RehearseQuickSort {

    public int[] sort(int... a) {
        return sort(a, 0, a.length - 1);
    }

    private int[] sort(int[] a, int l, int r) {
        if (l >= r) return a;

        // we're assuming here that l < r
        int pIdx = partition(a, l, r);
        sort(a, 0, pIdx - 1);
        return sort(a, pIdx + 1, r);
    }

    private int partition(int[] a, int l, int r) {
        // assume l < r
        swap(a, l, new Random().nextInt(r - l) + l);
        // we want to start rearranging the sub array a
        // from l + 1 to r so that we build the sequence of elements that
        // are less then than the pivot and then the sequence of elements that
        // are greater than or equals to the pivot,
        // tracking the index pIdx, so that pIdx - 1 points to the latest element
        // that is less than the pivot after we're done rearranging the sub array
        // then we will swap(a, l, pIdx - 1)
        int pIdx = l + 1;

        // l = 0
        // [*2, 1, 0, 0] pIdx = 4 => [1, 0, 0, 2]
        // [*2, 4, 3, 2] pIdx = 1 => [2, 4, 3, 2]
        // [*2, 1, 4, 0] pIdx = 3 => [0, 1, 2, 4]

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
        int ll = a[l];
        a[l] = a[r];
        a[r] = ll;
    }
}
