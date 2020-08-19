package training.challenges;

/**
 * Online test from Gentis Solutions for 84.51 8/19/2020
 */
public class PascalTriangle {

    /*
     * 1
     * 1 1
     * 1 2 1
     * 1 3 3 1
     */
    public String print(int depth) {
        int[][] x = new int[depth][depth];

        StringBuilder triangle = new StringBuilder();

        for (int i = 0; i < depth; i++) {
            for (int j = 0; j <= i; j++) {
                if (j == 0 || j == i) x[i][j] = 1;
                else {
                    x[i][j] = x[i - 1][j - 1] + x[i - 1][j];
                }
                triangle.append(x[i][j]);
                triangle.append(" ");
            }
        }

        return triangle.toString().trim();
    }
}
