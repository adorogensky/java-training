package training.algorithms;

public class QuickSort2 {

    public int[] sort(int... array) {
        return sort(array, 0, array.length - 1);
    }

    private int[] sort(int[] array, int startIdx, int endIdx) {
        if (startIdx >= endIdx) return array;
        int pivotIdx = partition(array, startIdx, endIdx);
//        sort(array, 0, pivotIdx - 1);
//        sort(array, pivotIdx + 1, array.length - 1);
        return array;
    }

    // endIdx > startIdx
    // length = endIdx - startIdx + 1
    // midIdx = startIdx + (endIdx - startIdx + 1)/2
    // 0..3, midIdx = 0 + 2 = 2
    private int partition(int[] array, int startIdx, int endIdx) {
        int length = endIdx - startIdx + 1;
        int pIdx = startIdx + (length / 2);
        int rightIdx = pIdx + 1;
        int i = startIdx;

        while(i < pIdx) {
            if (rightIdx > endIdx) break;
            if (array[i] > array[pIdx]) {
                swap(array, i++, rightIdx++);
            }
        }

        if (i < pIdx) {
            swap(array, i, pIdx);
            pIdx = i + 1;
        }

        return pIdx;
    }

    private void swap(int[] array, int i, int j) {
        int aj = array[j];
        array[j] = array[i];
        array[i] = aj;
    }
}
