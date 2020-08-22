package training.challenges;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class ExcelExpression {

    private final Pattern cellPattern = Pattern.compile("(?<letters>[A-Z]+)(?<numbers>[0-9]+)");

    public List<String> cells(String expression) {
        int indexOfNegate = expression.indexOf('~');

        if (indexOfNegate == -1) return union(expression);

        String inclusiveExpression = expression.substring(0, indexOfNegate);
        String exclusiveExpression = null;

        if (indexOfNegate < expression.length() - 2) {
            exclusiveExpression = expression.substring(indexOfNegate + 1);
        }

        List<String> cells = new ArrayList<>(
            union(inclusiveExpression)
        );

        if (exclusiveExpression != null) {
            cells.removeAll(union(exclusiveExpression));
        }

        return cells;
    }

    private List<String> union(String expression) {
        return Stream.of(
            expression.split("&")
        ).flatMap(
            this::range
        ).collect(Collectors.toList());
    }

    private Stream<String> range(String expression) {
        if ("".equals(expression)) return Stream.empty();
        String[] cellRange = expression.split(":", -1);

        if (cellRange.length == 1) return Stream.of(expression);

        if (cellRange.length > 2) throw new IllegalArgumentException();

        String rangeLow = cellRange[0];
        String rangeHigh = cellRange[1];

        Matcher rangeLowMatcher = cellPattern.matcher(rangeLow);
        Matcher rangeHighMatcher = cellPattern.matcher(rangeHigh);

        if (!rangeLowMatcher.find()) throw new IllegalArgumentException();
        char rangeLowCellLetter = rangeLowMatcher.group("letters").charAt(0);
        int rangeLowCellNumber = Integer.parseInt(rangeLowMatcher.group("numbers"));

        if (!rangeHighMatcher.find()) throw new IllegalArgumentException();
        char rangeHighCellLetter = rangeHighMatcher.group("letters").charAt(0);
        int rangeHighCellNumber = Integer.parseInt(rangeHighMatcher.group("numbers"));

        return IntStream.range(
            min(rangeLowCellLetter, rangeHighCellLetter),
            max(rangeLowCellLetter, rangeHighCellLetter) + 1
        ).mapToObj(
            letter -> (char) letter
        ).flatMap(
            letter -> IntStream.range(
                min(rangeLowCellNumber, rangeHighCellNumber),
                max(rangeLowCellNumber, rangeHighCellNumber) + 1
            ).mapToObj(
                number -> letter + "" + number
            )
        );
    }
}

