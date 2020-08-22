package training.algorithms;

import static java.util.Arrays.copyOfRange;

public class ArrayInversions {

    private static class InversionResult {
        private final int[] array;
        private final int inversionsCount;

        InversionResult(int[] array, int inversionsCount) {
            this.array = array;
            this.inversionsCount = inversionsCount;
        }
    }

    public int count(int... array) {
        return sortAndCount(array).inversionsCount;
    }

    private InversionResult sortAndCount(int[] array) {
        if (array.length > 1) {
            int middleIdx = array.length / 2;
            InversionResult sortedLeftHalfInversionResult = sortAndCount(copyOfRange(array, 0, middleIdx));
            InversionResult sortedRightHalfInversionsResult = sortAndCount(copyOfRange(array, middleIdx, array.length));

            int[] sortedLeftHalf = sortedLeftHalfInversionResult.array;
            int[] sortedRightHalf = sortedRightHalfInversionsResult.array;
            int sortedLeftHalfIdx = 0, sortedRightHalfIdx = 0;

            int inversionsCount = 0;

            int[] sortedArray = new int[array.length];
            for (int i = 0; i < sortedArray.length; i++) {
                if (sortedLeftHalfIdx == sortedLeftHalf.length) {
                    sortedArray[i] = sortedRightHalf[sortedRightHalfIdx++];
                } else if (sortedRightHalfIdx == sortedRightHalf.length) {
                    sortedArray[i] = sortedLeftHalf[sortedLeftHalfIdx++];
                } else if (sortedLeftHalf[sortedLeftHalfIdx] > sortedRightHalf[sortedRightHalfIdx]) {
                    inversionsCount += (sortedLeftHalf.length - sortedLeftHalfIdx);
                    sortedArray[i] = sortedRightHalf[sortedRightHalfIdx++];
                } else {
                    sortedArray[i] = sortedLeftHalf[sortedLeftHalfIdx++];
                }
            }

            return new InversionResult(
                sortedArray,
                inversionsCount +
                sortedLeftHalfInversionResult.inversionsCount +
                sortedRightHalfInversionsResult.inversionsCount
            );
        }

        return new InversionResult(array, 0);
    }
}