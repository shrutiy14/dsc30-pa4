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
    LocalDate date = LocalDate.now();



    @BeforeEach
    public void setup() throws OperationDeniedException {
        yunyi = new ValuePassenger("Yunyi", "Tutor");
    }

    @Test
    public void testValuePassengerThrowsIAE() {
        assertThrows(IllegalArgumentException.class, () -> {
            ValuePassenger yunyi = new ValuePassenger("Yunyi", null);
        });
    }


}
