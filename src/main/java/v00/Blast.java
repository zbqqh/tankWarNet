package v00;

import java.awt.*;

public class Blast {
    int x, y;
    int step;
    private boolean life = true;
    public static boolean init = false;
    public static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static Image e1;
    public static Image e2;
    public static Image e3;
    public static Image e4;
    public static Image e5;
    public static Image e6;
    public static Image e7;
    public static Image e8;
    public static Image e9;
    public static Image e10;
    public static Image e11;
    public static Image e12;
    public static Image e13;
    public static Image e14;
    public static Image e15;
    public static Image e16;
    public static Image[] images;

    static {
        e1 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e1.gif"));
        e2 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e2.gif"));
        e3 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e3.gif"));
        e4 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e4.gif"));
        e5 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e5.gif"));
        e6 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e6.gif"));
        e7 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e7.gif"));
        e8 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e8.gif"));
        e9 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e9.gif"));
        e10 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e10.gif"));
        e11 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e11.gif"));
        e12 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e12.gif"));
        e13 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e13.gif"));
        e14 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e14.gif"));
        e15 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e15.gif"));
        e16 = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/e16.gif"));

        images = new Image[]{
                e1,e2,e3,e4,e5,e6,e7,e8,e9,e10,e11,e12,e13,e14,e15,e16
        };
    }

    public Blast(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static void init(Graphics g){
        for (int i = 0; i < images.length; i++) {
            g.drawImage(images[i],-100,-100,null);
        }
    }
    public void paint(Graphics g) {
        if (!init){
            init(g);
            init = true;
        }
        if (!life || step == images.length) {
            return;
        }

        g.drawImage(images[step], x, y, null);
        step ++;
    }
}
