package be.vdab.expotest.domain;

import be.vdab.expotest.exceptions.QtyTicketsNotEnough;

public class Ticket {
    private int juniorDag;
    private int seniorDag;


    public Ticket(int juniorDag, int seniorDag) {
        this.juniorDag = juniorDag;
        this.seniorDag = seniorDag;
    }

    public int getJuniorDag() {
        return juniorDag;
    }

    public int getSeniorDag() {
        return seniorDag;
    }

    public void bestel (int qtyJuniorDag, int qtySeniorDag) {

        if (juniorDag < qtyJuniorDag) {
            throw new QtyTicketsNotEnough("JuniorDag tickets zijn op!");
        }

        if (seniorDag < qtySeniorDag) {
            throw new QtyTicketsNotEnough("SeniorDag tickets zijn op!");
        }

        juniorDag -= qtyJuniorDag;
        seniorDag -= qtySeniorDag;
    }
}