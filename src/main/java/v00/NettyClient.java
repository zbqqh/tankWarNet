package v00;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class NettyClient {
    public static final NettyClient INSTANCE = new NettyClient();
    Channel channel = null;
    private NettyClient(){}
    public void connectToServer() {
        EventLoopGroup group = new NioEventLoopGroup(1);
        Bootstrap b = new Bootstrap();
        b.group(group);
        b.channel(NioSocketChannel.class);
        b.handler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                channel = ch;
                ch.pipeline()
                        .addLast(new MyEncode())
                .addLast(new MyDecode());

                ch.pipeline().addLast(new MyChannelHandler());
            }
            class MyChannelHandler extends ChannelInboundHandlerAdapter {
                @Override
                public void channelActive(ChannelHandlerContext ctx) throws Exception {

                    ctx.writeAndFlush(new TankJoinMsg(NettyClientFrame.INSTANCE.tank));
                }

                @Override
                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                    TankJoinMsg tm = (TankJoinMsg) msg;
                    System.out.println(tm);
                    //new Player().handler(pm);
                    tm.handler();
                }

                @Override
                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                    super.exceptionCaught(ctx, cause);
                }
            }
        });
        b.connect("localhost",8888);

    }

    public void send(TankJoinMsg msg) {
        channel.writeAndFlush(msg);
    }
}
