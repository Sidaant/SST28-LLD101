import com.example.tickets.IncidentTicket;
import com.example.tickets.TicketService;

import java.util.List;

/**
 * Starter demo that shows why mutability is risky.
 *
 * After refactor:
 * - direct mutation should not compile (no setters)
 * - external modifications to tags should not affect the ticket
 * - service "updates" should return a NEW ticket instance
 */
public class TryIt {

    public static void main(String[] args) {
        TicketService service = new TicketService();

        IncidentTicket t = service.createTicket("TCK-1001", "reporter@example.com", "Payment failing on checkout");
        System.out.println("Created: " + t);

        // Service "updates" return a NEW ticket instance
        t = service.assign(t, "agent@example.com");
        t = service.escalateToCritical(t);
        System.out.println("\nAfter service updates (new instance returned): " + t);

        // Attempting external mutation should fail
        List<String> tags = t.getTags();
        try {
            tags.add("HACKED_FROM_OUTSIDE");
        } catch (UnsupportedOperationException e) {
            System.out.println("\nSuccessfully prevented external tag mutation: " + e);
        }
        
        System.out.println("\nFinal Ticket state: " + t);
    }
}
