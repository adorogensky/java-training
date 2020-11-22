package training.algorithms;

import java.util.Arrays;

public class RehearseMergeSort {

    public int[] sort(int... a) {
        if (a.length == 1) return a;

        // [1, 7, 9, 2] = merge(sort[1, 7], sort[9, 2]) = merge([1, 7], [2, 9]) = [1, 2, 7, 9]
        // sort[1, 7] = merge(sort[1], sort[7]) = [1, 7]
        // sort[9, 2] = merge(sort[2], sort[9]) = [2, 9]

        return merge(
            a,
            sort(Arrays.copyOfRange(a, 0, a.length / 2)),
            sort(Arrays.copyOfRange(a, a.length/2, a.length))
        );
    }

    int[] merge(int[] c, int[] a, int[] b) {
        // a, b are two sorted arrays of length n and m
        // then sorted array c that contains all elements of a and b
        // can be created with a linear time O(n)
        int aIdx = 0, bIdx = 0;
        for (int i = 0; i < c.length; i++) {
            if (aIdx == a.length) {
                c[i] = b[bIdx++];
            } else if (bIdx == b.length) {
                c[i] = a[aIdx++];
            } else if (a[aIdx] < b[bIdx]) {
                c[i] = a[aIdx++];
            } else {
                c[i] = b[bIdx++];
            }
        }

        return c;
    }
}
