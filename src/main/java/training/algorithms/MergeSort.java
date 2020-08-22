package training.algorithms;

import java.util.Arrays;

public class MergeSort {

    public int[] sort(int... array) {
        if (array.length == 1) return array;
        else if (array.length > 1) {
            int[] leftHalf = sort(Arrays.copyOfRange(array, 0, array.length / 2));
            int[] rightHalf = sort(Arrays.copyOfRange(array, array.length / 2, array.length));
            int leftHalfIdx = 0, rightHalfIdx = 0;

            for (int i = 0; i < array.length; i++) {
                if (leftHalfIdx == leftHalf.length) {
                    array[i] = rightHalf[rightHalfIdx++];
                } else if (rightHalfIdx == rightHalf.length) {
                    array[i] = leftHalf[leftHalfIdx++];
                } else if (leftHalf[leftHalfIdx] > rightHalf[rightHalfIdx]) {
                    array[i] = rightHalf[rightHalfIdx++];
                } else {
                    array[i] = leftHalf[leftHalfIdx++];
                }
            }
        }
        return array;
    }
}
