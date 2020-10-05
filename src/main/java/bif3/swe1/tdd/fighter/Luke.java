package bif3.swe1.tdd.fighter;

public class Luke extends AbstractFighter {

    private final Aim[] aims = { Aim.ATTACK, Aim.ATTACK, Aim.DEFENSE };
    private int currentAim = 0;

    public Luke() {
        super("Luke", 80);
    }

    @Override
    public Aim nextAim() {
        if( currentAim >= aims.length )
            currentAim = 0;
        return aims[currentAim++];
    }
}
