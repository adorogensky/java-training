package training.challenges;

import org.junit.jupiter.api.Test;
import training.challenges.PascalTriangle;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PascalTriangleTests {

    PascalTriangle pascalTriangle = new PascalTriangle();

    /*
     depth = 4
        1
       1 1
      1 2 1
     1 3 3 1
     Print: 1 1 1 1 2 1 1 3 3 1
    */
    @Test
    public void test() {
        assertEquals("1 1 1 1 2 1 1 3 3 1", pascalTriangle.print(4));
    }

    /*
     depth = 6
              1
            1   1
          1   2   1
        1   3   3   1
      1   4   6   4   1
    1   5  10   10  5  1
    Print: 1 1 1 1 2 1 1 3 3 1 1 4 6 4 1 1 5 10 10 5 1
    */
    @Test
    public void test2() {
        assertEquals("1 1 1 1 2 1 1 3 3 1 1 4 6 4 1 1 5 10 10 5 1", pascalTriangle.print(6));
    }
}
