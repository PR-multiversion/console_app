public class Taxi implements Cloneable{
    private static int idCounter = 0;
    private final int TaxiId;
    private int customerId;
    private char currentLocation = 'A';
    private char pickupLocation;
    private char dropLocation;
    private int pickupTime;
    private int dropTime;
    private double basicEarning;
    private double totalEarning;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();

    }
    Taxi(double basicEarning){
        this.basicEarning = basicEarning;
        this.TaxiId = ++idCounter;
    }


    public int getTaxiId() {
        return TaxiId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public char getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(char currentLocation) {
        this.currentLocation = currentLocation;
    }

    public char getPickupLocation() {
        return pickupLocation;
    }

    public void setPickupLocation(char pickupLocation) {
        this.pickupLocation = pickupLocation;
    }

    public char getDropLocation() {
        return dropLocation;
    }

    public void setDropLocation(char dropLocation) {
        this.dropLocation = dropLocation;
    }

    public int getPickupTime() {
        return pickupTime;
    }

    public void setPickupTime(int pickupTime) {
        this.pickupTime = pickupTime;
    }

    public int getDropTime() {
        return dropTime;
    }

    public void setDropTime(int dropTime) {
        this.dropTime = dropTime;
    }

    public double getTotalEarning() {
        return totalEarning;
    }

    public void setTotalEarning(double totalEarning) {
        this.totalEarning = totalEarning;
    }
    public double getBasicEarning(){
        return basicEarning;
    }

    @Override
    public String toString() {
        return  "TaxiId = " + TaxiId +
                "\tCustomerId = "+ customerId +
                "\tpickupLocation = " + pickupLocation +
                "\tdropLocation = " + dropLocation +
                "\tpickupTime = " + pickupTime +
                "\tdropTime = " + dropTime +
                "\ttotalEarning = " + totalEarning;
    }
}
