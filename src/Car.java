// Car class that extends Vehicle
public class Car extends Vehicle {
    private final int numberOfSeats;

    // Constructor to create a Car object
    public Car(String vehicleId, String brand, String model, double baseRatePerDay, boolean isAvailable, int numberOfSeats) {
        super(vehicleId, brand, model, baseRatePerDay, isAvailable); // Call parent constructor
        this.numberOfSeats = numberOfSeats; // Set car-specific attribute
    }

    // Display car details
    @Override
    public void displayDetails() {
        super.displayDetails();
        System.out.println("Number of seats: " + numberOfSeats + "\n");  // Show number of seats
    }

    // Calculate rental cost for car
    @Override
    public double calculateRentalCost(int days) {
        return getBaseRatePerDay() * days + (numberOfSeats * 200 * days); // Base rate + extra per seat
    }

    // Return vehicle type
    @Override
    public String getVehicleType() {
        return "Car";
    }
}