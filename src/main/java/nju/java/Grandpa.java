package nju.java;

public class Grandpa extends Player{

    public Grandpa(int x, int y, Field field) {
        super(x, y, field);
        this.to_left=false;
        getPicture("grandpa.png");
    }
}
