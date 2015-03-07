package cis.ramrodcs.tamagotchi;

import cis.ramrodcs.tamagotchi.api.IGame;
import cis.ramrodcs.tamagotchi.api.IPet;

public class Game implements IGame {

    IPet pet = null;
    private static IGame instance = new Game();

    public Game() {
    }

    public static IGame getInstance() {
        return instance;
    }

    @Override
    public IPet getPet() {
        return null;
    }

    @Override
    public void setPet(IPet pet) {

    }

    @Override
    public IPet createPet() {
        return null;
    }
}
