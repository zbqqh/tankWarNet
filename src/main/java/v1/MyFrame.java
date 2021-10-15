package v1;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class MyFrame extends Frame {
    public static final int WIDTH = 800;
    public static final int HEIGHT = 800;
    int x = 500;
    int y = 50;
    Tank tank = new Tank(this);

    List<Tank> tankList = new ArrayList<>();
    List<Missile> missileList = new ArrayList<>();
    List<Blast> blastsList = new ArrayList<>();
    Image theBackImage;
    Wall wall = new Wall(this, 600, 200, 50, 400);
    Wall wall2 = new Wall(this, 100, 200, 300, 50);

    int tanksCounts;
    Properties props = new Properties();

    public void launchFrame() {
        setLocation(x, y);
        setSize(WIDTH, HEIGHT);
        setBackground(Color.BLACK);
        setVisible(true);
        addWindowListener(new MyWindowListener());
        addKeyListener(new MyKeyListener());
        new Thread(new MyThread()).start();
        tankList.add(tank);
//        v1.Blast.launch();

        try {
            props.load(this.getClass().getClassLoader().getResourceAsStream(
                    "config/tanks.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        tanksCounts = Integer.parseInt(props.getProperty("tanksCounts"));
//        System.out.println(tanksCounts);
        creatTanks();

    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.drawString("missiles counts" + missileList.size(), 20, 50);
        g.drawString("tankList counts" + tankList.size(), 20, 70);
        g.setColor(c);
        wall.paint(g);
        wall2.paint(g);
        for (int i = 0; i < tankList.size(); i++) {
            Tank tank = tankList.get(i);
            tank.paint(g);
            tank.collidesWithWall(wall);
            tank.collidesWithWall(wall2);
            tank.collidesWithTanks(tankList);

        }
        for (int j = 0; j < missileList.size(); j++) {
            Missile missile = missileList.get(j);
            missile.paint(g);
            if (missile.good != tank.good) {
                if (missile.hitTank(tank)) {
                    return;
                }
            }
            for (int i = 0; i < tankList.size(); i++) {
                Tank tank = tankList.get(i);
                if (missile.good != tank.good) {
                    if (missile.hitTank(tank)) {
                        return;//炮弹打中坦克，此次循环结束，换下一个炮弹循环
                    }
                }
            }
        }
        for (int i = 0; i < blastsList.size(); i++) {
            Blast blast = blastsList.get(i);
            blast.paint(g);
        }
    }

    @Override
    public void update(Graphics g) {
        theBackImage = createImage(WIDTH, HEIGHT);
        Graphics graphics = theBackImage.getGraphics();
        paint(graphics);
        g.drawImage(theBackImage, 0, 0, null);

    }

    class MyKeyListener extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            tank.keyPressed(e);
        }

        @Override
        public void keyReleased(KeyEvent e) {
            tank.keyReleased(e);
            tank.keyReleased2(e);
        }
    }

    class MyWindowListener extends WindowAdapter {
        @Override
        public void windowClosing(WindowEvent e) {
            System.out.println("程序退出");
            System.exit(0);
        }
    }

    class MyThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.currentThread().sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                repaint();
            }
        }
    }


    public void creatTanks() {
        for (int i = 0; i < tanksCounts; i++) {
            tankList.add(new Tank(100 + 100 * i, 100, Direction.DOWN, false, this));
        }
    }
}




