package be.vdab.expotest.domain;

import be.vdab.expotest.exceptions.QtyTicketsNotEnough;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

class TicketTest {
    private Ticket ticket;
    @BeforeEach
    void beforeEach() {
        ticket = new Ticket(10, 15);
    }
    @Test
    void bestelLukt() {
        ticket.bestel(1,2);
        assertThat(ticket.getJuniorDag()).isEqualTo(9);
        assertThat(ticket.getSeniorDag()).isEqualTo(13);
    }

    @Test
    void bestelLuktBijNetGenoegTickets() {
        ticket.bestel(10,15);
        assertThat(ticket.getJuniorDag()).isEqualTo(0);
        assertThat(ticket.getSeniorDag()).isEqualTo(0);
    }

    @Test
    void bestelMisluktBijOnvoldoendeJuniorDag() {
        assertThatExceptionOfType(QtyTicketsNotEnough.class).isThrownBy(
                () -> ticket.bestel(11,1)
        );
    }

    @Test
    void bestelMisluktBijOnvoldoendeSeniorDag() {
        assertThatExceptionOfType(QtyTicketsNotEnough.class).isThrownBy(
                () -> ticket.bestel(1,16)
        );
    }
}