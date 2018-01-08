package nju.java;

public class Snake extends Player{
    public Snake(int x, int y, Field field) {
        super(x, y, field);
        this.to_left=true;
        getPicture("snake.png");
    }
}
