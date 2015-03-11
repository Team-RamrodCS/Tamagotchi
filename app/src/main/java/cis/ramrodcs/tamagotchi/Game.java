package cis.ramrodcs.tamagotchi;

import cis.ramrodcs.tamagotchi.api.IGame;
import cis.ramrodcs.tamagotchi.api.IPet;
import cis.ramrodcs.tamagotchi.api.Stat;

public class Game implements IGame {

    IPet pet = null;
    private static IGame instance = new Game();

    public Game() {
        pet = createPet();
    }

    public static IGame getInstance() {
        return instance;
    }

    @Override
    public IPet getPet() {
        return pet;
    }

    @Override
    public void setPet(IPet pet) {
        this.pet = pet;
    }

    @Override
    public IPet createPet() {
        return new Pet();
    }

}
