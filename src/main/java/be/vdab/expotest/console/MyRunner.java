package be.vdab.expotest.console;

import be.vdab.expotest.domain.Bestelling;
import be.vdab.expotest.exceptions.QtyTicketsNotEnough;
import be.vdab.expotest.services.BestellingService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MyRunner implements CommandLineRunner {
    private final BestellingService bestellingService;

    public MyRunner(BestellingService bestellingService) {
        this.bestellingService = bestellingService;
    }

    @Override
    public void run(String... args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Naam:");
        var naam = scanner.nextLine();

        System.out.println("Type ticket, kies 1, 2 of 3:");
        var ticketType = scanner.nextInt();

        try {
            var bestelling = new Bestelling(naam, ticketType);
            System.out.println("Uw bestelling heeft nummer: " + bestellingService.bestel(bestelling));
        } catch (IllegalArgumentException ex) {
            System.err.println(ex.getMessage());;
        } catch (QtyTicketsNotEnough ex) {
            System.err.println(ex.getMessage());
        }
    }
}