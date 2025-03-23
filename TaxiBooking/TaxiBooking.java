import java.util.ArrayList;
import java.util.List;

public class TaxiBooking {
    public static int customerId = 1;
    public List<Taxi> taxiList = new ArrayList<>();
    public List<Taxi> taxiBookedHistory = new ArrayList<>();

    public Taxi taxiReady;
    int minDistance;

    public void addTaxi(double basicEarning){
        taxiList.add(new Taxi(basicEarning));
    }

    public String bookTaxi(char pickupPoint, char dropPoint, int pickupTime) throws CloneNotSupportedException{

        taxiReady = null;
        minDistance = Integer.MAX_VALUE;

//        for(Taxi t: taxiList){
//
//            if(t.getDropTime() <= pickupTime && Math.abs(pickupPoint - t.getCurrentLocation()) <= min){
//
//                if(Math.abs(pickupPoint - t.getCurrentLocation()) == min){
//                    if(taxiReady != null && t.getTotalEarning() < taxiReady.getTotalEarning()){
//                        taxiReady = t;
//                    }
//                }
//                else{
//                    taxiReady = t;
//                    min = Math.abs(pickupPoint - t.getCurrentLocation());
//                }
//            }
//            System.out.println(taxiReady.toString());
//        }

        for (Taxi t : taxiList) {
            if (t.getDropTime() <= pickupTime) {
                int distance = Math.abs(pickupPoint - t.getCurrentLocation());
                if (distance < minDistance || (distance == minDistance && t.getTotalEarning() < (taxiReady != null ? taxiReady.getTotalEarning() : Double.MAX_VALUE))) {
                    taxiReady = t;
                    minDistance = distance;
                }
            }
        }

        if(taxiReady != null){
            taxiReady.setCustomerId(customerId++);
            taxiReady.setCurrentLocation(dropPoint);
            taxiReady.setPickupLocation(pickupPoint);
            taxiReady.setDropLocation(dropPoint);
            taxiReady.setPickupTime(pickupTime);
            taxiReady.setDropTime((pickupTime) + (Math.abs(dropPoint - pickupPoint)));
            taxiReady.setTotalEarning(taxiReady.getTotalEarning() + (Math.abs(dropPoint - pickupPoint)*15-5)*10 + taxiReady.getBasicEarning());

            taxiBookedHistory.add((Taxi)taxiReady.clone());
        }
        return taxiReady!=null?"Taxi number "+taxiReady.getTaxiId()+" is booked!":"Taxis not available";
    }

    public void display(){
        for(Taxi t: taxiBookedHistory){
            System.out.println(t);
        }
    }
}
