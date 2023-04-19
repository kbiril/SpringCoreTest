package be.vdab.expotest.exceptions;
public class QtyTicketsNotEnough extends RuntimeException{
    public QtyTicketsNotEnough(String message) {
        super(message);
    }
}