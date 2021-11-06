package training.algorithms;

import java.util.Arrays;

public class MergeSort2 {

    public int[] sort(int... array) {
        if (array.length == 1) {
            return array;
        }

        int[] leftHalfSorted = sort(Arrays.copyOfRange(array, 0, array.length / 2));
        int[] rightHalfSorted = sort(Arrays.copyOfRange(array, array.length / 2, array.length));

        int leftHalfIdx = 0, rightHalfIdx = 0;
        for (int i = 0; i < array.length; i++) {
            if (leftHalfIdx == leftHalfSorted.length) {
                array[i] = rightHalfSorted[rightHalfIdx++];
            } else if (rightHalfIdx == rightHalfSorted.length) {
                array[i] = leftHalfSorted[leftHalfIdx++];
            } else if (rightHalfSorted[rightHalfIdx] < leftHalfSorted[leftHalfIdx]) {
                array[i] = rightHalfSorted[rightHalfIdx++];
            } else {
                array[i] = leftHalfSorted[leftHalfIdx++];
            }
        }

        return array;
    }
}
