import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws CloneNotSupportedException {
        Scanner inp = new Scanner(System.in);
        int choice;

        TaxiBooking taxi = new TaxiBooking();
        taxi.addTaxi(100);
        taxi.addTaxi(100);
        taxi.addTaxi(120);
        taxi.addTaxi(130);
        do{
            System.out.println("1. Book Taxi\n2. Display Taxi Details\n3. Exit");
            choice = inp.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter a pickup point: ");
                    char start = inp.next().charAt(0);
                    System.out.println("Enter a drop point: ");
                    char end = inp.next().charAt(0);
                    System.out.println("Enter a pickup time: ");
                    int pickUpTime = inp.nextInt();

                    System.out.println(taxi.bookTaxi(start,end,pickUpTime));
                    break;
                case 2:
                    taxi.display();
                    break;
                case 3:
                    System.exit(3);
                    break;
                default:
                    System.out.println("Please choose a valid choice");
                    break;
            }
        }
        while(true);
    }
}
