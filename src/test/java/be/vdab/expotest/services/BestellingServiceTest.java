package be.vdab.expotest.services;

import be.vdab.expotest.domain.Bestelling;
import be.vdab.expotest.repositories.BestellingRepository;
import be.vdab.expotest.repositories.TicketRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
@Import({BestellingService.class, TicketRepository.class, BestellingRepository.class})
@Sql({"/tickets.sql", "/bestellingen.sql"})
class BestellingServiceIntegrationTest extends AbstractTransactionalJUnit4SpringContextTests {
    private static final String BESTELLINGEN = "bestellingen";
    private static final String TICKETS = "tickets";
    private final BestellingService bestellingService;

    BestellingServiceIntegrationTest(BestellingService bestellingService) {
        this.bestellingService = bestellingService;
    }

    @Test
    void bestel() {
        var id = bestellingService.bestel(new Bestelling("test4",3));
        assertThat(id).isPositive();
        assertThat(countRowsInTableWhere(
                BESTELLINGEN, "id = " + id + " and naam = 'test4' and ticketType = 3")).isOne();

        assertThat(countRowsInTable(TICKETS)).isOne();
        assertThat(countRowsInTableWhere(TICKETS, "juniorDag = 9 and seniorDag = 14")).isOne();
    }

    @Test
    void bestellingVanMetOngeldigeTypeTicketMislukt () {
        assertThatIllegalArgumentException().isThrownBy(
                () -> bestellingService.bestel(new Bestelling("test5", -2))
        );
    }

    @Test
    void bestellingMetLegeNaamMislukt() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> bestellingService.bestel(new Bestelling("", 1))
        );
    }
    @Test
    void bestellingMetNullAlsNaamMislukt() {
        assertThatNullPointerException().isThrownBy(
                () -> bestellingService.bestel(new Bestelling(null, 2))
        );
    }

}