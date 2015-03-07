package cis.ramrodcs.tamagotchi;

import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import cis.ramrodcs.tamagotchi.api.IPet;
import cis.ramrodcs.tamagotchi.api.Stat;

/**
 * Created by Dylan on 2/24/2015.
 */
public class Pet implements IPet {

    protected Map<Stat, Double> stats;
    protected Map<Stat, Double> modifiers;

    private static Double BASE_STAT = 0.5;
    private static Double BASE_MODIFIER = 1.0;

    public Pet() {
        for (Stat stat: Stat.values()) {
            stats.put(stat, BASE_STAT);
            modifiers.put(stat, BASE_MODIFIER);
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
        for (Stat stat: Stat.values()) {
            stats.put(stat, stats.get(stat) - 0.1);
        }
    }
}
