package nju.java;

public class Monster extends Player{
    public Monster(int x, int y, Field field) {
        super(x, y, field);
        this.to_left=true;
        getPicture("monster.png");
    }
}
