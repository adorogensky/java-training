package training.algorithms;

public class MergeSort2 {

    public int[] sort(int... array) {
        return sort(array, 0, array.length);
    }

    private int[] sort(int[] array, int startIdx, int endIdx) {
        if (startIdx >= endIdx) {
            return array;
        }

        int[] leftSortedArray = sort(array, 0, array.length / 2);
        int[] rightSortedArray = sort(array, array.length / 2, array.length);

        int leftArrayIdx = 0;
        int rightArrayIdx = 0;

        for (int i = 0; i < array.length; i++) {
            if (leftArrayIdx == leftSortedArray.length) {
                array[i] = rightSortedArray[rightArrayIdx++];
            } else if (rightArrayIdx == rightSortedArray.length) {
                array[i] = leftSortedArray[leftArrayIdx++];
            } else if (rightSortedArray[rightArrayIdx] < leftSortedArray[leftArrayIdx]) {
                array[i] = rightSortedArray[rightArrayIdx++];
            } else {
                array[i] = leftSortedArray[leftArrayIdx++];
            }
        }

        return array;
    }
}
