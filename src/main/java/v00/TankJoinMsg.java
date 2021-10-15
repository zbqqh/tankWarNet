package v00;

import java.io.*;
import java.util.UUID;

public class TankJoinMsg {
    int x,y;
    Direction direction;
    boolean group;
    boolean stop;
    UUID id;

    public TankJoinMsg() {
    }

    public TankJoinMsg(Tank p) {
        this.x = p.x;
        this.y = p.y;
        this.direction = p.direction;
        this.group = p.group;
        this.stop = p.stop;
        this.id = p.id;
    }

    public byte[] toBytes(){
        byte[] bytes = null;
        ByteArrayOutputStream bos = null;
        DataOutputStream dos = null;
        try {
            bos = new ByteArrayOutputStream();
            dos = new DataOutputStream(bos);
            dos.writeInt(x);
            dos.writeInt(y);
            dos.writeInt(direction.ordinal());
            dos.writeBoolean(group);
            dos.writeBoolean(stop);
            dos.writeLong(id.getMostSignificantBits());
            dos.writeLong(id.getLeastSignificantBits());
            dos.flush();
            bytes = bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (dos != null) {
                try {
                    dos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    public void parse(byte[] bytes) {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(bytes));
        try {
            this.x = dis.readInt();
            this.y = dis.readInt();
            this.direction = Direction.values()[dis.readInt()];
            this.group = dis.readBoolean();
            this.stop = dis.readBoolean();
            this.id = new UUID(dis.readLong(),dis.readLong());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                dis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {
        return "PlayerJoinMsg{" +
                "x=" + x +
                ", y=" + y +
                ", direction=" + direction +
                ", group=" + group +
                ", stop=" + stop +
                ", id=" + id +
                '}';
    }

    public void handler() {
        if (this.id.equals(NettyClientFrame.INSTANCE.tank.id))return;
        if (NettyClientFrame.INSTANCE.findTankByID(id) != null)return;
        Tank tank = new Tank(this);
        NettyClientFrame.INSTANCE.tankList.add(tank);
        NettyClient.INSTANCE.send(new TankJoinMsg(NettyClientFrame.INSTANCE.tank));
    }


}

