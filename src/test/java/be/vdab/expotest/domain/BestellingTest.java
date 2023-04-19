package be.vdab.expotest.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.Assertions.assertThatNullPointerException;

class BestellingTest {
    @Test void bestellingDieLukt() {
        new Bestelling("Katia", 1);
    }
    @Test void naamMoetIngevuldZijn() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Bestelling("", 1)
        );
    }

    @Test void naamMagNietNullZijn() {
        assertThatNullPointerException().isThrownBy(
                () -> new Bestelling(null, 1)
        );
    }

    @Test void typeTicketMoetOf1Of2Of3Zijn() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Bestelling("Katia", -1)
        );
    }

}