package training.algorithms;

import java.util.Arrays;

public class RehearseMergeSort {

    public int[] sort(int... a) {
        if (a.length == 1) return a;

        int[] lsa = sort(Arrays.copyOfRange(a, 0, a.length / 2));
        int[] rsa = sort(Arrays.copyOfRange(a, a.length / 2, a.length));

        int lsaIdx = 0, rsaIdx = 0;

        for (int i = 0; i < a.length; i++) {
            if (lsaIdx == lsa.length) {
                a[i] = rsa[rsaIdx++];
            } else if (rsaIdx == rsa.length) {
                a[i] = lsa[lsaIdx++];
            } else if (rsa[rsaIdx] > lsa[lsaIdx]) {
                a[i] = lsa[lsaIdx++];
            } else {
                a[i] = rsa[rsaIdx++];
            }
        }

        return a;
    }
}
