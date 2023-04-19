package be.vdab.expotest.repositories;

import be.vdab.expotest.domain.Bestelling;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Import(BestellingRepository.class)
@Sql("/bestellingen.sql")
class BestellingRepositoryTest extends AbstractTransactionalJUnit4SpringContextTests {
    private final static String BESTELLINGEN = "bestellingen";
    private final BestellingRepository bestellingRepository;

    BestellingRepositoryTest(BestellingRepository bestellingRepository) {
        this.bestellingRepository = bestellingRepository;
    }

    @Test
    void create() {
        var id = bestellingRepository.create(new Bestelling("test4",2));
        assertThat(id).isPositive();
        assertThat(countRowsInTableWhere(BESTELLINGEN, "id = " + id)).isOne();
    }
}