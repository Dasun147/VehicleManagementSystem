// Van class that extends Vehicle
public class Van extends Vehicle{
    private final double cargoCapacityKg;

    // Constructor to create a Van object
    public Van(String vehicleId, String brand, String model, double baseRatePerDay, boolean isAvailable , double cargoCapacityKg){
        super(vehicleId, brand, model, baseRatePerDay, isAvailable);
        this.cargoCapacityKg = cargoCapacityKg;
    }

    @Override
    public void displayDetails(){
        super.displayDetails();
        System.out.println("Cargo capacity :  " + cargoCapacityKg + "Kg" + "\n");
    }

    @Override
    public double calculateRentalCost(int days){
        return getBaseRatePerDay() * days + (cargoCapacityKg * 0.2 * days);
    }

    @Override
    public String getVehicleType(){
        return "Van";
    }


}
