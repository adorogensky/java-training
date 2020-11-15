package training.algorithms;

import java.util.Arrays;

public class RehearseMergeSort {

    public int[] sort(int... a) {
        if (a.length == 1) return a;

        int[] la = sort(Arrays.copyOfRange(a, 0, a.length / 2));
        int[] ra = sort(Arrays.copyOfRange(a, a.length / 2, a.length - 1));

        merge(a, la, ra);
        return a;
    }

    private void merge(int[] a, int[] la, int[] ra) {
        int laIdx = 0, raIdx = 0;

        for (int i = 0; i < a.length; i++) {
            if (laIdx == la.length) {
                a[i] = ra[raIdx++];
            } else if (raIdx == ra.length) {
                a[i] = la[laIdx++];
            } else if (ra[raIdx] < la[laIdx]) {
                a[i] = ra[raIdx++];
            } else {
                a[i] = la[laIdx++];
            }
        }
    }
}
