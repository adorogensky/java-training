package training.algorithms;

import java.util.Arrays;

public class ArrayNegativeInversions {

    private static class Result {
        int a[];
        int count;

        Result(int[] a, int count) {
            this.a = a;
            this.count = count;
        }
    }

    public int count(int... a) {
        return sortAndCount(a).count;
    }

    private Result sortAndCount(int[] a) {
        if (a.length == 1) {
            return new Result(a, 0);
        }

        Result lr = sortAndCount(Arrays.copyOfRange(a, 0, a.length / 2));
        Result rr = sortAndCount(Arrays.copyOfRange(a, a.length / 2, a.length));

        int rIdx = 0, lIdx = 0, count = lr.count + rr.count;

        for (int i = 0; i < a.length; i++) {
            if (lIdx == lr.a.length) {
                a[i] = rr.a[rIdx++];
            } else if (rIdx == rr.a.length) {
                a[i] = lr.a[lIdx++];
            } else if (lr.a[lIdx] <= rr.a[rIdx]) {
                a[i] = lr.a[lIdx++];
                count += rr.a.length - rIdx;
            } else {
                a[i] = rr.a[rIdx++];
            }
        }

        return new Result(a, count);
    }
}
