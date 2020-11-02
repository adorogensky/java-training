package training.algorithms;

import java.util.Arrays;

public class RehearseMergeSort {

    public int[] sort(int... array) {
        if (array.length == 1) {
            return array;
        }

        // split this array into two halves
        // 0 .. n/2 - 1, n/2 .. n - 1
        int[] leftSortedHalf = sort(Arrays.copyOfRange(array, 0, array.length / 2));
        int[] rightSortedHalf = sort(Arrays.copyOfRange(array, array.length / 2, array.length));

        int leftHalfIdx = 0, rightHalfIdx = 0;

        for (int i = 0; i < array.length; i++) {
            if (leftHalfIdx == leftSortedHalf.length) {
                array[i] = rightSortedHalf[rightHalfIdx++];
            } else if (rightHalfIdx == rightSortedHalf.length) {
                array[i] = leftSortedHalf[leftHalfIdx++];
            } else if (leftSortedHalf[leftHalfIdx] < rightSortedHalf[rightHalfIdx]) {
                array[i] = leftSortedHalf[leftHalfIdx++];
            } else {
                array[i] = rightSortedHalf[rightHalfIdx++];
            }
        }

        return array;
    }
}
