/*
  Name: Shruti Yamala
  PID:  A17502627
 */

/**
 * The Passenger class is the abstract class that defines the functionality of a vehicle user.
 * @author Shruti Yamala
 * @since  05/01/2023
 */
public abstract class Passenger {

    // instance variables
    protected String username;
    protected String bio;
    protected int passengerID;

    /**
     * Initialize username and bio with the given arguments.
     *
     * @param username String passenger username
     * @param bio String passenger bio
     */
    public Passenger(String username, String bio) {
        /*TODO*/
        if(username == null | bio == null){
            throw new IllegalArgumentException();
        }
        this.username = username;
        this.bio = bio;
    }

    /**
     * Updates the bio with newBio.
     *
     * @param newBio String to update bio with
     */
    public void setBio(String newBio) {
        /*TODO*/
        if(newBio == null){
            throw new IllegalArgumentException();
        }
        this.bio = newBio;
    }

    /**
     * Returns the bio.
     *
     * @return String bio
     */
    public String displayBio() {
        /*TODO*/
        return this.bio;
    }

    /**
     * Returns the passenger ID.
     *
     * @return Integer passengerID
     */
    public Integer getPassengerID() {
        /*TODO*/
        return this.passengerID;
    }

    public abstract String displayName();
}
