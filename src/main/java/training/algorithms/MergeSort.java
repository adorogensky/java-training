package training.algorithms;

public class MergeSort {

    public int[] sort(int... array) {
        if (array.length == 1) return array;
        else if (array[0] > array[1]) {
            int array0 = array[0];
            array[0] = array[1];
            array[1] = array0;
        }
        return array;
    }
}
