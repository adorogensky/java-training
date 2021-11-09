package training.algorithms;

import java.util.Arrays;

public class MergeSortDesc {

    public int[] sort(int... a) {
        if (a.length == 1) return a;

        int[] lhs = sort(Arrays.copyOfRange(a, 0, a.length / 2));
        int[] rhs = sort(Arrays.copyOfRange(a, a.length / 2, a.length));

        int lIdx = 0, rIdx = 0;

        for (int i = 0; i < a.length; i++) {
            if (lIdx == lhs.length) {
                a[i] = rhs[rIdx++];
            } else if (rIdx == rhs.length) {
                a[i] = lhs[lIdx++];
            } else if (lhs[lIdx] > rhs[rIdx]) {
                a[i] = lhs[lIdx++];
            } else {
                a[i] = rhs[rIdx++];
            }
        }

        return a;
    }
}
