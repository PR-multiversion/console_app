import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner inp = new Scanner(System.in);
        TicketBookingHandler bookingHandler = new TicketBookingHandler();
        int choice;
        while(true){
            System.out.println("1. Ticket Booking\n2. Cancel Ticket\n3. Display chart\n4. Exit");
            System.out.println("-------------------------------------------------------");
            System.out.println("Enter your choice: ");
            choice = inp.nextInt();
            inp.nextLine();
            switch (choice){
                case 1:
                    System.out.println("Enter the source location(A/B/C/D/E): ");
                    String from = inp.nextLine();
                    System.out.println("Enter the destination location(A/B/C/D/E): ");
                    String to = inp.nextLine();
                    System.out.println("Enter the total number of seats: ");
                    int seats = inp.nextInt();
                    inp.nextLine();
                    bookingHandler.bookTicket(from, to, seats);
                    break;
                case 2:
                    System.out.println("Enter the PNR number: ");
                    int pnr = inp.nextInt();
                    System.out.println("Enter the total number of seats: ");
                    int cancelSeats = inp.nextInt();
                    inp.nextLine();
                    bookingHandler.cancelTicket(pnr, cancelSeats);
                    break;
                case 3:
                    bookingHandler.displayChart();
                    break;
                case 4:
                    System.exit(0);
                default:
                    System.out.println("Please the enter the correct choice: ");

            }
        }
    }
}
