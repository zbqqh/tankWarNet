package v00;

import io.netty.buffer.ByteBuf;
import io.netty.channel.embedded.EmbeddedChannel;
import junit.framework.TestCase;

import java.util.UUID;
public class MyEncodeTest extends TestCase {

    public void testEncode() {
        EmbeddedChannel ch = new EmbeddedChannel();
        ch.pipeline().addLast(new MyEncode());
        Tank tank = new Tank(500,200,Direction.UP,true,true);
        TankJoinMsg msg = new TankJoinMsg(tank);
        ch.writeOutbound(msg);
        ByteBuf buf = ch.readOutbound();
        int length = buf.readInt();
        int x = buf.readInt();
        int y = buf.readInt();
        Direction direction  = Direction.values()[buf.readInt()];
        boolean group = buf.readBoolean();
        boolean stop = buf.readBoolean();
        UUID id = new UUID(buf.readLong(),buf.readLong());
        assertEquals(30,length);
        assertEquals(500,x);
        assertEquals(200,y);
        assertEquals(Direction.UP,direction);
        assertTrue(true);
        assertTrue(true);
        assertEquals(tank.id,id);
    }
}