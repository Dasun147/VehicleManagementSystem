import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;
/**
 * The RentalApp class is the main class of the Vehicle Rental System.
 * It contains the menu-driven program and manages all vehicle operations
 * such as adding, renting, returning vehicles and calculating rental income.
 */
public class RentalApp {
    // Scanner object for user input
    static Scanner sc = new Scanner(System.in);

    // Store all vehicles in an ArrayList
    static ArrayList<Vehicle> vehicles = new ArrayList<Vehicle>();

    // Store total rental income
    static double totalRental = 0;

    // checks vehicle availability in rental logic
    static boolean status = true;

    // Main method: entry point of the program
    public static void main(String[] args){

                  while(true){

                      // Display main menu
                      System.out.println("\n======================= VEHICLE RENTAL APP ========================");
                      System.out.println("___________________________________________________________________");
                      System.out.println("|    1. Add a Vehicle                                             |");
                      System.out.println("|    2. View All Vehicle                                          |");
                      System.out.println("|    3. Rent a Vehicle                                            |");
                      System.out.println("|    4. Return a Vehicle                                          |");
                      System.out.println("|    5. Search Vehicle by ID or Brand or Model or Availability    |");
                      System.out.println("|    6. View Total Rental Income                                  |");
                      System.out.println("|    7. Exit                                                      |");
                      System.out.println("___________________________________________________________________");
                         int choice;

                         // Input validation for menu choice
                         while (true){

                                 choice = getValidInt("\n--> Enter your choice: ");
                                 if (choice < 1 || choice > 7) {
                                     System.out.println("_______________________________________________");
                                     System.out.println("|    !! please enter valid number (1 - 7) !!  |");
                                     System.out.println("_______________________________________________\n");
                                     continue;
                                 }
                                 break;

                         }

                         // Handle menu selection using switch
                         switch(choice){
                             case 1 -> addVehicles();        //  add a vehicle
                             case 2 -> viewAllVehicles();    //  View all vehicles
                             case 3 -> rentVehicleFlow();    //  Rent a vehicle
                             case 4 -> returnVehicleFlow();  //  Return a vehicle
                             case 5 -> searchVehicle();      //  Show total rental income
                             case 6 -> {
                                 System.out.println("______________________________________________");
                                 System.out.printf("| + Total rental cost: Rs. %.2f +        |\n", totalRental );
                                 System.out.println("______________________________________________\n");
                             }
                             case 7 -> {           // Exit program
                                 System.out.println("\n_____________________");
                                 System.out.println("|     Thank you     |");
                                 System.out.println("_____________________");
                                 sc.close();
                                 return;
                             }
                         }
                     }


    }


    /**
     * addVehicles()
     * --------------------------------------------------------------
     * Allows the user to add a new vehicle into the system.
     * User must select vehicle type (Car/Bike/Van).
     * Performs validation to ensure:
     * - Vehicle type is valid
     * - Vehicle ID is unique
     * - Inputs are valid
     */
    static void addVehicles(){
        String vehicleType = "";
        String vehicleId;
        int numberOfSeats;
        int engineCapacityCC;
        double cargoCapacityKg;

        while (true) {

                // Get vehicle type from user
                vehicleType = getvalidString("--> which type is your vehicle (Car , Bike , Van): ");

                // Validate vehicle type
                if(!vehicleType.equalsIgnoreCase("car") &&
                   !vehicleType.equalsIgnoreCase("bike") &&
                   !vehicleType.equalsIgnoreCase("van")){
                    System.out.println("_________________________________________________________");
                    System.out.println("|  !! Invalid input. please check the vehicle type !!   |");
                    System.out.println("_________________________________________________________\n");
                    continue;
                }

                // Ensure vehicle ID is unique
                while(true){
                    vehicleId = getvalidString("--> Enter vehicle ID: ");
                    if (isVehicleIdExists(vehicleId)) {
                        System.out.println("_________________________________________________________");
                        System.out.println("|  !! vehicle id already exists. please use unique id   |");
                        System.out.println("_________________________________________________________\n");
                        continue;
                    }
                    break;
                }


                // Common attributes for all vehicles
                String brand = getvalidString("--> Enter Brand: ");
                String model = getvalidString("--> Enter Model: ");
                double baseRatePerDay = getValidDouble("--> Enter Base Rate Per Day: ");
                boolean isAvailable = getValidBoolean("--> Is Available (yes / no): ");

                // Create object based on selected type
                if (vehicleType.equalsIgnoreCase("Car")) {
                    numberOfSeats = getValidInt("--> Enter Number Of Seats: ");
                    Vehicle car = new Car(vehicleId, brand, model, baseRatePerDay, isAvailable, numberOfSeats);
                    vehicles.add(car);

                    System.out.println("_________________________________");
                    System.out.println("| ++ Car added successfully! ++ |");
                    System.out.println("_________________________________\n");
                    break;

                } else if (vehicleType.equalsIgnoreCase("Bike")) {
                    engineCapacityCC = getValidInt("--> Enter Engine Capacity CC: : ");
                    // Create bike object and add to list
                    Vehicle bike = new Bike(vehicleId, brand, model, baseRatePerDay, isAvailable, engineCapacityCC);
                    vehicles.add(bike);

                    System.out.println("__________________________________");
                    System.out.println("| ++ Bike added successfully! ++ |");
                    System.out.println("__________________________________\n");
                    break;
                } else if (vehicleType.equalsIgnoreCase("Van")) {
                    cargoCapacityKg = getValidDouble("--> Enter Cargo Capacity (Kg): ");
                    Vehicle van = new Van(vehicleId, brand, model, baseRatePerDay, isAvailable, cargoCapacityKg);
                    vehicles.add(van);
                    System.out.println("_________________________________");
                    System.out.println("| ++ Van added successfully! ++ |");
                    System.out.println("_________________________________\n");
                    break;
                }

        }
    }


    /**
     * viewAllVehicles()
     * --------------------------------------------------------------
     * Displays all vehicles stored in the system.
     * If no vehicles exist, prints warning message.
     */
    static void viewAllVehicles(){
        if(vehicles.isEmpty()){
            System.out.println("_______________________________________________");
            System.out.println("|  !! No vehicles available in the system !!  |");
            System.out.println("_______________________________________________\n");

        }
        System.out.println("=== All vehicles ===\n");
        for(Vehicle vehicle : vehicles){
            System.out.println("=== "+ vehicle.getVehicleType() + " ===");
            vehicle.displayDetails();
        }
    }


    /**
     * isVehicleIdExists()
     * --------------------------------------------------------------
     * Checks whether a vehicle ID already exists.
     * Returns true if found, otherwise false.
     */
    static boolean isVehicleIdExists(String vehicleId){
        for(Vehicle vehicle : vehicles){
            if(vehicle.getVehicleId().equalsIgnoreCase(vehicleId)){
                return true;
            }
        }

        return false;
    }



    static void showAllCars(){
          System.out.println("\n=== All Cars ===\n");
          boolean found = false;
          for(Vehicle vehicle : vehicles) {
              if (vehicle.getVehicleType().equalsIgnoreCase("Car")) {
                  vehicle.displayDetails();
                  found = true;
              }
          }

          if(!found){
              System.out.println("_______________________________________________");
              System.out.println("|  !! No vehicles available in the system !!  |");
              System.out.println("_______________________________________________\n");
              status = false;

          }

    }

    // Show all Bikes only
    static void showAllBikes(){
        System.out.println("\n=== All Bikes ===\n");
        boolean found = false;
        for(Vehicle vehicle : vehicles){
            if(vehicle.getVehicleType().equalsIgnoreCase("Bike")){
                vehicle.displayDetails();
                found = true;
            }}

        if(!found) {
            System.out.println("_______________________________________________");
            System.out.println("|  !! No vehicles available in the system !!  |");
            System.out.println("_______________________________________________\n");
            status = false;
        }

    }

    // Show all Vans Only
    static void showAllVans(){
        System.out.println("\n=== All Vans ===\n");
        boolean found = false;
        for(Vehicle vehicle : vehicles){
            if(vehicle.getVehicleType().equalsIgnoreCase("Van")){
                vehicle.displayDetails(); // later will be change

            }}
        if(!found) {
            System.out.println("_______________________________________________");
            System.out.println("|  !! No vehicles available in the system !!  |");
            System.out.println("_______________________________________________\n");
            status = false;
        }

        }




    /**
     * rentVehicleFlow()
     * --------------------------------------------------------------
     * This method handles the complete vehicle renting process.
     *--------------------------------------------------------
     * Steps performed:
     * 1. Check if vehicles exist in the system.
     * 2. Ask user to select vehicle type (Car, Bike, Van).
     * 3. Display vehicles based on selected type.
     * 4. Ask user to enter vehicle ID.
     * 5. Validate vehicle existence and availability.
     * 6. Collect renter details.
     * 7. Get rental duration (number of days).
     * 8. Calculate total rental cost.
     * 9. Update total rental income.
     */
    static void rentVehicleFlow(){
        // Step 1: Check if vehicle list is empty
        if(vehicles.isEmpty()) {
            System.out.println("_______________________________________________");
            System.out.println("|  !! No vehicles available in the system !!  |");
            System.out.println("_______________________________________________\n");

            return; // Stop method if no vehicles exist
        }




        String vehicleType;

        // Step 2: Ask user which vehicle type they want to rent
        while (true) {
            try {
                System.out.print("--> Which vehicle do you want to rent (Car, Bike, Van) :  ");

                // Read user input and remove extra space
                vehicleType = sc.nextLine().trim();

            }
            catch (StringIndexOutOfBoundsException e){
                // Handle unexpected input error
                System.out.println("_____________________________________________________");
                System.out.println("|  !! Invalid input. please Input related thing !!  |");
                System.out.println("_____________________________________________________\n");
                continue;
            }

            // Step 3: Display vehicles based on selected type
            if(vehicleType.equalsIgnoreCase("Car")){
                showAllCars();
                if(!status){
                    return;
                }

            }
            else if(vehicleType.equalsIgnoreCase("Van")){
                showAllVans();
                if(!status){
                    return;
                }
            }
            else if(vehicleType.equalsIgnoreCase("Bike")){
                showAllBikes();
                if(!status){
                    return;
                }
            }
            else{
                // If invalid type entered
                System.out.println("_____________________________________________");
                System.out.println("|  !! Please enter a valid vehicle type !!  |");
                System.out.println("_____________________________________________\n");

                continue;
            }
            break; // Exit loop if valid vehicle type entered
        }


        // Step 4: Select vehicle by ID
        while(true){

            System.out.print("--> Enter the vehicle ID you want to rent: ");
            String chooseVehicle = sc.nextLine();

            Vehicle selectedVehicle = null;

            // Search for matching vehicle ID
            for(Vehicle vehicle : vehicles){
                if(chooseVehicle.equalsIgnoreCase(vehicle.getVehicleId())){
                    selectedVehicle = vehicle;
                    break;
                }
            }

            // Step 5: Validate vehicle existence
            if(selectedVehicle == null){
                System.out.println("__________________________________________________");
                System.out.println("| !!  Vehicle ID not found. please try again  !! |");
                System.out.println("__________________________________________________\n");

                continue;
            }

            // Check if vehicle is already rented
            if(!selectedVehicle.isAvailable()){
                System.out.println("__________________________________________");
                System.out.println("| !!  This vehicle is already rented  !! |");
                System.out.println("__________________________________________\n");

                return; // Stop process if not available

            }

            // Step 6: Collect renter details
            System.out.print("--> Enter your full name: ");
            String fullName = sc.nextLine();

            System.out.print("--> Enter your ID number (without V): ");
            String userId = sc.nextLine();

            // Validate renter details
            if(fullName.isEmpty() || userId.isEmpty()){
                System.out.println("____________________________________");
                System.out.println("| !!  Please enter all details !!  |");
                System.out.println("____________________________________\n");

                continue;
            }

            // Step 7: Get number of rental days with validation
            int days;
            while(true){
                System.out.print("--> Enter number of rental days: ");
                try {
                    days = Integer.parseInt(sc.nextLine());

                    // Ensure rental period is greater than zero
                    if(days <= 0){
                        System.out.println("_________________________________________");
                        System.out.println("|  !! Day must be greater than zero !!  |");
                        System.out.println("_________________________________________\n");
                        continue;
                    }
                    break; // Valid number entered

                }catch(NumberFormatException e){
                    System.out.println("________________________________________________________________");
                    System.out.println("| !!  Invalid input!. please enter a numeric value for day !!  |");
                    System.out.println("________________________________________________________________\n");
                }

            }

            // Step 8: Rent the vehicle (update availability status)
            selectedVehicle.rentVehicle();

            // Step 9: Calculate rental cost
            double totalCost = selectedVehicle.calculateRentalCost(days);

            // Step 10: Add cost to total rental income
            totalRental += totalCost;

            // Display total rental cost
            System.out.println("___________________________________________");
            System.out.println("| + Total rental cost for " + days + " days: " + totalCost + " + |");
            System.out.println("___________________________________________\n");
            break; // Exit renting process

        }

    }


    /**
     * returnVehicleFlow()
     * --------------------------------------------------------------
     * This method handles the vehicle return process.
     * -
     * Steps performed:
     * 1. Ask the user to enter the vehicle ID.
     * 2. Search for the vehicle in the system.
     * 3. Check if the vehicle exists.
     * 4. Verify whether the vehicle is currently rented.
     * 5. If rented, update its availability status.
     */
    static void  returnVehicleFlow(){

        // Repeat process until valid return or exit condition
        while (true){

            // Step 1: Ask user for vehicle ID
            System.out.print("--> Enter vehicle ID to return: ");
            String vehicleId = sc.nextLine();

            Vehicle selectedVehicle = null;

            // Step 2: Search vehicle by ID in the ArrayList
            for(Vehicle vehicle : vehicles){
                if(vehicleId.equalsIgnoreCase(vehicle.getVehicleId())){
                    selectedVehicle = vehicle;
                    break;  // Stop searching once found
                }
            }

            // Step 3: If vehicle not found, display message
            if(selectedVehicle == null){
                System.out.println("___________________________________________");
                System.out.println("|  !! Vehicle ID not found try again. !!  |");
                System.out.println("___________________________________________\n");
                break;
            }

            // Step 4: Check if vehicle is already available
            // (Meaning it is not currently rented)
            if(selectedVehicle.isAvailable()){
                System.out.println("_____________________________________________________");
                System.out.println("|  !! This vehicle is not rented. cannot return !!  |");
                System.out.println("_____________________________________________________\n");
                return;  // Stop method execution
            }

            // Step 5: Perform return operation
            // This will update availability status inside Vehicle class
            selectedVehicle.returnVehicle();

            // Exit loop after successful return
            break;

        }

    }


    /**
     * searchVehicle()
     * --------------------------------------------------------------
     * This method allows the user to search for vehicles
     * using one of the following attributes:
     * -
     * - Vehicle ID
     * - Brand
     * - Model
     * - Availability status (true / false)
     * -
     * The method displays all matching vehicles.
     * If no match is found, a warning message is shown.
     */
    static void searchVehicle(){

        // Step 1: Check if vehicle list is empty
        if(vehicles.isEmpty()){
            System.out.println("_______________________________________________");
            System.out.println("|  !! No vehicles available in the system !!  |");
            System.out.println("_______________________________________________\n");
            return; // Stop method if no vehicles exist
        }

        // Step 2: Ask user to enter search value
        System.out.print("--> Enter the vehicle Id or Brand or Model or Availability: ");

        // Remove leading and trailing spaces using trim()
        String vehicleDetail = sc.nextLine().trim();

        Vehicle selectedVehicle = null;

        // Counter to number matching vehicles
        int count = 1;

        // Step 3: Loop through all vehicles to find matches
        for(Vehicle vehicle : vehicles){

            // Convert boolean availability to String for comparison
            String vehicleAvailability = String.valueOf(vehicle.isAvailable());

            // Check if search input matches any attribute
            if(vehicleDetail.equalsIgnoreCase(vehicle.getVehicleId()) ||
                    vehicleDetail.equalsIgnoreCase(vehicle.getBrand()) ||
                    vehicleDetail.equalsIgnoreCase(vehicle.getModel()) ||
                    vehicleDetail.equalsIgnoreCase(vehicleAvailability) ){

                selectedVehicle = vehicle;

                // Display matching vehicle details
                System.out.println("\n_____ Vehicle [" + count+ "] _____\n");
                count  += 1;
                selectedVehicle.displayDetails();

            }
        }

        // Step 4: If no vehicle was found, show message
        if(selectedVehicle == null){
            System.out.println("_______________________________________________");
            System.out.println("|  !! No vehicle matching for your search !!  |");
            System.out.println("_______________________________________________\n");

        }

    }

    /**
     * getValidInt()
     * --------------------------------------------------------------
     * This method reads and validates integer input from the user.
     * -
     * Conditions:
     * - Input must be a valid integer.
     * - Value must be greater than 0.
     *-
     * If invalid input is entered, the user is prompted again.
     */
    static int getValidInt(String message){
       while(true){
           try {
               // Display message and read integer input
               System.out.print(message);
               int value = sc.nextInt();

               // Clear newline buffer
               sc.nextLine();

               // Validate positive value
               if(value <= 0){
                   System.out.println("____________________________________________________________");
                   System.out.println("|  !! Invalid input. Valued cannot be 0 or less than 0 !!  |");
                   System.out.println("____________________________________________________________\n");
                   continue;
               }
               return value; // Return valid integer
           }
           catch(InputMismatchException e){
               // Handle non-numeric input
               System.out.println("_____________________________________________");
               System.out.println("|  !! Invalid input. please check again !!  |");
               System.out.println("_____________________________________________\n");

               // Clear invalid input from scanner
               sc.nextLine();
           }
       }
    }


    /**
     * getValidBoolean()
     * --------------------------------------------------------------
     * This method reads and validates a yes/no input from the user.
     *-
     * Conditions:
     * - Accepts only "yes" or "no" (case-insensitive).
     * - Converts input into boolean value.
     *-
     * yes  -> true
     * no   -> false
     */
    static boolean getValidBoolean(String message){
        while(true){

                // Display message and read input
                System.out.print(message);
                String value = sc.nextLine();

                if(value.equalsIgnoreCase("yes")) {
                    return true;
                }
                else if (value.equalsIgnoreCase("no")){
                    return false;
                }
                else{
                    // Invalid boolean input
                    System.out.println("_____________________________________________________");
                    System.out.println("|  !! Invalid input. please input 'yes' or 'no' !!  |");
                    System.out.println("_____________________________________________________\n");
                }

        }
    }


    /**
     * getValidDouble()
     * --------------------------------------------------------------
     * This method reads and validates double input from the user.
     * -
     * Conditions:
     * - Input must be numeric (decimal allowed).
     * - Value must be greater than 0.
     */
    static double getValidDouble(String message){
        while(true){
            try {
                // Display message and read double input
                System.out.print(message);
                double value = sc.nextDouble();

                // Clear newline buffer
                sc.nextLine();

                // Clear newline buffer
                if(value <= 0){
                    System.out.println("____________________________________________________________");
                    System.out.println("|  !! Invalid input. Valued cannot be 0 or less than 0 !!  |");
                    System.out.println("____________________________________________________________\n");
                    continue;
                }
                return value ; // Return valid double
            }
            catch(InputMismatchException e){
                // Handle invalid numeric input
                System.out.println("_____________________________________________");
                System.out.println("|  !! Invalid input. please check again !!  |");
                System.out.println("_____________________________________________\n");

                // Clear invalid input
                sc.nextLine();
            }
        }
    }

    /**
     * getvalidString()
     * --------------------------------------------------------------
     * This method reads and validates string input.
     * -
     * Conditions:
     * - Input must not be empty.
     * - Leading and trailing spaces are removed using trim().
     */
    static String getvalidString(String message){
        while(true) {

                // Display message and read input
                System.out.print(message);
                String value = sc.nextLine().trim();

                // Check for empty input
                if (value.isEmpty()) {

                    System.out.println("_________________________");
                    System.out.println("|  !! Invalid input !!  |");
                    System.out.println("_________________________");
                    continue;
                }
                return value; // Return valid string

        }
    }
}

