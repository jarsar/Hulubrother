package nju.java;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javax.swing.JPanel;

public class Field extends JPanel {

    private final int OFFSET = 30;
    private final int SPACE = 48;

    private ArrayList tiles = new ArrayList();
    private Player player;
    Grandpa grandpa;

    Brother[] brothers = new Brother[7];

    Scorpion scorpion;
    Snake snake;
    Monster []monsters=new Monster[6];
    private int w = 0;
    private int h = 0;
    private boolean completed = false;

    private String level =
            "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n" +
                    "..........\n";

    public Field() {

        addKeyListener(new TAdapter());
        setFocusable(true);
        initWorld();
    }

    public int getBoardWidth() {
        return this.w;
    }

    public int getBoardHeight() {
        return this.h;
    }

    public final void initWorld() {

        int x = OFFSET;
        int y = OFFSET;

        Tile a;


        for (int i = 0; i < level.length(); i++) {

            char item = level.charAt(i);

            if (item == '\n') {
                y += SPACE;
                if (this.w < x) {
                    this.w = x;
                }

                x = OFFSET;
            } else if (item == '.') {
                a = new Tile(x, y);
                tiles.add(a);
                x += SPACE;
            } else if (item == '@') {
                player = new Player(x, y, this);
                x += SPACE;
            } else if (item == ' ') {
                x += SPACE;
            }

            h = y;
        }

        player = new Player(0 + OFFSET, 0 + OFFSET, this);
        player.setKind(1);
        player.getPicture("monster.png");

        grandpa=new Grandpa(0+OFFSET,0+OFFSET,this);
        grandpa.setKind(1);
        for(int i=0;i<brothers.length;i++){
            brothers[i]=new Brother(0+OFFSET,(i+1)*SPACE+OFFSET,this);
            brothers[i].setKind(i+2);
            String str=String.valueOf((i+1));
            brothers[i].getPicture("bro"+str+".png");
        }

        scorpion=new Scorpion(432+OFFSET,0+OFFSET,this);
        scorpion.setKind(-1);

        snake=new Snake(432+OFFSET,SPACE+OFFSET,this);
        snake.setKind(-2);

        for(int i=0;i<6;i++){
            monsters[i]=new Monster(432+OFFSET,(i+2)*SPACE+OFFSET,this);
            monsters[i].setKind(-(i+3));
        }
    }

    public void buildWorld(Graphics g) {

        g.setColor(new Color(250, 240, 170));
        g.fillRect(0, 0, this.getWidth(), this.getHeight());

        ArrayList world = new ArrayList();
        world.addAll(tiles);


//        world.add(player);

        world.add(grandpa);
        for(int i=0;i<brothers.length;i++){
            world.add(brothers[i]);
        }

        world.add(scorpion);
        world.add(snake);
        for(int i=0;i<6;i++){
            world.add(monsters[i]);
        }

        for (int i = 0; i < world.size(); i++) {

            Thing2D item = (Thing2D) world.get(i);

            if (item instanceof Player) {
                g.drawImage(item.getImage(), item.x() + 2, item.y() + 2, this);
            } else {
                g.drawImage(item.getImage(), item.x(), item.y(), this);
            }

            if (completed) {
                g.setColor(new Color(0, 0, 0));
                g.drawString("Completed", 25, 20);
            }

        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        buildWorld(g);
    }

    class TAdapter extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {

            if (completed) {
                return;
            }


            int key = e.getKeyCode();


            if (key == KeyEvent.VK_LEFT) {


                player.move(-SPACE, 0);

            } else if (key == KeyEvent.VK_RIGHT) {


                player.move(SPACE, 0);

            } else if (key == KeyEvent.VK_UP) {


                player.move(0, -SPACE);

            } else if (key == KeyEvent.VK_DOWN) {


                player.move(0, SPACE);

            } else if (key == KeyEvent.VK_S) {

                new Thread(player).start();

            }else if(key ==KeyEvent.VK_SPACE){
                new Thread(grandpa).start();

                for(int i=0;i<brothers.length;i++){
                    new Thread(brothers[i]).start();
                }

                new Thread(scorpion).start();
                new Thread(snake).start();

                for(int i=0;i<monsters.length;i++){
                    new Thread(monsters[i]).start();
                }
            }
            else if (key == KeyEvent.VK_R) {
                restartLevel();
            }

            repaint();
        }
    }


    public void restartLevel() {

        tiles.clear();
        initWorld();
        if (completed) {
            completed = false;
        }
    }
}