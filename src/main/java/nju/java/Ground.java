package nju.java;


import javax.swing.JFrame;

import static nju.java.GlobaVar.playground;

public final class Ground extends JFrame {

    private final int OFFSET = 30;


    public Ground() {
        InitUI();
    }

    public void InitUI() {
        Field field = new Field();
        add(field);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(field.getBoardWidth() + OFFSET,
                field.getBoardHeight() + 2 * OFFSET);
        setLocationRelativeTo(null);
        setTitle("Ground");
    }


    public static void main(String[] args) {
        for(int i=0;i<100;i++){
            playground[i]=0;
        }
        Ground ground = new Ground();
        ground.setVisible(true);
    }
}