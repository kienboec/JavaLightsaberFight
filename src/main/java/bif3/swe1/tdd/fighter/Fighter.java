package bif3.swe1.tdd.fighter;

public interface Fighter {

    Aim nextAim();

    int getVitality();
    String getName();

    void changeVitality(int change);
    boolean isDead();
}
