package v00;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class NettyClientFrame extends Frame {
    public static final NettyClientFrame INSTANCE = new NettyClientFrame();
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    Tank tank = new Tank();
    private NettyClientFrame(){}
    List<Tank> tankList = new ArrayList<>();
    List<Missile> missileList = new ArrayList<>();
    List<Blast> blastsList = new ArrayList<>();
    Image theBackImage;
    public static void main(String[] args) {
        INSTANCE.launch();
        NettyClient.INSTANCE.connectToServer();
    }

    private void launch() {
        setTitle("tankWar");
        setBounds(500,200,WIDTH,HEIGHT);
        setBackground(Color.BLACK);

        setVisible(true);
        tankList.add(tank);
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        addKeyListener(new MyKeyListener());

        new Thread(new MyThread()).start();

    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.GREEN);
        g.drawString("missiles counts" + missileList.size(), 20, 50);
        g.drawString("tankList counts" + tankList.size(), 20, 70);
        g.setColor(c);
        for (int i = 0; i < tankList.size(); i++) {
            Tank tank = tankList.get(i);
            tank.paint(g);
            tank.collidesWithTanks(tankList);

        }
        for (int j = 0; j < missileList.size(); j++) {
            Missile missile = missileList.get(j);
            missile.paint(g);
            if (missile.good != tank.group) {
                if (missile.hitTank(tank)) {
                    return;
                }
            }
            for (int i = 0; i < tankList.size(); i++) {
                Tank tank = tankList.get(i);
                if (missile.good != tank.group) {
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

    public Tank findTankByID(UUID id) {
        for (Tank tank : tankList){
            if (id.equals(tank.id))return tank;
        }
        return null;
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
}
