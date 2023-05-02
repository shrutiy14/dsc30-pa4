/**
 * The EconomyVehicle class will extend the Vehicle class.
 * @author Shruti Yamala
 * @since  05/01/2023
 */
public class EconomyVehicle extends Vehicle{

    /**
     * Constructor that initializes EconomyVehicle object given VehicleName.
     *
     * @param VehicleName String name of vehicle
     */
    public EconomyVehicle(String VehicleName) {
        /*TODO*/
        super(VehicleName);
        this.vehicleID = 0;
    }

    /**
     * Add the passenger to the vehicle.
     *
     * @param p Passenger to add
     * @return boolean true or false if the passenger already exists in this vehicle
     */
    public boolean addPassengerToVehicle(Passenger p){
        /*TODO*/
        if(this.currentPassengers.contains(p)){
            return false;
        }
        this.currentPassengers.add(p);
        return true;
    }

    /**
     * Returns a string containing the vehicle info.
     *
     * @return String vehicle info phrase
     */
    // civic [2022-10-08]: [Steven]
    public String getVehicleInfo() {
        /*TODO*/
        //phrase or passengerNames?

        String phrase = "";
        for(int i = 0; i < this.currentPassengers.size(); i++){
            if(i==this.currentPassengers.size()-1){
                phrase += this.currentPassengers.get(i).displayName();
                continue;
            }
            phrase += this.currentPassengers.get(i).displayName() + ", ";
        }
        return super.getVehicleName() + " [" + super.getDate() + "]" + ": " + "[" + phrase + "]";
    }

}
