package training.algorithms;

import java.util.Arrays;

public class RehearseArrayInversions {

    private static class R {
        int count;
        int[] array;

        R(int[] array, int count) {
            this.array = array;
            this.count = count;
        }
    }

    public int count(int... a) {
        // an inversion is a pair of indexes (i, j) so that i < j and a[i] > a[j]
        return countAndSort(a).count;
    }

    private R countAndSort(int... a) {
        if (a.length == 1) return new R(a, 0);

        return countAndMerge(
            countAndSort(Arrays.copyOfRange(a, 0, a.length / 2)),
            countAndSort(Arrays.copyOfRange(a, a.length / 2, a.length))
        );
    }

    private R countAndMerge(R r1, R r2) {
        R r = new R(new int[r1.array.length + r2.array.length], r1.count + r2.count);
        int r1Idx = 0, r2Idx = 0;

        for (int i = 0; i < r.array.length; i++) {
            if (r1Idx == r1.array.length) {
                r.array[i] = r2.array[r2Idx++];
            } else if (r2Idx == r2.array.length) {
                r.array[i] = r1.array[r1Idx++];
            } else if (r1.array[r1Idx] > r2.array[r2Idx]) {
                r.array[i] = r2.array[r2Idx++];
                r.count += r1.array.length - r1Idx;
            } else {
                r.array[i] = r1.array[r1Idx++];
            }
        }

        return r;
    }
}
