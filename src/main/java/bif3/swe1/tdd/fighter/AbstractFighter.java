package bif3.swe1.tdd.fighter;

import bif3.swe1.tdd.weapon.Lightsaber;
import lombok.Getter;
import lombok.ToString;

@ToString
public abstract class AbstractFighter implements Fighter {

    @Getter
    private String name;

    @Getter
    private int vitality;

    @Getter
    private Lightsaber lightsaber;

    public AbstractFighter(String name, int vitality) {
        this.name = name;
        this.vitality = vitality;

        this.lightsaber = new Lightsaber();
        lightsaber.setBelongTo(name);
    }

    public void changeVitality(int change) {
        vitality += change;
    }

    public boolean isDead() {
        return vitality <= 0;
    }
}
