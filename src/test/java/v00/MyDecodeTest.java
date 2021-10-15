package v00;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.embedded.EmbeddedChannel;
import org.junit.Test;

import java.util.UUID;

import static org.junit.Assert.*;

public class MyDecodeTest {

    @Test
    public void decode() {
        EmbeddedChannel ch = new EmbeddedChannel();
        ch.pipeline().addLast(new MyDecode());
        ByteBuf buf = Unpooled.buffer();
        UUID id = UUID.randomUUID();
        buf.writeInt(30);
        buf.writeInt(200);
        buf.writeInt(100);
        buf.writeInt(Direction.UP.ordinal());
        buf.writeBoolean(true);
        buf.writeBoolean(true);
        buf.writeLong(id.getMostSignificantBits());
        buf.writeLong(id.getLeastSignificantBits());

        ch.writeInbound(buf);
        TankJoinMsg msg = ch.readInbound();
        assertEquals(200,msg.x);
        assertEquals(100,msg.y);
        assertEquals(Direction.UP,msg.direction);
        assertTrue(msg.group);
        assertTrue(msg.stop);
        assertEquals(id,msg.id);
    }
}