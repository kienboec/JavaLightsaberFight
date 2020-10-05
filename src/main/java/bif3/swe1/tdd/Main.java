package bif3.swe1.tdd;

import bif3.swe1.tdd.fighter.*;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        Luke luke = new Luke();
        DarthVader darthVader = new DarthVader();

        Combat combat1 = new Combat(luke, darthVader);
        Optional<Fighter> winner = combat1.fightForLifeAndDeath();

        System.out.println("--------");

        if (!winner.isEmpty()) {
            Yoda yoda = new Yoda();

            Combat combat2 = new Combat(yoda, winner.get());
            combat2.limitedFight(10);
        }
    }
}
