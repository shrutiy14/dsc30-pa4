/*
  Name: Shruti Yamala
  PID:  A17502627
 */
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * The Vehicle class is the abstract class that defines the functionality of a vehicle.
 * @author Shruti Yamala
 * @since  05/01/2023
 */

public abstract class Vehicle {

    private LocalDate date;
    private final String vehicle;
    protected final ArrayList<Passenger> currentPassengers;
    protected final ArrayList<String> passengerNames;
    protected int vehicleID;

    /**
     * Constructor that initializes a Vehicle object with a given name.
     *
     * @param VehicleName String that represents name of vehicle
     * @throws IllegalArgumentException if VehicleName null
     */
    public Vehicle(String VehicleName) throws IllegalArgumentException{
        this.date = LocalDate.now();
        /*TODO*/
        this.currentPassengers = new ArrayList<Passenger>();
        this.passengerNames = new ArrayList<String>();
        if(VehicleName == null){
            throw new IllegalArgumentException();
        }
        this.vehicle = VehicleName;

    }

    public LocalDate getDate(){
        return this.date;
    }

    /**
     * Returns vehicleName.
     *
     * @return String vehicleName
     */
    public String getVehicleName(){
        /*TODO*/
        return this.vehicle;
    }

    /**
     * Returns currentPassengers.
     *
     * @return ArrayList<Passenger> currentPassengers
     */
    public ArrayList<Passenger> getCurrentPassengers(){
        /*TODO*/
        return this.currentPassengers;
    }

    /**
     * Returns vehicleId.
     *
     * @return Integer vehicleID
     */
    public Integer getVehicleID() {
        /*TODO*/
        return this.vehicleID;
    }

    public abstract boolean addPassengerToVehicle(Passenger p)
            throws OperationDeniedException;

    public abstract String getVehicleInfo();

}
