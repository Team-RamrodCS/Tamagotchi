package cis.ramrodcs.tomagotcha.virtualpenguin;

import java.util.HashMap;
import java.util.Map;

import cis.ramrodcs.tamagotchi.api.IPet;
import cis.ramrodcs.tamagotchi.api.Stat;

/**
 * Created by Dylan on 2/24/2015.
 */
public class Pet implements IPet {

    protected Map<Stat, Double> stats;
    protected Map<Stat, Double> modifiers;
    protected Map<Stat, Double> modifyAmt;

    private static Double BASE_STAT = 0.5;
    private static Double BASE_MODIFIER = 1.0;
    private static Double BASE_DIFF_PER_UPDATE = 10000./(24*60*60*1000/Settings.MS_PER_UPDATE);

    private boolean isSleeping = false;

    public Pet() {
        stats = new HashMap<Stat, Double>();
        modifiers = new HashMap<Stat, Double>();
        modifyAmt = new HashMap<Stat, Double>();
        for (Stat stat: Stat.values()) {
            stats.put(stat, BASE_STAT);
            modifiers.put(stat, BASE_MODIFIER);
            modifyAmt.put(stat, BASE_DIFF_PER_UPDATE);
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
        System.out.println(BASE_DIFF_PER_UPDATE);

        // For every statistic:
        if(isSleeping()) {
            modifyStat(Stat.ENERGY, .1);
            modifyStat(Stat.HYGIENE, -.01);
            modifyStat(Stat.HUNGER, -.01);
        } else {
            modifyStat(Stat.ENERGY, -.05);
            modifyStat(Stat.HAPPINESS, -.05);
            modifyStat(Stat.HYGIENE, -.05);
            modifyStat(Stat.HUNGER, -.05);
        }
        if(stats.get(Stat.ENERGY) < .2 && Math.random() > .25) {
            isSleeping = true;
        }
        if(stats.get(Stat.ENERGY) > .8 && Math.random() > .25) {
            isSleeping = false;
        }
    }

    public void modifyStat(Stat stat, double amount) {
        stats.put(stat, Math.min(Math.max(stats.get(stat) + modifiers.get(stat)*amount*modifyAmt.get(stat)*10, 0), 1));
    }

    public boolean canModifyStat(Stat stat, double amount) {
        double mod = stats.get(stat) + amount;
        return mod <=1 && mod >= 0;
    }

    public boolean isSleeping() {
        return isSleeping;
    }
}
