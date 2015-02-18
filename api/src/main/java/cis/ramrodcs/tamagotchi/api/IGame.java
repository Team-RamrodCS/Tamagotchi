package cis.ramrodcs.tamagotchi.api;

public interface IGame{

    public IPet getPet();
    public void setPet(IPet pet);
    public IPet createPet();

}
