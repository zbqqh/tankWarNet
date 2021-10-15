package v00;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.concurrent.GlobalEventExecutor;

public class NettyServer {
    public static final ChannelGroup clients = new DefaultChannelGroup(
            GlobalEventExecutor.INSTANCE);
    public static final NettyServer INSTANCE = new NettyServer();
    private NettyServer(){}
    public void launch() {
        EventLoopGroup bossGroup = new NioEventLoopGroup(2);
        EventLoopGroup workerGroup = new NioEventLoopGroup(4);
        ServerBootstrap b = new ServerBootstrap();
        b.group(bossGroup,workerGroup);
        b.channel(NioServerSocketChannel.class);
        b.childHandler(new ChannelInitializer<SocketChannel>() {
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                //ch.pipeline().addLast(new MyDecode());
                ch.pipeline().addLast(new MyChannelHandler());
            }
            class MyChannelHandler extends ChannelInboundHandlerAdapter {
                @Override
                public void channelActive(ChannelHandlerContext ctx) throws Exception {
                    clients.add(ctx.channel());
                }

                @Override
                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                    System.out.println(msg.toString());
                    ByteBuf buf = (ByteBuf) msg;
//                    ctx.writeAndFlush(buf);
                    clients.writeAndFlush(buf);
                    //TankMsg tm = (TankMsg) msg;
                    //clients.writeAndFlush(tm.toString());
                }

                @Override
                public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
                    super.exceptionCaught(ctx, cause);
                }
            }
        });

        ChannelFuture channelFuture = null;
        channelFuture = b.bind(8888);

/*        try {
            channelFuture = b.bind(8888).sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/

/*        try {
            channelFuture.channel().close().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
/*        finally {
            bossGroup.shutdownGracefully();
            workerGroup.shutdownGracefully();
        }*/

    }
}
