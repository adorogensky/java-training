package training.algorithms;

import java.util.Arrays;

public class MergeSort3 {

    public int[] sort(int... array) {
        if (array.length == 1) return array;

        int[] leftHalfSorted = sort(Arrays.copyOfRange(array, 0, array.length / 2));
        int[] rightHalfSorted = sort(Arrays.copyOfRange(array, array.length / 2, array.length));

        int leftIdx = 0, rightIdx = 0;

        for (int i = 0; i < array.length; i++) {
            if (leftIdx == leftHalfSorted.length) {
                array[i] = rightHalfSorted[rightIdx++];
            } else if (rightIdx == rightHalfSorted.length) {
                array[i] = leftHalfSorted[leftIdx++];
            } else if (rightHalfSorted[rightIdx] < leftHalfSorted[leftIdx]) {
                array[i] = rightHalfSorted[rightIdx++];
            } else {
                array[i] = leftHalfSorted[leftIdx++];
            }
        }

        return array;
    }
}
