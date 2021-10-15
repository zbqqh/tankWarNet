package v00;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class Tank {
    public static Random random = new Random();
    public int x = 100 + random.nextInt(9)*50;
    public int y = 100;
    public static int I = 0;
    public int oldX;
    public int oldY;
    public static final int WIDTH = 50;
    public static final int HEIGHT = 50;
    public static final int SPEED = 5;
    Direction direction = Direction.UP;
    boolean left, up, right, down;
    boolean stop = true;
    boolean group = true;
    UUID id = UUID.randomUUID();
    public boolean life = true;
    public static Direction[] directions = Direction.values();
    int step = 0;
    public int light;
    public static final int TIME = 20;

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

    public Tank(int x, int y, Direction ptDirection, boolean group, boolean stop) {
        this.x = x;
        this.y = y;
        this.direction = ptDirection;
        this.group = group;
        this.stop = stop;
        this.id = UUID.randomUUID();
        this.oldX = x;
        this.oldY = y;
    }

    public Tank(TankJoinMsg msg) {
        this.x = msg.x;
        this.y = msg.y;
        this.direction = msg.direction;
        this.group = msg.group;
        this.stop = msg.stop;
        this.id = msg.id;
    }

    public Tank() {
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
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.drawString("missilesCounts:" + NettyClientFrame.INSTANCE.missileList.size(), 10, 90);
        g.drawString(id.toString(),x,y - 10);
        g.setColor(c);
        if (!life) {
            NettyClientFrame.INSTANCE.tankList.remove(this);
            return;
        }

        if (group) {
            move(g);
        }
        if (!group) {

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
        if (light == TIME * 2) {
            light = 0;
        }
        if (group) {
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
        if (x >= NettyClientFrame.WIDTH - WIDTH - 5) {
            x = NettyClientFrame.WIDTH - WIDTH - 5;
        }
        if (y <= 35) {
            y = 35;
        }
        if (y >= NettyClientFrame.HEIGHT - HEIGHT - 5) {
            y = NettyClientFrame.HEIGHT - HEIGHT - 5;
        }
    }

    public void fire() {
        int missileX = x + WIDTH / 2 - Missile.WIDTH / 2;
        int missileY = y + HEIGHT / 2 - Missile.HEIGHT / 2;
        if (this.life) {
            Missile missile = new Missile(missileX, missileY, direction, group);
            NettyClientFrame.INSTANCE.missileList.add(missile);
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
