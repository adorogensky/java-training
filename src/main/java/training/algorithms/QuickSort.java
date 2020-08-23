package training.algorithms;

public class QuickSort {

    public int[] sort(int... array) {
        return sort(array, 0, array.length - 1);
    }

    private int[] sort(int[] array, int leftIdx, int rightIdx) {
        if (leftIdx >= rightIdx) return array;
        int pivotIdx = partition(array, leftIdx, rightIdx);
        sort(array, leftIdx, pivotIdx - 1);
        sort(array, pivotIdx + 1, rightIdx);
        return array;
    }

    private int partition(int[] array, int leftIdx, int rightIdx) {
        int pivot = array[leftIdx];
        int partitionIdx = leftIdx + 1;

        for (int unseenElementIdx = leftIdx + 1; unseenElementIdx <= rightIdx; unseenElementIdx++) {
            if (array[unseenElementIdx] < pivot) {
                int elementAtPartitionIdx = array[partitionIdx];
                array[partitionIdx++] = array[unseenElementIdx];
                array[unseenElementIdx] = elementAtPartitionIdx;
            }
        }

        partitionIdx--;

        array[leftIdx] = array[partitionIdx];
        array[partitionIdx] = pivot;

        return partitionIdx;
    }
}
