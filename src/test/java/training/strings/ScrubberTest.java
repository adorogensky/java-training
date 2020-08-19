package training.strings;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ScrubberTest {

    private Scrubber scrubber = new Scrubber();

    @Test
    public void test() {
        assertEquals("hll wrld", scrubber.scrub("hello world, eo"));
    }

    @Test
    public void test2() {
        assertEquals("lue ar", scrubber.scrub("blue car, abc"));
    }
}
