package v00;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class NettyServerFrame extends Frame {
    public static final NettyServerFrame INSTANCE = new NettyServerFrame();
    public static final int WIDTH = 600;
    public static final int HEIGHT = 600;
    private NettyServerFrame(){}
    public static void main(String[] args) {
        NettyServerFrame.INSTANCE.launch();
        NettyServer.INSTANCE.launch();
    }
    public void launch(){
        setTitle("serverNetty");
        setBounds(500,200,WIDTH,HEIGHT);
        setBackground(Color.BLACK);
        TextArea taServer = new TextArea("server:");
        TextArea taClient = new TextArea("client:");
        taServer.setFont(new Font("verde",Font.PLAIN,25));
        taClient.setFont(new Font("verdernor",Font.PLAIN,25));

        setLayout(new GridLayout(1,2));
        add(taServer);
        add(taClient);

        setVisible(true);

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }
}
