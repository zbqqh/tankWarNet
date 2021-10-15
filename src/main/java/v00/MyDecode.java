package v00;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.util.List;

public class MyDecode extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        if (in.readableBytes() < 34)
            return;;
        int length = in.readInt();
        System.out.println(length);
        byte[] bytes = new byte[length];
        in.readBytes(bytes);
        TankJoinMsg msg = new TankJoinMsg();
        msg.parse(bytes);
        out.add(msg);
        //out.add(new TankMsg(x,y));
    }
}
