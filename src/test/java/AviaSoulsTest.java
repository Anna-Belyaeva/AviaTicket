import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class AviaSoulsTest {
    public AviaSouls souls = new AviaSouls();
    Ticket ticket1 = new Ticket("Москва",
            "Сочи",
            25_000,
            10,
            12
    );

    Ticket ticket2 = new Ticket("Иркутск",
            "Новосибирск",
            15_000,
            11,
            15
    );

    Ticket ticket3 = new Ticket("Москва",
            "Сочи",
            10_000,
            10,
            14
    );

    Ticket ticket4 = new Ticket("Иркутск",
            "Новосибирск",
            105_000,
            10,
            21
    );

    Ticket ticket5 = new Ticket("Москва",
            "Анапа",
            13_500,
            11,
            14
    );

    Ticket ticket6 = new Ticket("Москва",
            "Сочи",
            15_000,
            10,
            13
    );

    @Test
    public void shouldComparePositive() {

        Assertions.assertEquals(1, ticket4.compareTo(ticket3));
    }

    @Test
    public void shouldCompareNegative() {

        Assertions.assertEquals(-1, ticket5.compareTo(ticket1));
    }

    @Test
    public void shouldCompareEquals() {

        Assertions.assertEquals(0, ticket2.compareTo(ticket6));
    }

    @Test
    public void shouldSearchAFewTickets() {
        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);
        souls.add(ticket5);
        souls.add(ticket6);

        Ticket[] expected = {ticket3, ticket6, ticket1};
        Ticket[] actual = souls.search("Москва", "Сочи");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSearch1Ticket() {
        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);
        souls.add(ticket5);
        souls.add(ticket6);

        Ticket[] expected = {ticket5};
        Ticket[] actual = souls.search("Москва", "Анапа");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void NotShouldSearchTicket() {
        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);
        souls.add(ticket5);
        souls.add(ticket6);

        Ticket[] expected = {};
        Ticket[] actual = souls.search("Сочи", "Анапа");

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCompareTicketTime() {
        TicketTimeComparator timeComparator = new TicketTimeComparator();

        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);
        souls.add(ticket5);
        souls.add(ticket6);

        Arrays.sort(souls.findAll(), timeComparator);

        Ticket[] expected = {ticket1, ticket5, ticket6, ticket2, ticket3, ticket4};
        Ticket[] actual = souls.findAll();

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSortAFewTicketsWithComparator() {

        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);
        souls.add(ticket5);
        souls.add(ticket6);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket1, ticket6, ticket3};
        Ticket[] actual = souls.searchAndSortBy("Москва", "Сочи", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void NotShouldSortTicketsWithComparator() {

        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);
        souls.add(ticket5);
        souls.add(ticket6);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {};
        Ticket[] actual = souls.searchAndSortBy("Москва", "Тольятти", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }

    @Test
    public void ShouldSort1TicketWithComparator() {

        souls.add(ticket1);
        souls.add(ticket2);
        souls.add(ticket3);
        souls.add(ticket4);
        souls.add(ticket5);
        souls.add(ticket6);

        Comparator<Ticket> comparator = new TicketTimeComparator();

        Ticket[] expected = {ticket5};
        Ticket[] actual = souls.searchAndSortBy("Москва", "Анапа", comparator);

        Assertions.assertArrayEquals(expected, actual);
    }
}
