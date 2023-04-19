package be.vdab.expotest.domain;

public class Bestelling {
    private final int id;
    private final String naam;
    private final int ticketType;

    public Bestelling(int id, String naam, int ticketType) {
        this.id = id;
        this.naam = naam;
        this.ticketType = ticketType;
    }

    public Bestelling(String naam, int ticketType) {

        if (naam.isBlank()) throw new IllegalArgumentException("De naam mag niet leeg zijn!");
        if (ticketType != 1 && ticketType != 2 && ticketType != 3) {
            throw new IllegalArgumentException("Geen geldige ticket type: moet 1, 2 of 3 zijn");
        }
        this.id = 0;
        this.naam = naam;
        this.ticketType = ticketType;
    }

    public int getId() {
        return id;
    }

    public String getNaam() {
        return naam;
    }

    public int getTicketType() {
        return ticketType;
    }
}