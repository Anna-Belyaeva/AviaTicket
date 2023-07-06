import java.util.Comparator;

public class TicketTimeComparator implements Comparator<Ticket> {

    @Override
    public int compare(Ticket t1, Ticket t2) {
        int timeFlight1 = t1.getTimeTo() - t1.getTimeFrom();
        int timeFlight2 = t2.getTimeTo() - t2.getTimeFrom();

        if (timeFlight1 < timeFlight2) {
            return -1;
        } else if (timeFlight1 > timeFlight2) {
            return 1;
        } else {
            return 0;
        }
    }
}