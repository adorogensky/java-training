package training.challenges;

import org.junit.jupiter.api.Test;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TicketItineraryTests {

    private TicketItinerary ticketItinerary = new TicketItinerary();

    @Test
    public void testOneTicket() {
        Map<String, String> tickets = new LinkedHashMap<>();
        tickets.put("A", "B");

        assertEquals("A -> B", ticketItinerary.print(tickets));
    }

    @Test
    public void testTwoTicketsInOrder() {
        Map<String, String> tickets = new LinkedHashMap<>();
        tickets.put("A", "B");
        tickets.put("B", "C");

        assertEquals("A -> B -> C", ticketItinerary.print(tickets));
    }

    @Test
    public void testThreeTicketsInOrder() {
        Map<String, String> tickets = new LinkedHashMap<>();
        tickets.put("A", "B");
        tickets.put("B", "C");
        tickets.put("C", "D");

        assertEquals("A -> B -> C -> D", ticketItinerary.print(tickets));
    }

    @Test
    public void testTwoTicketsReverseOrder() {
        Map<String, String> tickets = new LinkedHashMap<>();
        tickets.put("B", "C");
        tickets.put("A", "B");

        assertEquals("A -> B -> C", ticketItinerary.print(tickets));
    }

    @Test
    public void testFiveTicketsRandomOrder() {
        Map<String, String> tickets = new LinkedHashMap<>();
        tickets.put("E", "F");
        tickets.put("C", "D");
        tickets.put("A", "B");
        tickets.put("D", "E");
        tickets.put("B", "C");

        assertEquals("A -> B -> C -> D -> E -> F", ticketItinerary.print(tickets));
    }
}
