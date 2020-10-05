package bif3.swe1.tdd.weapon;
import bif3.swe1.tdd.weapon.Lightsaber;
import bif3.swe1.tdd.weapon.LightsaberColor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SimpleLightsaberTest {

    @Test
    public void testGetColor_LukeShouldBeGreen() {
        Lightsaber lightsaber = new Lightsaber();
        lightsaber.setBelongTo("Luke");

        // can be simplified
        assertTrue( lightsaber.getColor() == LightsaberColor.GREEN, "Lukes lightsaber color should be GREEN" );
    }

    @Test
    public void testGetColor_DarthShouldBeRed() {
        Lightsaber lightsaber = new Lightsaber();
        lightsaber.setBelongTo("Darth");

        // can be simplified
        assertFalse( lightsaber.getColor() != LightsaberColor.RED, "Darths lightsaber color should be RED" );
    }

    @Test
    @DisplayName("Unknown fighters should not use the lightsaber, color NONE")
    public void testGetColor_WrongFighterShouldBeNone() {
        Lightsaber lightsaber = new Lightsaber();
        lightsaber.setBelongTo("Donald Duck");
        assertEquals( lightsaber.getColor(), LightsaberColor.NONE, "For unknown fighters the color should be NONE");
    }

    @Test
    @DisplayName("Unused lightsabers should not glow, color NONE")
    public void testGetColor_UnusedShouldBeNone() {
        Lightsaber lightsaber = new Lightsaber();
        if( lightsaber.getColor() != LightsaberColor.NONE ) {
            fail("For unused lightsabers the color should be NONE");
        }
    }
}