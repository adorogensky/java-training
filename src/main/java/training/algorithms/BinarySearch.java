package training.algorithms;

public class BinarySearch {

    public enum Order {
        ASC, DESC
    }

    public int doSearch(int[] a, int el) {
        Order o = a[0] < a[a.length - 1] ? Order.ASC : Order.DESC;
        return doSearch(a, o, el, 0, a.length - 1);
    }

    // assume a is sorted in ascending or descending order
    public int doSearch(int[] a, Order o, int el, int sIdx, int eIdx) {
        if (sIdx > eIdx) {
            return -1;
        }

        if (sIdx == eIdx) {
            return a[sIdx] == el ? sIdx : -1;
        }

        if (o == Order.ASC && (a[sIdx] > el || a[eIdx] < el)) {
            return -1;
        } else if (o == Order.DESC && (a[sIdx] < el || a[eIdx] > el)) {
            return -1;
        }

        int mIdx = sIdx + (eIdx - sIdx) / 2;

        int lr = doSearch(a, o, el, sIdx, mIdx);
        int rr = doSearch(a, o, el, mIdx + 1, eIdx);

        if (lr > -1) {
            return lr;
        }

        return Math.max(rr, -1);
    }
}
