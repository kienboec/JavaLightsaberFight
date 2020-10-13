package bif3.swe1.tdd.combat;

import bif3.swe1.tdd.Combat;
import bif3.swe1.tdd.fighter.Fighter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/*
 Mocking part 2

 Show some stubbing - teach the mock to return specific values

 By default, for all methods that return a value, a mock will return either null, a primitive/primitive wrapper value, or an empty collection, as appropriate.
 Once stubbed, the method will always return a stubbed value, regardless of how many times it is called.
 Last stubbing is more important - when you stubbed the same method with the same arguments many times.
 */
@ExtendWith(MockitoExtension.class)
class WinnerCombatTest {
    Combat combat;
    @Mock Fighter mockedA;
    @Mock Fighter mockedB;

    @BeforeEach
    void setUp() {
        combat = new Combat(mockedA, mockedB);
    }

    @Test
    @DisplayName("0:0 -> No winner(both dead)")
    void testGetWinner_Dead2Dead() {
        when(mockedA.getVitality()).thenReturn(0);
        when(mockedB.getVitality()).thenReturn(0);
        assertEquals(Optional.empty(), combat.getWinner(), "There should not be a winner!");
    }

    @Test
    @DisplayName("1:1 -> No winner(equal)")
    void testGetWinner_one2one() {
        when(mockedA.getVitality()).thenReturn(1);
        when(mockedB.getVitality()).thenReturn(1);
        assertEquals(Optional.empty(), combat.getWinner(), "There should not be a winner!");
    }

    @Test
    @DisplayName("n:n -> No winner(equal)")
    void testGetWinner_n2n() {
        for (int n = 1; n < 100; n++) {
            when(mockedA.getVitality()).thenReturn(n);
            when(mockedB.getVitality()).thenReturn(n);
            assertEquals(Optional.empty(), combat.getWinner(), "There should not be a winner!");
        }
    }

    @Test
    @DisplayName("m:n (m>n) -> A wins")
    void testGetWinner_m2n_mGreater() {
        for (int m = 1; m < 100; m++) {
            when(mockedA.getVitality()).thenReturn(m);
            for (int n = 0; n < m; n++) {
                when(mockedB.getVitality()).thenReturn(n);
                assertEquals(Optional.of(mockedA), combat.getWinner(), "There should not be a winner!");
            }
        }
    }

    @Test
    @DisplayName("n:m (m>n) -> B wins")
    void testGetWinner_n2m_mGreater() {
        for (int m = 1; m < 100; m++) {
            when(mockedB.getVitality()).thenReturn(m);
            for (int n = 0; n < m; n++) {
                when(mockedA.getVitality()).thenReturn(n);
                assertEquals(Optional.of(mockedB), combat.getWinner(), "There should not be a winner!");
            }
        }
    }

}