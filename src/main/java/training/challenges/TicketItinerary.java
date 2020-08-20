package training.challenges;

import java.util.*;
import java.util.stream.Collectors;

/**
 * In-person interview code challenge from Apple, 7/2020
 * 
 * You're given a list of tickets from "source" to "destination"
 * so that the 1st ticket's destination matches the 2nd ticket's source and so on
 * E.g. A -> B, B -> C, C -> D
 * Valid input assumes that the 1st ticket's source and the last ticket's destination
 * dont match any other tickets' source or destination.
 *
 * You need to print out an "itinerary" A -> B -> C -> D
 * as the tickets can be given to you in random order
 * e.g. B -> C, A -> B, C -> D
 */
public class TicketItinerary {

    String print(Map<String, String> tickets) {
        if (tickets.isEmpty()) return "";

        LinkedList<String> itinerary = new LinkedList<>();

        Map<String, String> reverseTickets = tickets.entrySet().stream().collect(
            Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey)
        );

        buildItinerary(itinerary, tickets, reverseTickets);
        return String.join(" -> ", itinerary);
    }

    /*
        Recursive implementation
     */
    private void buildItinerary(LinkedList<String> itinerary, Map<String, String> tickets, Map<String, String> reverseTickets) {
        if (itinerary.size() == tickets.size() + 1) return;

        if (itinerary.isEmpty()) {
            Map.Entry<String, String> ticket = tickets.entrySet().iterator().next();
            itinerary.add(ticket.getKey());
            itinerary.add(ticket.getValue());
        }

        String nextTicketDestOnRight = tickets.get(itinerary.getLast());
        String prevTicketSrcOnLeft = reverseTickets.get(itinerary.getFirst());

        if (nextTicketDestOnRight != null) {
            itinerary.add(nextTicketDestOnRight);
            buildItinerary(itinerary, tickets, reverseTickets);
        } else if (prevTicketSrcOnLeft != null) {
            itinerary.addFirst(prevTicketSrcOnLeft);
            buildItinerary(itinerary, tickets, reverseTickets);
        }
    }

    /*
        Iterative implementation
     */
    private void buildItinerary2(LinkedList<String> itinerary, Map<String, String> tickets, Map<String, String> reverseTickets) {
        Map.Entry<String, String> start = tickets.entrySet().stream().filter(
            mapEntry -> reverseTickets.get(mapEntry.getKey()) == null
        ).findFirst().orElseThrow(
            () -> new RuntimeException("Invalid input")
        );

        itinerary.add(start.getKey());
        itinerary.add(start.getValue());

        String currentDest = start.getValue();

        while (tickets.get(currentDest) != null) {
            currentDest = tickets.get(currentDest);
            itinerary.add(currentDest);
        }
    }
}
