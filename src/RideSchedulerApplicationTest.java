/*
  Name: Shruti Yamala
  PID:  A17502627
 */

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.time.LocalDate;

/**
 * This class represents J-unit test file for the entire messenger application.
 * @author Shruti Yamala
 * @since  05/01/2023
 */
public class RideSchedulerApplicationTest {
    ValuePassenger yunyi;
    Vehicle vehicle1;
    Vehicle vehicle2;
    Vehicle vehicle3;
    Vehicle vehicle4;
    Vehicle vehicle5;
    Vehicle vehicle6;
    ValuePassenger shawna;
    ValuePassenger roya;
    ValuePassenger zoya;
    StandardPassenger abhay;
    StandardPassenger derek;
    StandardPassenger nate;
    StandardRide ride1;
    StandardRide ride2;
    StandardRide ride3;
    ShareableRide ride4;
    ShareableRide ride5;
    ShareableRide ride6;

    LocalDate date = LocalDate.now();



    @BeforeEach
    public void setup() throws OperationDeniedException {
        yunyi = new ValuePassenger("Yunyi", "Tutor");
        vehicle1 = new EconomyVehicle("toyota");
        vehicle2 = new EconomyVehicle("ford");
        vehicle3 = new EconomyVehicle("chevy");

        vehicle4 = new PremiumVehicle("bmw");
        vehicle5 = new PremiumVehicle("mercedes");
        vehicle6 = new PremiumVehicle("audi");
        assertThrows(OperationDeniedException.class, () -> {
            PremiumVehicle vehicleA = new PremiumVehicle("toyota");
        });

        try {
            PremiumVehicle vehicleA = new PremiumVehicle("toyota");
        } catch (OperationDeniedException e) {
            assertEquals(e.getMessage(), "The input vehicle is not a premium vehicle.");
        }

        shawna = new ValuePassenger("Shawna", "Doctor");
        roya = new ValuePassenger("Roya", "Dentist");
        zoya = new ValuePassenger("Zoya", "CEO");

        abhay = new StandardPassenger("Abhay", "Economist");
        derek = new StandardPassenger("Derek", "Lawyer");
        nate = new StandardPassenger("Nate", "Scientist");

        ride1 = new StandardRide();
        ride2 = new StandardRide();
        ride3 = new StandardRide();

        ride4 = new ShareableRide();
        ride5 = new ShareableRide();
        ride6 = new ShareableRide();
    }

    @Test
    public void testValuePassengerThrowsIAE() {
        assertThrows(IllegalArgumentException.class, () -> {
            ValuePassenger yunyi = new ValuePassenger("Yunyi", null);
        });
    }

    @Test
    public void testVehicleThrowsIAE(){
        assertThrows(IllegalArgumentException.class, () -> {
            Vehicle vehicle = new EconomyVehicle(null);
        });

    }

    //for vehicle class
    @Test
    public void testGetVehicleName(){
        assertEquals(vehicle1.getVehicleName(), "toyota");
    }
    //for EconomyVehicle
    @Test
    public void testAddPassenger() throws OperationDeniedException{
        vehicle1.addPassengerToVehicle(abhay);
        vehicle1.addPassengerToVehicle(derek);
        assertEquals(vehicle1.getCurrentPassengers().size(), 2);

        vehicle1.addPassengerToVehicle(nate);
        assertEquals(vehicle1.getCurrentPassengers().size(), 3);

        assertEquals(vehicle2.addPassengerToVehicle(roya), true);
    }
    //for vehicle
    @Test
    public void testGetCurrentPassengers() throws OperationDeniedException
    {
        vehicle4.addPassengerToVehicle(roya);
        vehicle4.addPassengerToVehicle(shawna);
        assertEquals(vehicle4.getCurrentPassengers().size(), 2);
    }

    @Test
    public void testGetVehicleID(){
        assertEquals(vehicle2.getVehicleID(), 0);
    }
    //for economy vehicle
    @Test
    public void testGetVehicleInfo() throws OperationDeniedException{
        vehicle1.addPassengerToVehicle(abhay);
        //toyota
        String phrase = "toyota [2023-05-01]: [Abhay]";
        assertEquals(vehicle1.getVehicleInfo(), phrase);
    }
    //premium vehicle
    @Test
    public void testPremiumAddPassenger() throws OperationDeniedException{
        assertThrows(OperationDeniedException.class, () -> {
            vehicle4.addPassengerToVehicle(abhay);
        });
        try {
            vehicle4.addPassengerToVehicle(abhay);
        } catch (OperationDeniedException e) {
            assertEquals(e.getMessage(), "This operation is disabled in your passenger group.");
        }

        vehicle4.addPassengerToVehicle(roya);
        assertEquals(vehicle4.getCurrentPassengers().size(), 1);
        vehicle5.addPassengerToVehicle(shawna);
        vehicle5.addPassengerToVehicle(zoya);
        assertEquals(vehicle5.getCurrentPassengers().size(), 2);

        assertEquals(vehicle6.addPassengerToVehicle(zoya), true);
    }

    @Test
    public void testPremiumGetVehicleInfo() throws OperationDeniedException{
        vehicle4.addPassengerToVehicle(roya);
        String phrase = "bmw (Premium) [2023-05-01]: [<Value Passenger> Roya]";
        assertEquals(vehicle4.getVehicleInfo(), phrase);
    }
    //Passenger class
    @Test
    public void testSetBio(){
        roya.setBio("Orthodontist");
        assertEquals(roya.displayBio(), "Orthodontist");
    }

    @Test
    public void testDisplayBio(){
        assertEquals(shawna.displayBio(), "Doctor");
    }

    @Test
    public void testGetPassengerID(){
        assertEquals(nate.getPassengerID(), 0);
    }
    //Standard Passenger class
    @Test
    public void testStandardDisplayName(){
        assertEquals(abhay.displayName(), "Abhay");
    }
    //Value Passenger Class
    @Test
    public void testValueDisplayName(){
        assertEquals(zoya.displayName(), "<Value Passenger> Zoya");
    }

    @Test
    public void testSetCustomTitle(){
        shawna.setCustomTitle("VIP Passenger");
        assertEquals(shawna.displayName(), "<VIP Passenger> Shawna");
    }
    //StandardRide class
    @Test
    public void testStandardGetVehicles(){
        ride1.addVehicle(vehicle1);
        ride1.addVehicle(vehicle2);
        assertEquals(ride1.getVehicles().size(), 2);
    }

    @Test
    public void testStandardGetPassengers(){
        ride2.addPassenger(roya);
        ride2.addPassenger(shawna);
        assertEquals(ride2.getPassengers().size(), 2);
    }

    @Test
    public void testStandardAddPassenger(){
        ride1.addPassenger(roya);
        assertEquals(ride1.addPassenger(roya), false);
        assertEquals(ride2.addPassenger(derek), true);
        assertEquals(ride3.addPassenger(zoya), true);
    }
    @Test
    public void testStandardAddVehicle(){
        ride1.addVehicle(vehicle1);
        ride1.addVehicle(vehicle3);
        assertEquals(ride1.addVehicle(vehicle1), false);
        assertEquals(ride1.addVehicle(vehicle2), true);
        assertEquals(ride3.addVehicle(vehicle6), true);
    }
    @Test
    public void testAssignPassengerToVehicle() throws OperationDeniedException{
        ride1.addVehicle(vehicle1);
        ride1.addVehicle(vehicle2);
        ride1.addPassenger(derek);
        ride1.addPassenger(abhay);
        ride1.addPassenger(nate);
        //catch exception1
        assertThrows(OperationDeniedException.class, () -> {
            ride1.assignPassengerToVehicle();
        });
        try {
            ride1.assignPassengerToVehicle();
        } catch (OperationDeniedException e) {
            assertEquals(e.getMessage(), "Each passenger should have one vehicle matched.");
        }
        //catch exception2 more standard than econ
        ride3.addVehicle(vehicle1);
        ride3.addVehicle(vehicle4);
        ride3.addVehicle(vehicle5);
        ride3.addPassenger(derek);
        ride3.addPassenger(nate);
        ride3.addPassenger(zoya);
        assertThrows(OperationDeniedException.class, () -> {
            ride3.assignPassengerToVehicle();
        });
        try {
            ride3.assignPassengerToVehicle();
        } catch (OperationDeniedException e) {
            assertEquals(e.getMessage(), "Not able to perform proper assignment.");
        }


        ride2.addVehicle(vehicle1);
        ride2.addVehicle(vehicle2);
        //premium vehicle added
        ride2.addVehicle(vehicle4);
        ride2.addPassenger(derek);
        ride2.addPassenger(zoya);
        ride2.addPassenger(shawna);
        ride2.assignPassengerToVehicle();
        assertEquals(ride2.getRecords().size(), 3);
        assertEquals(ride2.getRecords().get(0), "toyota [2023-05-01]: [Derek]");
        assertEquals(ride2.getRecords().get(1), "ford [2023-05-01]: [<Value Passenger> Zoya]");
        assertEquals(ride2.getRecords().get(2), "bmw (Premium) [2023-05-01]: [<Value Passenger> Shawna]");
    }
    @Test
    public void testGetRecords() throws OperationDeniedException{
        ride3.addVehicle(vehicle1);
        ride3.addVehicle(vehicle2);
        ride3.addPassenger(nate);
        ride3.addPassenger(shawna);
        ride3.assignPassengerToVehicle();
        assertEquals(ride3.getRecords().size(), 2);
    }
    //ShareableRide Class
    @Test
    public void testShareableGetVehicles(){
        ride4.addVehicle(vehicle5);
        assertEquals(ride4.getVehicles().size(), 1);
    }

    @Test
    public void testShareableGetPassengers() throws OperationDeniedException{
        ride5.addPassenger(shawna);
        ride5.addPassenger(zoya);
        ride5.addPassenger(roya);
        assertEquals(ride5.getPassengers().size(), 3);
    }

    @Test
    public void testShareableAddPassenger() throws OperationDeniedException{
        //exception
        assertThrows(OperationDeniedException.class, () -> {
            ride4.addPassenger(abhay);
        });
        try {
            ride4.addPassenger(abhay);
        } catch (OperationDeniedException e) {
            assertEquals(e.getMessage(), "This operation is disabled in your passenger group.");
        }

        assertEquals(ride5.addPassenger(shawna), true);
        ride5.addPassenger(roya);
        assertEquals(ride5.addPassenger(roya), false);
        assertEquals(ride6.addPassenger(zoya), true);
    }

    @Test
    public void testShareableAddVehicle(){
        assertEquals(ride4.addVehicle(vehicle4), true);
        assertEquals(ride5.addVehicle(vehicle2), true);
        ride6.addVehicle(vehicle1);
        assertEquals(ride6.addVehicle(vehicle1), false);

    }

    @Test
    public void testShareableAssignPassengerToVehicle() throws OperationDeniedException{
        ride4.addPassenger(shawna);
        ride4.addPassenger(zoya);
        ride4.addPassenger(roya);
        ride4.addVehicle(vehicle1);
        ride4.addVehicle(vehicle4);
        ride4.assignPassengerToVehicle();
        assertEquals(ride4.getRecords().get(0), "toyota [2023-05-01]: [<Value Passenger> Shawna, <Value Passenger> Zoya, <Value Passenger> Roya]");
        assertEquals(ride4.getRecords().size(), 1);

        ride5.addPassenger(shawna);
        ride5.addPassenger(zoya);
        ride5.addPassenger(roya);
        ride5.addVehicle(vehicle1);
        ride5.assignPassengerToVehicle();
        assertEquals(ride5.getRecords().size(), 1);
    }








}
