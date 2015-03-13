package cis.ramrodcs.tomagotcha.virtualpenguin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cis.ramrodcs.tamagotchi.api.IPet;
import cis.ramrodcs.tamagotchi.api.Stat;
import cis.ramrodcs.tamagotchi.api.Trait;

/**
 * Created by Dylan on 2/24/2015.
 */
public class Pet implements IPet {

    protected Map<Stat, Double> stats;
    protected Map<Stat, Double> modifiers;
    protected ArrayList<Trait> traits;

    private int playTimeOut = 0;
    private int feedTimeOut = 0;
    private int cleanTimeOut = 0;

    private static Double BASE_STAT = 0.9;
    private static Double BASE_MODIFIER = 1.0;

    private boolean isSleeping = false;

    public Pet() {
        stats = new HashMap<Stat, Double>();
        modifiers = new HashMap<Stat, Double>();
        traits = new ArrayList<Trait>();
        for(Trait trait : Trait.values()) {
            if(Math.random() >= 0.65) {
                traits.add(trait);
            }
        }
        for (Stat stat: Stat.values()) {
            stats.put(stat, BASE_STAT);
            double mod = BASE_MODIFIER;
            for(Trait t : traits) {
                if(t.getStat() == stat) {
                    mod *= t.getModifier();
                }
            }
            modifiers.put(stat, mod);
        }
        for(Trait trait : traits) {
            System.out.println(trait.toString());
        }
    }

    // Get the stat value
    public double getStat(Stat s) {
        return stats.get(s);
    }

    // Get the modifier value
    public double getModifier(Stat s) {
        return modifiers.get(s);
    }

    /*
     / Return the wellness of this pet
     / Wellness: (In the range 0.0-1.0) Average measure of all statistics
    */
    public double getWellness() {
        Double total, wellness;
        int count = 0;
        total = 0.0;
        // Find average of all stats
        for(Double n : stats.values()) {
            total += n;
            count++;
        }
        wellness = total / count;
        return wellness;
    }

    public void update() {
        // Update statistics
        // For every statistic:
        if(isSleeping()) {
            modifyStat(Stat.ENERGY, 1);
            modifyStat(Stat.HYGIENE, -.5);
            modifyStat(Stat.HUNGER, -.4);
        } else {
            modifyStat(Stat.ENERGY, -.5);
            modifyStat(Stat.HAPPINESS, -.5);
            modifyStat(Stat.HYGIENE, -.4);
            modifyStat(Stat.HUNGER, -.5);
        }
        if(stats.get(Stat.ENERGY) < .2 && Math.random() > .75) {
            isSleeping = true;
        }
        if(stats.get(Stat.ENERGY) > .8 && Math.random() > .75) {
            isSleeping = false;
        }

        playTimeOut = Math.max(--playTimeOut, 0);
        feedTimeOut = Math.max(--feedTimeOut, 0);
        cleanTimeOut = Math.max(--cleanTimeOut, 0);
    }

    public void modifyStat(Stat stat, double amount) {
        double newValue = getModifiedAmount(stat, amount);
        stats.put(stat, Math.min(Math.max(newValue, 0), 1));
    }

    public boolean canModifyStat(Stat stat, double amount) {
        double delta = getModifiedAmount(stat, amount);
        return delta <=1 && delta >= 0;
    }

    private double getModifiedAmount(Stat stat, double amount) {
        double delta = amount;
        for(Trait t : traits) {
            if(stat == t.getStat()) {
                delta += t.getAdditive();
            }
        }
        double mod = (modifiers.get(stat) * delta/100.)  + stats.get(stat);
        return mod;
    }

    public void setPlayTimeOut(int to) {
        playTimeOut = to;
    }

    public void setFeedTimeOut(int to) {
        feedTimeOut = to;
    }

    public void setCleanTimeOut(int to) {
        cleanTimeOut = to;
    }

    public boolean canPlay() {
        return playTimeOut == 0;
    }

    public boolean canFeed() {
        return feedTimeOut == 0;
    }

    public boolean canClean() {
        return cleanTimeOut == 0;
    }

    public boolean isSleeping() {
        return isSleeping;
    }
}
