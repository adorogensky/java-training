package training.algorithms;

public class QuickSortAsc {

    public int[] sort(int... array) {
        return sort(array, 0, array.length - 1);
    }

    private int[] sort(int[] array, int leftIdx, int rightIdx) {
        if (leftIdx >= rightIdx) return array;
        int partitionIdx = partition(array, leftIdx, rightIdx);
        sort(array, leftIdx, partitionIdx - 1);
        sort(array, partitionIdx + 1, rightIdx);
        return array;
    }

    private int partition(int[] array, int leftIdx, int rightIdx) {
        int pivot = array[leftIdx];
        int pivotIdx = leftIdx + 1;

        for (int i = pivotIdx; i <= rightIdx; i++) {
            if (array[i] < pivot) {
                int elementAtPivotIdx = array[pivotIdx];
                array[pivotIdx++] = array[i];
                array[i] = elementAtPivotIdx;
            }
        }

        pivotIdx--;

        array[leftIdx] = array[pivotIdx];
        array[pivotIdx] = pivot;

        return pivotIdx;
    }
}
