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
        //sort by passengerID
        for(int i = 1; i < this.passengers.size(); i++){
            int j = i;
            while(j>0 && this.passengers.get(j).getPassengerID() < this.passengers.get(j-1).getPassengerID()){
                Passenger temp = this.passengers.get(j);
                this.passengers.set(j, this.passengers.get(j-1));
                this.passengers.set(j-1, temp);
                j--;
            }
        }
        //Same thing for vehicles
        ArrayList<Vehicle> vehicleTemp = new ArrayList<Vehicle>();
        //separating EconomyVehicles
        for(int i = 0; i < this.vehicles.size(); i++){
            if(this.vehicles.get(i) instanceof EconomyVehicle){
                vehicleTemp.add(this.vehicles.get(i));
            }
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
        //assign passengers to vehicle
        int counter = 0;
        for(int i = 0; i < this.passengers.size(); i+=5){
            if(this.passengers.get(i) == null){
                continue;
            }
            this.vehicles.get(counter).addPassengerToVehicle(this.passengers.get(i));
            if(this.passengers.get(i+1) == null){
                continue;
            }
            this.vehicles.get(counter).addPassengerToVehicle(this.passengers.get(i+1));
            if(this.passengers.get(i+2) == null){
                continue;
            }
            this.vehicles.get(counter).addPassengerToVehicle(this.passengers.get(i+2));
            if(this.passengers.get(i+3) == null){
                continue;
            }
            this.vehicles.get(counter).addPassengerToVehicle(this.passengers.get(i+3));
            if(this.passengers.get(i+4) == null){
                continue;
            }
            this.vehicles.get(counter).addPassengerToVehicle(this.passengers.get(i+4));
            counter++;
        }
        //1:1 passenger-to-vehicle assignments
        for(int i = 0; i < this.vehicles.size(); i++){
            assignments.set(i, this.vehicles.get(i).getVehicleInfo());
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
