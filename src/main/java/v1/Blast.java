package v1;

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
//            images.add(
//                    toolkit.createImage(
//                            v1.Blast.class.getClassLoader().getResource(
//                                    "images/e" + i + ".gif"
//                            )
//                    )
//            );
    }
//    public static Image e0 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e1.gif"));
//    public static Image e2 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e2.gif"));
//    public static Image e3 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e3.gif"));
//    public static Image e4 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e4.gif"));
//    public static Image e5 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e5.gif"));
//    public static Image e6 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e6.gif"));
//    public static Image e7 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e7.gif"));
//    public static Image e8 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e8.gif"));
//    public static Image e9 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e9.gif"));
//    public static Image e10 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e10.gif"));
//    public static Image e11 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e11.gif"));
//    public static Image e12 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e12.gif"));
//    public static Image e13 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e13.gif"));
//    public static Image e14 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e14.gif"));
//    public static Image e15 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e15.gif"));
//    public static Image e16 = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/e16.gif"));
//
//    public static Image badTank1L = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/BadTank1L.jpeg"));
//    public static Image badTank1U = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/BadTank1U.jpeg"));
//    public static Image badTank1R = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/BadTank1R.jpeg"));
//    public static Image badTank1D = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/BadTank1D.jpeg"));
//    public static Image badTank2L = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/BadTank2L.jpeg"));
//    public static Image badTank2U = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/BadTank2U.jpeg"));
//    public static Image badTank2R = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/BadTank2R.jpeg"));
//    public static Image badTank2D = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/BadTank2D.jpeg"));
//    public static Image bulletL = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/bulletL.jpeg"));
//    public static Image bulletU = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/bulletU.jpeg"));
//    public static Image bulletR = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/bulletR.jpeg"));
//    public static Image bulletD = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/bulletD.jpeg"));
//    public static Image goodTank1L = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/GoodTank1L.jpeg"));
//    public static Image goodTank1U = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/GoodTank1U.jpeg"));
//    public static Image goodTank1R = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/GoodTank1R.jpeg"));
//    public static Image goodTank1D = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/GoodTank1D.jpeg"));
//    public static Image goodTank2L = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/GoodTank2L.jpeg"));
//    public static Image goodTank2U = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/GoodTank2U.jpeg"));
//    public static Image goodTank2R = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/GoodTank2R.jpeg"));
//    public static Image goodTank2D = toolkit.createImage(
//            v1.Blast.class.getClassLoader().getResource("images/GoodTank2D.jpeg"));
//    public static List<Image> images = new ArrayList<>();

//    public static void launch(){
//        for (int i = 0; i < 16; i++) {
//            Image image = toolkit.createImage(
//                    v1.Blast.class.getClassLoader().getResource("images/e" + i + ".gif"));
//            images.add(image);
//        }
//    }

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

//        g.drawImage(images.get(step),x,y,null);
//        step++;
//        if (step == 16){
//            life = false;
//        }
//        g.drawImage(e1,x,y,null);
//        g.drawImage(badTank2U,x,y,null);
//        g.drawImage(badTank1L,x,y,null);
//        g.drawImage(badTank1L,x,y,null);
//        g.drawImage(badTank1L,x,y,null);
//        g.drawImage(badTank1L,x,y,null);
//        g.drawImage(badTank1L,x,y,null);
//        g.drawImage(badTank1L,x,y,null);
//        g.drawImage(badTank1L,x,y,null);
//        g.drawImage(badTank1L,x,y,null);
//        g.drawImage(bulletL,x,y,null);
//        g.drawImage(bulletU,x,y,null);
//        g.drawImage(bulletR,x,y,null);
//        g.drawImage(goodTank2L,x,y,null);
        g.drawImage(images[step], x, y, null);
        step ++;
    }
}
