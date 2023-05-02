import java.util.ArrayList;

public class ShareableRide implements RideScheduler{

    private static final String DENIED_PASSENGER_GROUP =
            "This operation is disabled in your passenger group.";
    private final String INVALID_ACTION =
            "Not able to perform proper assignment.";
    private final int CARPOOL_LIMIT = 5;

    private ArrayList<Vehicle> vehicles;
    private ArrayList<Passenger> passengers;
    private ArrayList<String> assignments;

    /**
     * Initializes the vehicle, passengers, assignments lists.
     */
    public ShareableRide(){
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
     * Returns list of passengers.
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
     * @param p Passenger object to add
     * @return boolean true or false if the input passenger has been already added
     * @throws OperationDeniedException if p is not of type ValuePassenger
     */
    public boolean addPassenger(Passenger p) throws OperationDeniedException {
        /*TODO*/
        if(p instanceof ValuePassenger == false){
            throw new OperationDeniedException(DENIED_PASSENGER_GROUP);
        }
        if(this.passengers.contains(p)){
            return false;
        }
        this.passengers.add(p);
        return true;
    }


    /**
     * Add the vehicle into the scheduler.
     *
     * @param v Vehicle object to add
     * @return boolean true or false if the input vehicle has been already added
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
     * Adds all the passengers to the available vehicles.
     *
     * @throws OperationDeniedException if there are more passengers than the total
     * number of seats available
     */
    public void assignPassengerToVehicle() throws OperationDeniedException {
        /*TODO*/
        int numAvailable = this.vehicles.size() * CARPOOL_LIMIT;
        if(this.passengers.size() > numAvailable){
            throw new OperationDeniedException(INVALID_ACTION);
        }
        //Same thing for vehicles
        ArrayList<Vehicle> vehicleTemp = new ArrayList<Vehicle>();
        //separating EconomyVehicles
        for(int i = 0; i < this.vehicles.size(); i++){
            if(this.vehicles.get(i) instanceof EconomyVehicle){
                vehicleTemp.add(this.vehicles.get(i));
            }
        }
        ArrayList<Vehicle> vehicleTemp1 = new ArrayList<Vehicle>();
        //separating PremiumVehicles
        for(int i = 0; i < this.vehicles.size(); i++){
            if(this.vehicles.get(i) instanceof PremiumVehicle){
                vehicleTemp1.add(this.vehicles.get(i));
            }
        }
        //combining the two sorted lists
        for(int i = 0; i < vehicleTemp1.size(); i++){
            Vehicle toAdd1 = vehicleTemp1.get(i);
            vehicleTemp.add(toAdd1);
        }
        this.vehicles = vehicleTemp;
        //assign passengers to vehicle
        int counter = 0;
        for(int i = 0; i < this.passengers.size(); i+=CARPOOL_LIMIT){
            /*if(this.passengers.get(i) == null){
                continue;
            }*/
            int j = 0;
            while(j+i<5 && j+i<this.passengers.size()) {
                this.vehicles.get(counter).addPassengerToVehicle(this.passengers.get(i+j));
                j++;
            }
            counter++;
        }
        //1:1 passenger-to-vehicle assignments
        for(int i = 0; i < this.vehicles.size(); i++){
            if(this.vehicles.get(i).getCurrentPassengers().size() == 0){
                continue;
            }
            assignments.add(this.vehicles.get(i).getVehicleInfo());
        }
    }

    /**
     * Returns the assignments list.
     *
     * @return ArrayList<String> assignments
     */
    public ArrayList<String> getRecords() {
        /*TODO*/
        return this.assignments;
    }
}
