package bif3.swe1.tdd.fighter;

public class Yoda extends AbstractFighter {

    private final Aim[] aims = { Aim.DEFENSE, Aim.ATTACK };
    private int currentAim = 0;

    public Yoda() {
        super("Yoda", 120);
    }

    @Override
    public Aim nextAim() {
        if( currentAim >= aims.length )
            currentAim = 0;
        return aims[currentAim++];
    }
}
