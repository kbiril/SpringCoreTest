package be.vdab.expotest.repositories;

import be.vdab.expotest.domain.Ticket;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;
@JdbcTest
@Import(TicketRepository.class)
@Sql("/tickets.sql")
class TicketRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String TICKETS = "tickets";
    private final TicketRepository ticketRepository;

    TicketRepositoryTest(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    @Test
    void lockForUpdate() {
        var lockedRecord = ticketRepository.lockForUpdate();
        assertThat(lockedRecord).hasFieldOrPropertyWithValue("juniorDag",10);
        assertThat(lockedRecord).hasFieldOrPropertyWithValue("seniorDag", 15);
    }

    @Test
    void update() {
        var newTicket = new Ticket(1, 1);
        ticketRepository.update(newTicket);
        assertThat(countRowsInTable(TICKETS)).isOne();
        assertThat(countRowsInTableWhere(TICKETS,"juniorDag = 1 and seniorDag = 1")).isOne();
    }
}