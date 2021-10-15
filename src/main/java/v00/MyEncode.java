package v00;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

public class MyEncode extends MessageToByteEncoder<TankJoinMsg> {
    @Override
    protected void encode(ChannelHandlerContext ctx, TankJoinMsg msg, ByteBuf out) throws Exception {
        byte[] bytes = msg.toBytes();
        out.writeInt(bytes.length);
        out.writeBytes(bytes);
    }
}
