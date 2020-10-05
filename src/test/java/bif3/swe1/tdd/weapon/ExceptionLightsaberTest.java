package bif3.swe1.tdd.weapon;

import bif3.swe1.tdd.weapon.Lightsaber;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ExceptionLightsaberTest {

    @Test
    public void testSetBelongsTo_NullPointerException() {
        Lightsaber lightsaber = new Lightsaber();
        assertThrows(NullPointerException.class, () -> { // switch throws null pointer exception on null
            lightsaber.setBelongTo(null);
        }, "Should throw exception");

    }

}