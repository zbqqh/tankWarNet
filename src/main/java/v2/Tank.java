package v2;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;

public class Tank {
    public int x = 375;
    public int y = 740;
    public int oldX;
    public int oldY;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    public static final int SPEED = 5;
    MyFrame myFrame;
    Direction direction = Direction.UP;
    boolean left, up, right, down;
    boolean stop = true;
    boolean good = true;
    public boolean life = true;
    public static Random random = new Random();
    public static Direction[] directions = Direction.values();
    int step = 0;
    public int light;
    public static final int TIME= 20;

    public static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static Image badTank1L;
    public static Image badTank1U;
    public static Image badTank1R;
    public static Image badTank1D;
    public static Image badTank2L;
    public static Image badTank2U;
    public static Image badTank2R;
    public static Image badTank2D;
    public static Image goodTank1L;
    public static Image goodTank1U;
    public static Image goodTank1R;
    public static Image goodTank1D;
    public static Image goodTank2L;
    public static Image goodTank2U;
    public static Image goodTank2R;
    public static Image goodTank2D;

    public Tank(int x, int y, Direction ptDirection, boolean good, MyFrame myFrame) {
        this.x = x;
        this.y = y;
        this.direction = ptDirection;
        this.good = good;
        this.myFrame = myFrame;
        this.oldX = x;
        this.oldY = y;
    }

    public Tank(MyFrame myFrame) {
        this.myFrame = myFrame;
    }

    static {
        badTank1L = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/BadTank1L.png"));
        badTank1U = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/BadTank1U.png"));
        badTank1R = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/BadTank1R.png"));
        badTank1D = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/BadTank1D.png"));
        badTank2L = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/BadTank2L.png"));
        badTank2U = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/BadTank2U.png"));
        badTank2R = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/BadTank2R.png"));
        badTank2D = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/BadTank2D.png"));
        goodTank1L = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/GoodTank1L.png"));
        goodTank1U = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/GoodTank1U.png"));
        goodTank1R = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/GoodTank1R.png"));
        goodTank1D = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/GoodTank1D.png"));
        goodTank2L = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/GoodTank2L.png"));
        goodTank2U = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/GoodTank2U.png"));
        goodTank2R = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/GoodTank2R.png"));
        goodTank2D = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/GoodTank2D.png"));
    }

    public void paint(Graphics g) {
        if (!life) {
            myFrame.tankList.remove(this);
            return;
        }
      //  Color c = g.getColor();
   //     if (good) g.setColor(Color.MAGENTA);
     //   if (!good) g.setColor(Color.BLUE);
      //  g.fillOval(x, y, WIDTH, HEIGHT);
      //  g.drawRect(x, y, WIDTH, HEIGHT);
      //  g.setColor(c);
        if (good) {
            g.drawString("missilesCounts:" + myFrame.missileList.size(), 10, 50);
            move(g);
                                   // g.drawImage(v1.Tank.goodTank1U, x, y, null);
        }
        if (!good) {
            stop = false;
            if (step == 0) {
                step = random.nextInt(100) + 5;
                int rn = random.nextInt(directions.length);
                direction = directions[rn];
            }
            step--;//设置刷新一定次数后更换方向
            move(g);
            if (random.nextInt(50) > 48) {
//                fire();
            }
        }
        paintPT(g);
    }

    public void keyPressed(KeyEvent e) {
        int i = e.getKeyCode();
        if (i == KeyEvent.VK_LEFT || i == KeyEvent.VK_UP ||
                i == KeyEvent.VK_RIGHT || i == KeyEvent.VK_DOWN) {
            stop = false;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = true;
                break;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = true;
                break;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                right = true;
                break;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                down = true;
                break;
        }
        setDirection();
    }

    public void keyReleased2(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_SPACE:
                fire();
                break;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_Q:
                myFrame.creatTanks();
                break;
        }
    }

    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left = false;
                break;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                up = false;
                break;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
                right = false;
                break;
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_DOWN:
                down = false;
                break;
        }
        setDirection();
    }

    public void setDirection() {
        if (left && !up && !right && !down) {
            direction = Direction.LEFT;
        }
        if (!left && up && !right && !down) {
            direction = Direction.UP;
        }
        if (!left && !up && right && !down) {
            direction = Direction.RIGHT;
        }
        if (!left && !up && !right && down) {
            direction = Direction.DOWN;
        }
    }

    public void move(Graphics g) {
        oldX = x;
        oldY = y;
        if (!stop) {
            switch (direction) {
                case LEFT:
                    x -= SPEED;
                    break;
            }
            switch (direction) {
                case UP:
                    y -= SPEED;
                    break;
            }
            switch (direction) {
                case RIGHT:
                    x += SPEED;
                    break;
            }
            switch (direction) {
                case DOWN:
                    y += SPEED;
                    break;
            }
            if (!left && !up && !right && !down) {
                stop = true;
            }
        } else {
            x = x;
            y = y;
        }
        setBounds();
    }

    void paintPT(Graphics g) {
//        int centreX = x + WIDTH / 2;
//        int centreY = y + HEIGHT / 2;
        if (light >= TIME * 2) {
            light = 0;
        }
        if (good) {
            switch (direction) {
                case LEFT:
                    if (light < TIME) {
                        g.drawImage(Tank.goodTank1L, x, y, null);
                        light++;
                        //System.out.println("1");
                    }
                    if (light >= TIME) {
                        g.drawImage(Tank.goodTank2L, x, y, null);
                        light++;
                        //System.out.println("2");
                    }
                    //g.drawLine(centreX, centreY, x, y + v1.Tank.HEIGHT / 2);
                    break;
            }
            switch (direction) {
                case UP:
                    if (light < TIME) {
                        g.drawImage(Tank.goodTank1U, x, y, null);
                        light++;
                    }
                    if (light >= TIME) {
                        g.drawImage(Tank.goodTank2U, x, y, null);
                        light++;
                    }
//                g.drawLine(centreX, centreY, x + v1.Tank.WIDTH / 2, y);
                    break;
            }
            switch (direction) {
                case RIGHT:
                    if (light < TIME) {
                        g.drawImage(Tank.goodTank1R, x, y, null);
                        light++;
                    }
                    if (light >= TIME) {
                        g.drawImage(Tank.goodTank2R, x, y, null);
                        light++;
                    }
//                g.drawLine(centreX, centreY, x + v1.Tank.WIDTH, y + v1.Tank.HEIGHT / 2);
                    break;
            }
            switch (direction) {
                case DOWN:
                    if (light < TIME) {
                        g.drawImage(Tank.goodTank1D, x, y, null);
                        light++;
                    }
                    if (light >= TIME) {
                        g.drawImage(Tank.goodTank2D, x, y, null);
                        light++;
                    }
//                g.drawLine(centreX, centreY, x + v1.Tank.WIDTH / 2, y + v1.Tank.HEIGHT);
                    break;
            }
        } else {
            switch (direction) {
                case LEFT:
                    if (light < TIME) {
                        g.drawImage(Tank.badTank1L, x, y, null);
                        light ++;
                    } if (light >= TIME){
                        g.drawImage(Tank.badTank2L, x, y, null);
                        light ++;
                    }
                    break;
            }
            switch (direction) {
                case UP:
                    if (light < TIME) {
                        g.drawImage(Tank.badTank1U, x, y, null);
                        light ++;
                    } if (light >= TIME){
                    g.drawImage(Tank.badTank2U, x, y, null);
                    light ++;
                }
                    break;
            }
            switch (direction) {
                case RIGHT:
                if (light < TIME) {
                    g.drawImage(Tank.badTank1R, x, y, null);
                    light ++;
                } if (light >= TIME){
                    g.drawImage(Tank.badTank2R, x, y, null);
                    light ++;
                }
                    break;
            }
            switch (direction) {
                case DOWN:
                    if (light < TIME) {
                        g.drawImage(Tank.badTank1D, x, y, null);
                        light ++;
                    } if (light >= TIME){
                    g.drawImage(Tank.badTank2D, x, y, null);
                    light ++;
                }
                    break;
            }
        }

    }

    void setBounds() {
        if (x <= 5) {
            x = 5;
        }
        if (x >= MyFrame.WIDTH - WIDTH - 5) {
            x = MyFrame.WIDTH - WIDTH - 5;
        }
        if (y <= 35) {
            y = 35;
        }
        if (y >= MyFrame.HEIGHT - HEIGHT - 5) {
            y = MyFrame.HEIGHT - HEIGHT - 5;
        }
    }

    public void fire() {
        int missileX = x + WIDTH / 2 - Missile.WIDTH / 2;
        int missileY = y + HEIGHT / 2 - Missile.HEIGHT / 2;
        if (this.life) {
            Missile missile = new Missile(missileX, missileY, direction, myFrame, good);
            myFrame.missileList.add(missile);
        }
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y, WIDTH, HEIGHT);
        return rectangle;
    }

    public boolean collidesWithTank(Tank tank) {
        if (getRect().intersects(tank.getRect())) {
            back();
            return true;
        }
        return false;
    }

    public void collidesWithTanks(List<Tank> tanks) {
        for (int i = 0; i < tanks.size(); i++) {
            Tank tank = tanks.get(i);
            if (this != tank) {
                collidesWithTank(tank);
            }
        }
    }

    void back() {
        x = oldX;
        y = oldY;
    }
}
