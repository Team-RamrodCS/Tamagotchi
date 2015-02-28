package cis.ramrodcs.tamagotchi.api;

/**
 * Created by Dylan on 2/28/2015.
 */
public enum Stat {
    HUNGER("Hunger"),
    HAPPINESS("Happiness"),
    HYGIENE("Hygiene"),
    ENERGY("Energy"),
    GROWTH("Growth");

    String name = "";

    Stat(String s) {
        name = s;
    }

    public String getName() {
        return name;
    }
}
