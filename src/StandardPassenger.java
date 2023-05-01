/*
  Name: Shruti Yamala
  PID:  A17502627
 */

/**
 * The StandardPassenger class extends the Passenger class.
 * @author Shruti Yamala
 * @since  05/01/2023
 */
public class StandardPassenger extends Passenger{

    /**
     * Constructor that initializes StandardPassenger object given username and bio.
     *
     * @param username String passenger username
     * @param bio String passenger bio
     */
    public StandardPassenger(String username, String bio){
        /*TODO*/
        super(username, bio);
        this.passengerID = 0;
    }

    /**
     * Returns the username.
     *
     * @return String username
     */
    public String displayName() {
        /*TODO*/
        return this.username;
    }
}
