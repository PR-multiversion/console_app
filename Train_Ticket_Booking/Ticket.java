import java.util.List;

public class Ticket {
    int pnr;
    String from;
    String to;
    int seats;
    boolean isBooked;
    List<Integer> seatNumbers;

    Ticket(int pnr, String from, String to, int seats, boolean isBooked, List<Integer> seatNumbers){
        this.pnr = pnr;
        this.from = from;
        this.to = to;
        this.seats = seats;
        this.isBooked = isBooked;
        this.seatNumbers = seatNumbers;
    }

    @Override
    public String toString() {
        return String.format("PNR: %d, From: %s, To: %s, Seats: %d, Status: %s, Seat Numbers: %s",
                pnr, from, to, seats, isBooked ? "Booked" : "Waiting List", seatNumbers);
    }
}
