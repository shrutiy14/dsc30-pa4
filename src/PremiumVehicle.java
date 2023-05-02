/**
 * The PremiumVehicle class will also ‘extend’ the abstract class Vehicle.
 * @author Shruti Yamala
 * @since  05/01/2023
 */
public class PremiumVehicle extends Vehicle{

    // Error message to use in OperationDeniedException
    private static final String INVALID_INPUT =
            "The input vehicle is not a premium vehicle.";
    private static final String [] PREMIUM_VEHICLE_BRAND =
            new String[] {"lamborghini", "ferrari", "bmw", "mercedes","audi"};
    private static final String DENIED_PASSENGER_GROUP =
            "This operation is disabled in your passenger group.";

    /**
     * Constructor that initializes PremiumVehicle object given VehicleName.
     *
     * @param VehicleName String name
     * @throws OperationDeniedException if VehicleName is not a valid premium vehicle brand
     */

    public PremiumVehicle(String VehicleName)
            throws OperationDeniedException
    {
        /*TODO*/
        super(VehicleName);
        boolean exception = true;
        for(String element: PREMIUM_VEHICLE_BRAND){
            if(super.getVehicleName().toLowerCase().contains(element)){
                exception = false;
            }
        }
        if(exception){
            throw new OperationDeniedException(INVALID_INPUT);
        }
        this.vehicleID = 1;
    }

    /**
     * Add the passenger to the vehicle.
     *
     * @param p Passenger object to add
     * @return boolean true or false if the passenger already exists in this vehicle
     * @throws OperationDeniedException if p is not of type ValuePassenger
     */
    public boolean addPassengerToVehicle(Passenger p)
            throws OperationDeniedException {
        /*TODO*/
        if(p instanceof ValuePassenger == false){
            throw new OperationDeniedException(DENIED_PASSENGER_GROUP);
        }
        if(this.currentPassengers.contains(p)){
            return false;
        }
        this.currentPassengers.add(p);
        return true;
    }

    /**
     * Returns a string containing the vehicle info
     *
     * @return String containing vehicle info
     */
    // bmw01 (Premium) [2022-10-08]: [<Value Passenger> Yunyi]
    public String getVehicleInfo() {
        /*TODO*/
        String phrase = "";
        for(int i = 0; i < this.currentPassengers.size(); i++){
            if(i==this.currentPassengers.size()-1){
                phrase += this.currentPassengers.get(i).displayName();
                continue;
            }
            phrase += this.currentPassengers.get(i).displayName() + ", ";
        }
        return super.getVehicleName() + " (Premium) " + "[" + super.getDate() + "]" + ": " + "[" + phrase + "]";
    }
}
