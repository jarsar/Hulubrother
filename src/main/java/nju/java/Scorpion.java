package nju.java;

public class Scorpion extends Player {
    public Scorpion(int x, int y, Field field) {
        super(x, y, field);
        this.to_left=true;
        getPicture("scorpion.png");
    }
}
