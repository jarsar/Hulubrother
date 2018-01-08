package nju.java;


import java.awt.Image;
import java.net.URL;
import java.util.Random;
import javax.swing.ImageIcon;

import static nju.java.GlobaVar.playground;

public class Player extends Thing2D implements Runnable {
    private Boolean down;

    public int getKind() {
        return kind;
    }

    public void setKind(int kind) {
        this.kind = kind;
    }

    private int kind;

    public int getP_x() {
        return p_x;
    }

    private int p_x;

    public int getP_y() {
        return p_y;
    }

    private int p_y;
    private Field field;
    protected Boolean to_left;

    public Boolean getIs_death() {
        return is_death;
    }

    public void setIs_death(Boolean is_death) {
        this.is_death = is_death;
    }

//    private void setP_x_y() {
//        p_x = (x() - 30) / 48;
//        p_y = (x() - 30) / 48;
//    }


    private Boolean is_death;

    public Player(int x, int y, Field field) {
        super(x, y);

        this.field = field;
        is_death = false;
        to_left = false;
        down = false;
        p_x=(this.x()-30)/48;
        p_y=(this.x()-30)/48;
        p_x=ra(p_x,9);
        p_y=ra(p_y,9);
        set_death(p_x,p_y);
//        setP_x_y();
        //playground[p_x + p_y * 10] = kind;
//        URL loc = this.getClass().getClassLoader().getResource("monster.png");
//        ImageIcon iia = new ImageIcon(loc);
//        Image image = iia.getImage();
//        this.setImage(image);
    }

    public void getPicture(String source) {
        URL loc = this.getClass().getClassLoader().getResource(source);
        ImageIcon iia = new ImageIcon(loc);
        Image image = iia.getImage();
        this.setImage(image);
    }

    public void move(int x, int y) {
        int nx = this.x() + x;
        int ny = this.y() + y;
        this.setX(nx);
        this.setY(ny);
    }

    public void set_death(int x, int y) {
        this.p_x = x*48+30;
        this.p_y = y*48+30;
    }

    //    public void run() {
//        while (!Thread.interrupted()) {
//            Random rand = new Random();
//
//            this.move(rand.nextInt(10), rand.nextInt(10));
//            try {
//
//                Thread.sleep(rand.nextInt(1000) + 1000);
//                this.field.repaint();
//
//            } catch (Exception e) {
//
//            }
//        }
//    }
    public void run() {
        while (!Thread.interrupted()) {
            Random rand = new Random();
            if (is_death == false) {
                if (down == false) {
                    if (to_left == true) {

//                        playground[p_x + p_y * 10] = 0;
                        this.move(-48, 0);
//                        setP_x_y();
//                        if (playground[p_x + p_y * 10] > 0) {
//                            this.move(48, 0);
//                            setP_x_y();
//                        }
//                        playground[p_x + p_y * 10] = kind;
                        if (this.x() == 30) {
                            to_left = false;
                            if (this.y() != 462) {
                                down = true;
                            }
                        }
                    } else {
//                        playground[p_x + p_y * 10] = 0;
                        this.move(48, 0);
//                        setP_x_y();
//                        if (playground[p_x + p_y * 10] > 0) {
//                            this.move(-48, 0);
//                            setP_x_y();
//                        }
//                        playground[p_x + p_y * 10] = kind;
                        if (this.x() == 462) {
                            to_left = true;
                            if (this.y() != 462) {
                                down = true;
                            }
                        }
                    }
                } else {
//                    playground[p_x + p_y * 10] = 0;
                    this.move(0, 48);
//                    setP_x_y();
//                    if (playground[p_x + p_y * 10] > 0) {
//                        this.move(0, -48);
//                        setP_x_y();
//                    }
//                    playground[p_x + p_y * 10] = kind;
                    down = false;
                }
            }
//            kill_enemy();
            be_death();
            try {

                Thread.sleep(rand.nextInt(1000) + 1000);
                this.field.repaint();

            } catch (Exception e) {

            }
            synchronized (playground) {

            }
        }
    }

    public void be_death() {
        if (this.x() == p_x && this.y() == p_y) {
            this.is_death = true;
            this.getPicture("death.png");
        }
    }
//    private void kill_enemy() {
//        int[] enemy = new int[8];
//        enemy[0] = (p_y - 1) * 10 + p_x - 1;
//        enemy[1] = (p_y - 1) * 10 + p_x;
//        enemy[2] = (p_y - 1) * 10 + p_x + 1;
//        enemy[3] = p_y * 10 + p_y - 1;
//        enemy[4] = p_y * 10 + p_y + 1;
//        enemy[5] = (p_y + 1) * 10 + p_x - 1;
//        enemy[6] = (p_y + 1) * 10 + p_x;
//        enemy[7] = (p_y + 1) * 10 + p_x + 1;
//        for (int i = 0; i < 8; i++) {
//            if (enemy[i] >= 0 && enemy[i] < 100) {
//                if ((playground[enemy[i]] > 0 && kind < 0) || (playground[enemy[i]] < 0 && kind > 0)) {
//                    is_death = true;
//                    getPicture("death.png");
//                }
//            }
//        }
//        playground[p_x + p_y * 10] = 0;
//    }
    private int ra(int min,int max){
        Random rand=new Random();
        int s=rand.nextInt(max)%(max-min+1) + min;
        return s;
    }
}