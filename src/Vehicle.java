public abstract class Vehicle {
    private String vehicleId;
    private String brand;
    private String model;
    private double baseRatePerDay;
    private boolean isAvailable;

    /**
     * Constructor
     * Initializes all vehicle attributes.
     */
    public Vehicle(String vehicleId, String brand, String model, double baseRatePerDay, boolean isAvailable){
        this.vehicleId = vehicleId;
        this.brand = brand;
        this.model = model;
        this.baseRatePerDay = baseRatePerDay;
        this.isAvailable = isAvailable;
    }

    // setters
    public void setVehicleId(String vehicleId){
        this.vehicleId = vehicleId;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
    public void setModel(String model){
        this.model = model;
    }
    public void setBaseRatePerDay(double baseRatePerDay){
        this.baseRatePerDay = baseRatePerDay;
    }
    public void setIsAvailable(boolean isAvailable){
        this.isAvailable = isAvailable;
    }

    //getters
    public String getVehicleId(){
        return vehicleId;
    }
    public String getBrand(){
        return brand;
    }
    public String getModel(){
        return model;
    }
    public double getBaseRatePerDay(){
        return baseRatePerDay;
    }
    public boolean isAvailable(){
        return isAvailable;
    }


    /**
     * displayDetails()
     * Displays common vehicle information.
     * Subclasses can override if needed.
     */
    public void displayDetails(){
        System.out.println("Vehicle Id: " + vehicleId);
        System.out.println("Brand: " + brand);
        System.out.println("Model: " + model);
        System.out.printf("Base Rate Per Day: Rs. %.2f \n",  baseRatePerDay);
        System.out.println("Is Available: " + isAvailable);
    }

    /**
     * rentVehicle()
     * Marks the vehicle as rented if available.
     */
    public void rentVehicle(){
         if(isAvailable) {
             isAvailable = false;
             System.out.println("_____________________________________");
             System.out.println("| ++ Vehicle successfully rented ++ |");
             System.out.println("_____________________________________");
         }
         else{
             System.out.println("__________________________________");
             System.out.println("| !!! Vehicle already rented !!! |");
             System.out.println("__________________________________");
         }
    }

    /**
     * returnVehicle()
     * Marks the vehicle as available if it was rented.
     */
    public void returnVehicle(){
        if(!isAvailable){
            isAvailable = true;
            System.out.println("_______________________________________");
            System.out.println("| ++ Vehicle successfully returned ++ |");
            System.out.println("_______________________________________");
        }
        else{
            System.out.println("__________________________________");
            System.out.println("| !!! Vehicle was not rented !!! |");
            System.out.println("__________________________________");
        }

    }

    /**
     * calculateRentalCost()
     * This method must be implemented in each subclass.
     * It calculates rental cost based on vehicle type rules.
     */
    public abstract double calculateRentalCost(int days);


    /**
     * getVehicleType()
     * Returns the type of vehicle (Car, Bike, Van).
     * Must be implemented in subclasses.
     */
    public abstract String getVehicleType();
}
