package training.algorithms;

public class QuickSort2 {

    public int[] sort(int... a) {
        return sort(a, 0, a.length - 1);
    }

    private int[] sort(int[] a, int sIdx, int eIdx) {
        if (sIdx >= eIdx) return a;
        int pIdx = partition(a, sIdx, eIdx);
        sort(a, sIdx, pIdx - 1);
        sort(a, pIdx + 1, eIdx);
        return a;
    }

    // sIdx - start index
    // eIdx - end index
    // pIdx - pivot element index
    // p - pivot element
    // llpIdx - last less that the pivot element index
    private int partition(int[] a, int sIdx, int eIdx) {
        int pIdx = sIdx;
        int p = a[pIdx];
        int llpIdx = sIdx + 1;
        for (int i = sIdx + 1; i <= eIdx; i++) {
            if (a[i] < p) {
                swap(a, i, llpIdx++);
            }
        }

        return pIdx;
    }

    private void swap(int[] a, int lIdx, int rIdx) {
        if (lIdx == rIdx) return;
        int lEl = a[lIdx];
        a[lIdx] = a[rIdx];
        a[rIdx] = lEl;
    }
}
