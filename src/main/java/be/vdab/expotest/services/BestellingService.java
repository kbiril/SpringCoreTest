package be.vdab.expotest.services;

import be.vdab.expotest.domain.Bestelling;
import be.vdab.expotest.repositories.BestellingRepository;
import be.vdab.expotest.repositories.TicketRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class BestellingService {
    private final TicketRepository ticketRepository;
    private final BestellingRepository bestellingRepository;

    public BestellingService(TicketRepository ticketRepository, BestellingRepository bestellingRepository) {
        this.ticketRepository = ticketRepository;
        this.bestellingRepository = bestellingRepository;
    }

    @Transactional
    public int bestel(Bestelling bestelling) {
        int qtyJuniorDag = 0;
        int qtySeniorDag = 0;

        var ticket = ticketRepository.lockForUpdate();

        switch (bestelling.getTicketType()) {
            case 1 -> qtyJuniorDag = 1;
            case 2 -> qtySeniorDag = 1;
            case 3 -> {
                qtyJuniorDag = 1;
                qtySeniorDag = 1;
            }
            default -> throw new IllegalArgumentException("Ticket type is 1, 2 of 3!");
        }

        ticket.bestel(qtyJuniorDag, qtySeniorDag);
        ticketRepository.update(ticket);

        return bestellingRepository.create(bestelling);
    }
}
