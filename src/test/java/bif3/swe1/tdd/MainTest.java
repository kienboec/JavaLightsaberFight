package bif3.swe1.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MainTest {
    @Test
    public void testCreateBattle(){

        // arrange

        // act
        // checks if this crashes (it should not)
        Combat actual = Main.createCombat(null, null);

        // assert
        Assertions.assertNotNull(actual);
    }
}
