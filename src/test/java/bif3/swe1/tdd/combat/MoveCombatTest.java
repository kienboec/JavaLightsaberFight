package bif3.swe1.tdd.combat;

import bif3.swe1.tdd.Combat;
import bif3.swe1.tdd.fighter.Aim;
import bif3.swe1.tdd.fighter.Fighter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.*;


/*
  Mocking Part 1

  Herein we show some mocking to check the behaviour
  The class LightsaberFighter is mocked, so that we can track the effects the game mechanics - a combat round -
  to the two opponents.
  The behaviour of Combat.gameMechanics() is recorded and evaluated.
 */
@ExtendWith(MockitoExtension.class)
public class MoveCombatTest {
    @Mock Fighter mockedA;
    @Mock Fighter mockedB;
    Combat combat;

    @BeforeEach
    void setUp() {
        // arrange
        combat = new Combat(mockedA, mockedB);
    }

    @Test
    @DisplayName("Attack:Attack -> -1:-1")
    void testMove_Attack2Attack() {
        // arrange
        mockedA = mock(Fighter.class);
        mockedB = mock(Fighter.class);
        // Once created, a mock will remember all interactions.
        // Then you can selectively verify whatever interactions you are interested in.
        combat = new Combat(mockedA, mockedB);

        // act
        combat.gameMechanics(Aim.ATTACK, Aim.ATTACK);    // the game-mechs for one round is applied

        // assert
        verify(mockedA).changeVitality(-1);     // now check if the vitality of the opponents
        verify(mockedB).changeVitality(-1);     // were modified the right way
    }

    @Test
    @DisplayName("Attack:Defense -> -2:0")
    void testMove_Attack2Defense() {
        combat.gameMechanics(Aim.ATTACK, Aim.DEFENSE);
        verify(mockedA).changeVitality(-2);
        verify(mockedB, never()).changeVitality(0); // check, that changeVitality() was not called

        // How this would look like with EasyMock
//        expect(mockedA.changeVitality(-2));
//        replay(mockedA);
//        combat.move(Aim.ATTACK, Aim.DEFENSE);
//        verify(mockedA);
    }

    @Test
    @DisplayName("Attack:Rest -> 0:-2")
    void testMove_Attack2Rest() {
        combat.gameMechanics(Aim.ATTACK, Aim.REST);
        verify(mockedA, never()).changeVitality(0);
        verify(mockedB).changeVitality(-2);
    }

    @Test
    @DisplayName("Defense:Attack -> 0:-2")
    void testMove_DefenseAttack() {
        testMove(Aim.DEFENSE, Aim.ATTACK, 0, -2);
    }

    @Test
    @DisplayName("Defense:Defense -> -1:-1")
    void testMove_DefenseDefense() {
        testMove(Aim.DEFENSE, Aim.DEFENSE, -1, -1);
    }

    @Test
    @DisplayName("Defense:Rest -> 0:+1")
    void testMove_DefenseRest() {
        testMove(Aim.DEFENSE, Aim.REST, 0, +1);
    }

    @Test
    @DisplayName("Rest:Attack -> -2:0")
    void testMove_RestAttack() {
        testMove(Aim.REST, Aim.ATTACK, -2, 0);
    }

    @Test
    @DisplayName("Rest:Defense -> +1:0")
    void testMove_RestDefense() {
        testMove(Aim.REST, Aim.DEFENSE, +1, 0);
    }

    @Test
    @DisplayName("Rest:Rest -> +1:+1")
    void testMove_RestRest() {
        testMove(Aim.REST, Aim.REST, +1, +1);
    }

    private void testMove(Aim aimA, Aim aimB, int deltaA, int deltaB) {
        combat.gameMechanics(aimA, aimB);
        if (deltaA!=0)
            verify(mockedA).changeVitality(deltaA);
        else
            verify(mockedA, never()).changeVitality(0);

        if (deltaB!=0)
            verify(mockedB).changeVitality(deltaB);
        else
            verify(mockedB, never()).changeVitality(0);
    }
}