package bif3.swe1.tdd;

import bif3.swe1.tdd.fighter.*;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Optional<Fighter> winningFighter = createCombat(
                new Luke(),
                new DarthVader()).fightForLifeAndDeath();

        if (winningFighter.isEmpty()) {
            return;
        }

        System.out.println("--------");

        createCombat(
                new Yoda(),
                winningFighter.get()).limitedFight(10);

    }

    public static Combat createCombat(Fighter f1, Fighter f2) {
        return new Combat(f1, f2);
    }
}
