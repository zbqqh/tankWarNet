package v1;

import java.awt.*;

public class Wall {
    MyFrame myFrame;
    int x, y, width, height;

    public Wall(MyFrame myFrame, int x, int y, int width, int height) {
        this.myFrame = myFrame;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x, y, width, height);
        g.setColor(c);
    }

    public Rectangle getRect() {
        Rectangle rectangle = new Rectangle(x, y, width, height);
//        System.out.println(width);
        return rectangle;
    }
}
