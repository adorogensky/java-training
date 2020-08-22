package training.challenges;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Given an Excel expression which contains union, range and negate operands,
 * return a list of cells that this expression computes to. Cells could be A1 to ZZZ999.
 * E.g.
 * "A1" => ["A1"]
 * "A1:A2" => ["A1", "A2"]
 * "A2:A1" => ["A1, "A2"]
 * "A1:A2:A3" => not valid
 * "A1&A2" => ["A1", "A2"]
 * "A1:A3&~A2" => ["A1, "A3"] (~ can be used only one in the expression)
 * "A1:A3&~A1:A2" => ["A3"]
 */
public class ExcelExpressionTests {

    private final ExcelExpression excelExpression = new ExcelExpression();

    @Test
    public void emptyExpression_shouldReturn_emptyList() {
        assertTrue(excelExpression.cells("").isEmpty());
    }

    @Test
    public void solitaryNegateOperator_shouldReturn_emptyList() {
        assertTrue(excelExpression.cells("~").isEmpty());
    }

    @Test
    public void singleCell_shouldReturn_thatCell() {
        assertArrayEquals(new String[] { "A1" }, excelExpression.cells("A1").toArray());
    }

    @Test
    public void singleCellRange_shouldReturn_thatCell() {
        assertArrayEquals(new String[] { "A1" }, excelExpression.cells("A1:A1").toArray());
    }

    @Test
    public void range_shouldReturn_cellsInRange() {
        assertArrayEquals(new String[] { "A1", "A2", "A3" }, excelExpression.cells("A1:A3").toArray());
    }

    @Test
    public void reverseRange_shouldReturn_cellsInRange() {
        assertArrayEquals(new String[] { "A1", "A2", "A3" }, excelExpression.cells("A3:A1").toArray());
    }

    @Test
    public void multiRowRange_shouldReturn_cellsInRange() {
        assertArrayEquals(
            new String[] { "A1", "A2", "B1", "B2" },
            excelExpression.cells("A1:B2").toArray()
        );
    }

    @Test
    public void threeWayRange_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> excelExpression.cells("A1:A1:A1"));
    }

    @Test
    public void undefinedLowRange_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> excelExpression.cells(":A1"));
    }

    @Test
    public void undefinedHighRange_shouldThrowException() {
        assertThrows(IllegalArgumentException.class, () -> excelExpression.cells("A1:"));
    }

    @Test
    public void singleCellUnion_shouldReturn_cellsInUnion() {
        assertArrayEquals(
            new String[] { "A1", "A2" },
            excelExpression.cells("A1&A2").toArray()
        );
    }

    @Test
    public void rangeAndSingleCellUnion_shouldReturn_cellsInUnion() {
        assertArrayEquals(
            new String[] { "A1", "A2", "A3" },
            excelExpression.cells("A1:A2&A3").toArray()
        );
    }

    @Test
    public void rangeAndNegativeSingleCell_shouldReturn_cellsInRangeExceptThatNegativeSingleCell() {
        assertArrayEquals(
            new String[] { "A1", "A3" },
            excelExpression.cells("A1:A3~A2").toArray()
        );
    }

    @Test
    public void rangeUnionWithNegativeRange_shouldReturn_cellsInRangeExceptCellsInNegativeRange() {
        assertArrayEquals(
            new String[] { "A3" },
            excelExpression.cells("A1:A3&~A1:A2").toArray()
        );
    }
}