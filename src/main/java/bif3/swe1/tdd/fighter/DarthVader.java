package bif3.swe1.tdd.fighter;

public class DarthVader extends AbstractFighter {

    private final Aim[] aims = {Aim.ATTACK, Aim.ATTACK, Aim.ATTACK, Aim.REST};
    private int currentAim = 0;

    public DarthVader() {
        super("Darth", 100);
    }

    @Override
    public Aim nextAim() {
        if (currentAim >= aims.length)
            currentAim = 0;
        return aims[currentAim++];
    }
}
