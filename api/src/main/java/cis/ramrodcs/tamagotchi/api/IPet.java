package cis.ramrodcs.tamagotchi.api;

public interface IPet {
    public double getStat(Stat s);
    public double getModifier(Stat s);
    public double getWellness();
    public void update();
    public boolean isSleeping();
    public void modifyStat(Stat stat, double amount);
    public boolean canModifyStat(Stat stat, double amount);
}
