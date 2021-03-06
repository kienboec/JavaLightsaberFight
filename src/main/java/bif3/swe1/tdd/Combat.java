package bif3.swe1.tdd;

import bif3.swe1.tdd.fighter.Aim;
import bif3.swe1.tdd.fighter.Fighter;

import java.util.Optional;

public class Combat {
    private final Fighter opponentA;
    private final Fighter opponentB;

    public Combat(Fighter opponentA, Fighter opponentB) {
        this.opponentA = opponentA;
        this.opponentB = opponentB;
    }

    public Optional<Fighter> fightForLifeAndDeath() {
        return limitedFightImplementation(false, 0);
    }

    public Optional<Fighter> limitedFight(int turns) {
        return limitedFightImplementation(true, turns);
    }

    private Optional<Fighter> limitedFightImplementation(boolean checkTurns, int turns) {
        if (checkTurns && turns < 1) {
            throw new IllegalArgumentException("Number of turns must be greater than 0!");
        }

        System.out.printf("%s fights against %s\n", opponentA.getName(), opponentB.getName());
        System.out.println("Let the fight begin!");
        for (int turn = 1; ((!checkTurns) || turn <= turns) &&
                (!opponentA.isDead()) &&
                (!opponentB.isDead()); turn++) {

            Aim aimA = opponentA.nextAim();
            Aim aimB = opponentB.nextAim();
            System.out.printf("%d.turn: %s <---> %s  \n", turn, aimA, aimB);

            gameMechanics(aimA, aimB);
        }
        Optional<Fighter> optWinner = getWinner();
        if (optWinner.isEmpty()) {
            System.out.println("There is no winner");
        } else {
            System.out.printf("The winner is %s \n", optWinner.get().getName());
        }

        return optWinner;
    }

    public void gameMechanics(Aim aimA, Aim aimB) {
        if (aimA == Aim.ATTACK && aimB == Aim.ATTACK) {
            opponentA.changeVitality(-1);
            opponentB.changeVitality(-1);
        } else if (aimA == Aim.ATTACK && aimB == Aim.DEFENSE) {
            opponentA.changeVitality(-2);
        } else if (aimA == Aim.ATTACK && aimB == Aim.REST) { // REST
            opponentB.changeVitality(-2);
        } else if (aimA == Aim.DEFENSE && aimB == Aim.ATTACK) {
            opponentB.changeVitality(-2);
        } else if (aimA == Aim.DEFENSE && aimB == Aim.DEFENSE) {
            opponentA.changeVitality(-1);
            opponentB.changeVitality(-1);
        } else if (aimA == Aim.DEFENSE && aimB == Aim.REST) {
            opponentB.changeVitality(+1);
        } else if (aimA == Aim.REST && aimB == Aim.ATTACK) {
            opponentA.changeVitality(-2);
        } else if (aimA == Aim.REST && aimB == Aim.DEFENSE) {
            opponentA.changeVitality(+1);
        } else if (aimA == Aim.REST && aimB == Aim.REST) {
            opponentA.changeVitality(+1);
            opponentB.changeVitality(+1);
        }
    }

    public Optional<Fighter> getWinner() {
        if (opponentA.isDead() && opponentB.isDead())
            return Optional.empty();    // all are dead - no winner

        if (opponentA.isDead())
            return Optional.of(opponentB);
        if (opponentB.isDead())
            return Optional.of(opponentA);

        if (opponentA.getVitality() == opponentB.getVitality())
            return Optional.empty();    // both equal - still no winner

        return Optional.of((opponentA.getVitality() > opponentB.getVitality()) ? opponentA : opponentB);
    }
}
