import java.util.ArrayList;

public class StandardRide implements RideScheduler{

    private final String MISMATCH_MSG =
            "Each passenger should have one vehicle matched.";
    private final String INVALID_ACTION =
            "Not able to perform proper assignment.";
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Passenger> passengers;
    private ArrayList<String> assignments;

    /**
     * Initialize the vehicle, passengers, assignments lists.
     */
    public StandardRide(){
        /*TODO*/
        this.vehicles = new ArrayList<Vehicle>();
        this.passengers = new ArrayList<Passenger>();
        this.assignments = new ArrayList<String>();
    }

    /**
     * Returns the list of vehicles.
     *
     * @return ArrayList<Vehicle> vehicles
     */
    public ArrayList<Vehicle> getVehicles() {
        /*TODO*/
        return this.vehicles;
    }

    /**
     * Returns the list of passengers.
     *
     * @return ArrayList<Passenger> passengers
     */
    public ArrayList<Passenger> getPassengers() {
        /*TODO*/
        return this.passengers;
    }

    /**
     * Add the passenger into the scheduler.
     *
     * @param p Passenger to add
     * @return boolean true or false if p has already been added
     */
    public boolean addPassenger(Passenger p) {
        /*TODO*/
        if(this.passengers.contains(p)){
            return false;
        }
        this.passengers.add(p);
        return true;
    }

    /**
     * Add the vehicle into the scheduler.
     *
     * @param v Vehicle to add
     * @return boolean true or false if v has already been added
     */
    public boolean addVehicle(Vehicle v) {
        /*TODO*/
        if(this.vehicles.contains(v)){
            return false;
        }
        this.vehicles.add(v);
        return true;
    }

    /**
     * Creates the 1:1 passenger-to-vehicle assignments.
     *
     * @throws OperationDeniedException if there are more passengers or more vehicles
     * or if not every Standard passenger can have a ride
     */
    public void assignPassengerToVehicle() throws OperationDeniedException {
        /*TODO*/
        if(this.passengers.size() != this.vehicles.size()){
            throw new OperationDeniedException(MISMATCH_MSG);
        }
        ArrayList<Passenger> temporary = new ArrayList<Passenger>();
        int count = 0;
        //separating StandardPassengers
        for(int i = 0; i < this.passengers.size(); i++){
            if(this.passengers.get(i) instanceof StandardPassenger){
                temporary.add(this.passengers.get(i));
                count += 1;
            }
        }
        //sorting StandardPassengers by passengerID
        for(int i = 1; i < temporary.size(); i++){
            int j = i;
            while(j>0 && temporary.get(j).getPassengerID() < temporary.get(j-1).getPassengerID()){
                Passenger temp = temporary.get(j);
                temporary.set(j, temporary.get(j-1));
                temporary.set(j-1, temp);
                j--;
            }
        }
        ArrayList<Passenger> temporary1 = new ArrayList<Passenger>();
        //separating ValuePassengers
        for(int i = 0; i < this.passengers.size(); i++){
            if(this.passengers.get(i) instanceof ValuePassenger){
                temporary1.add(this.passengers.get(i));
            }
        }
        //sorting ValuePassengers by passengerID
        for(int i = 1; i < temporary1.size(); i++){
            int j = i;
            while(j>0 && temporary1.get(j).getPassengerID() < temporary1.get(j-1).getPassengerID()){
                Passenger temp = temporary1.get(j);
                temporary1.set(j, temporary1.get(j-1));
                temporary1.set(j-1, temp);
                j--;
            }
        }
        //combining the two sorted lists
        for(int i = 0; i < temporary1.size(); i++){
            Passenger toAdd = temporary1.get(i);
            temporary.add(toAdd);
        }
        this.passengers = temporary;
        //Same thing for vehicles
        ArrayList<Vehicle> vehicleTemp = new ArrayList<Vehicle>();
        int counter = 0;
        //separating EconomyVehicles
        for(int i = 0; i < this.vehicles.size(); i++){
            if(this.vehicles.get(i) instanceof EconomyVehicle){
                vehicleTemp.add(this.vehicles.get(i));
                counter += 1;
            }
        }
        //if more StandardPassengers than EconomyVehicles
        if(count > counter){
            throw new OperationDeniedException(INVALID_ACTION);
        }
        //sorting EconomyVehicles by vehicleID
        for(int i = 1; i < vehicleTemp.size(); i++){
            int j = i;
            while(j>0 && vehicleTemp.get(j).getVehicleID() < vehicleTemp.get(j-1).getVehicleID()){
                Vehicle temp1 = vehicleTemp.get(j);
                vehicleTemp.set(j, vehicleTemp.get(j-1));
                vehicleTemp.set(j-1, temp1);
                j--;
            }
        }
        ArrayList<Vehicle> vehicleTemp1 = new ArrayList<Vehicle>();
        //separating PremiumVehicles
        for(int i = 0; i < this.vehicles.size(); i++){
            if(this.vehicles.get(i) instanceof PremiumVehicle){
                vehicleTemp1.add(this.vehicles.get(i));
            }
        }
        //sorting PremiumVehicles by vehicleID
        for(int i = 1; i < vehicleTemp1.size(); i++){
            int j = i;
            while(j>0 && vehicleTemp1.get(j).getVehicleID() < vehicleTemp1.get(j-1).getVehicleID()){
                Vehicle temp2 = vehicleTemp1.get(j);
                vehicleTemp1.set(j, vehicleTemp1.get(j-1));
                vehicleTemp1.set(j-1, temp2);
                j--;
            }
        }
        //combining the two sorted lists
        for(int i = 0; i < vehicleTemp1.size(); i++){
            Vehicle toAdd1 = vehicleTemp1.get(i);
            vehicleTemp.add(toAdd1);
        }
        this.vehicles = vehicleTemp;
        //Question!
        //assign passenger to vehicle
        for(int i = 0; i < this.vehicles.size(); i++){
            this.vehicles.get(i).addPassengerToVehicle(this.passengers.get(i));
        }
        //1:1 passenger-to-vehicle assignments
        for(int i = 0; i < this.vehicles.size(); i++){
            assignments.set(i, this.vehicles.get(i).getVehicleInfo());
        }
    }

    /**
     * Returns the list of assignments.
     *
     * @return ArrayList<String> assignments
     */
    public ArrayList<String> getRecords() {
        /*TODO*/
        return this.assignments;
    }

}
