/*
  Name: Shruti Yamala
  PID:  A17502627
 */

/**
 * The ValuePassenger class extends the Passenger class.
 * @author Shruti Yamala
 * @since  05/01/2023
 */
public class ValuePassenger extends Passenger{

    // instance variable
    private String customTitle;

    /**
     * Constructor that initializes ValuePassenger object given a username and bio.
     *
     * @param username String username of passenger
     * @param bio String passenger bio
     */
    public ValuePassenger(String username, String bio){
        /*TODO*/
        super(username, bio);
        this.customTitle = "Value Passenger";
        this.passengerID = 1;
    }

    /**
     * Returns a string displaying the passenger type and name.
     *
     * @return String phrase displaying name
     */
    public String displayName() {
        /*TODO*/
        return "<" + this.customTitle + ">" + " " + this.username;
    }

    /**
     * Update customTitle with the new title.
     *
     * @param newTitle String that represents the new title
     */
    public void setCustomTitle(String newTitle) {
        /*TODO*/
        this.customTitle = newTitle;
    }
}
