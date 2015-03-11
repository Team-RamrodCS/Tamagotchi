package cis.ramrodcs.tamagotchi.api;

public interface IPet {
    public double getStat(Stat s);
    public double getModifier(Stat s);
    public double getWellness();
    public void update();
    public boolean modifyStat(Stat s, double amt, boolean force);
    public boolean isSleeping();
}
