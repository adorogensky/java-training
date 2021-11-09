package training.algorithms;

public class QuickSortDesc {

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

    private int partition(int[] a, int sIdx, int eIdx) {
        int p = a[sIdx];
        int clp = 0;
        for (int i = sIdx + 1; i <= eIdx; i++) {
            if (a[i] >= p) {
                swap(a, i, sIdx + ++clp);
            }
        }

        int pIdx = sIdx + clp;
        swap(a, sIdx, pIdx);

        return pIdx;
    }

    private void swap(int[] a, int i, int j) {
        if (i == j) return;
        int aj = a[j];
        a[j] = a[i];
        a[i] = aj;
    }
}
