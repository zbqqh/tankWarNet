package v00;

import java.awt.*;

public class Missile {

    public static final int WIDTH = 10;
    public static final int HEIGHT = 10;
    public static final int SPEED = 10;
    public int x;
    public int y;
    private boolean life = true;
    private Direction direction = Direction.UP;
    public boolean good = true;

    public static Toolkit toolkit = Toolkit.getDefaultToolkit();
    public static Image bulletL;
    public static Image bulletU;
    public static Image bulletR;
    public static Image bulletD;

    static {
        bulletL = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/bulletL.png"));
        bulletU = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/bulletU.png"));
        bulletR = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/bulletR.png"));
        bulletD = toolkit.createImage(
                Blast.class.getClassLoader().getResource("images/bulletD.png"));
    }

    public boolean isLife() {
        return life;
    }

    public void setLife() {
        if (x < 0 || x > 800 || y < 0 || y > 800) {
            life = false;
        }
    }

    public Missile(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Missile(int x, int y, v00.Direction direction, boolean good) {
        this(x, y);
        this.direction = direction;
        this.good = good;
    }

    public void paint(Graphics g) {
        if (!life) {
            NettyClientFrame.INSTANCE.missileList.remove(this);
            return;
        }
        move(g);
        setLife();
    }

    public void move(Graphics g) {
        switch (direction) {
            case LEFT:
                g.drawImage(bulletL,x,y,null);
                x -= SPEED;
                break;
        }
        switch (direction) {
            case UP:
                g.drawImage(bulletU,x,y,null);
                y -= SPEED;
                break;
        }
        switch (direction) {
            case RIGHT:
                g.drawImage(bulletR,x,y,null);
                x += SPEED;
                break;
        }
        switch (direction) {
            case DOWN:
                g.drawImage(bulletD,x,y,null);
                y += SPEED;
                break;
        }
    }

    public boolean hitTank(Tank tank){
        if (getRect().intersects(tank.getRect())){
            //爆炸画面
            life = false;
            tank.life = false;
            NettyClientFrame.INSTANCE.blastsList.add(new v00.Blast(tank.x, tank.y));
            return true;
        }
        return false;
    }

    private Rectangle getRect(){
        Rectangle rectangle = new Rectangle(x,y,WIDTH,HEIGHT);
        return rectangle;
    }
}
