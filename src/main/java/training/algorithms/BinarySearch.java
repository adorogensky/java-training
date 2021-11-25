package training.algorithms;

public class BinarySearch {

    public int doSearch(int[] a, int el) {
        return doSearch(a, el, 0, a.length - 1);
    }

    // assume a is sorted in ascending order
    public int doSearch(int[] a, int el, int sIdx, int eIdx) {
        if (sIdx > eIdx) {
            return -1;
        }

        if (sIdx == eIdx) {
            return a[sIdx] == el ? sIdx : -1;
        }

        if (a[sIdx] > el || a[eIdx] < el) {
            return -1;
        }

        int mIdx = sIdx + (eIdx - sIdx) / 2;

        int lr = doSearch(a, el, sIdx, mIdx);
        int rr = doSearch(a, el,mIdx + 1, eIdx);

        if (lr > -1) {
            return lr;
        }

        if (rr > -1) {
            return rr;
        }

        return -1;
    }
}
