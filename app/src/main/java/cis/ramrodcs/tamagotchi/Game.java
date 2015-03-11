package cis.ramrodcs.tamagotchi;

import cis.ramrodcs.tamagotchi.api.IGame;
import cis.ramrodcs.tamagotchi.api.IPet;

public class Game implements IGame {

    IPet pet = null;
    private static IGame instance = new Game();

    public Game() {
        setPet(createPet());
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
