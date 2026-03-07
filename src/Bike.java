// Bike class that extends Vehicle
public class Bike extends Vehicle{
    private final int engineCapacityCC;

    // Constructor to create a Bike object
    public Bike(String vehicleId, String brand, String model, double baseRatePerDay, boolean isAvailable ,int engineCapacityCC){
        super(vehicleId, brand, model, baseRatePerDay, isAvailable);
        this.engineCapacityCC = engineCapacityCC;
    }

    @Override
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Engine capacity: " + engineCapacityCC + "CC" +  "\n");
    }

    @Override
    public double calculateRentalCost(int days){
        return getBaseRatePerDay() * days + (engineCapacityCC * 0.5 * days);
    }


    @Override
    public String getVehicleType(){
        return "Bike";
    }



}
