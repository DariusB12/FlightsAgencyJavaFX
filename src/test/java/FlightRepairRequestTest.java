import model.Flight;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class FlightRepairRequestTest {
    @Test
    @DisplayName("Flight test random!")
    public void testExample(){
        Flight crr = new Flight(null,null,null,null,null);
        assertNull(crr.getAirport());
        assertNull(crr.getDestination());
    }

    @Test
    @DisplayName("Flight test random2!")
    public void testExample2(){
        assertEquals(1,1,"numere ar trebui sa fie egale");
    }
}
