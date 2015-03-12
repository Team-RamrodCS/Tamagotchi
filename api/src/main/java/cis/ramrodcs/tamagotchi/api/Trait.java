package cis.ramrodcs.tamagotchi.api;

/**
 * Created by Quentillionaire on 3/12/2015.
 */
public enum Trait {
    LAZY(Stat.ENERGY, -.1, 1.5),
    ENERGETIC(Stat.ENERGY, .1, 0.7),
    DIRTY(Stat.HYGIENE, -.1, 1.5),
    CLEAN(Stat.HYGIENE, .2, 1.5),
    HUNGRY(Stat.HUNGER, -.1, 1.5),
    SAD(Stat.HAPPINESS, -.1, .7),
    HAPPY(Stat.HAPPINESS, .2, 1.5);

    private Stat stat;
    private double add;
    private double modifier;

    Trait(Stat stat, double add, double modifier) {
        this.stat = stat;
        this.add = add;
        this.modifier = modifier;
    }

    public double getModifier() {
        return modifier;
    }

    public double getAdditive() {
        return add;
    }

    public Stat getStat() {
        return this.stat;
    }
}
