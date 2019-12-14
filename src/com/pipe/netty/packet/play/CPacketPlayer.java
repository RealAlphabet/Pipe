package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class CPacketPlayer implements Packet<INetHandlerPlay> {

    double x;
    double y;
    double z;
    float yaw;
    float pitch;
    boolean onGround;
    boolean moving;
    boolean rotating;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        onGround = buf.readUnsignedByte() != 0;
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handlePlayer(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public float getYaw() {
        return yaw;
    }

    public float getPitch() {
        return pitch;
    }

    public boolean isOnGround() {
        return onGround;
    }

    public boolean isMoving() {
        return moving;
    }

    public boolean isRotating() {
        return rotating;
    }

    ///////////////////////////////////////////////////////////////////////////

    public static class Position extends CPacketPlayer {

        public Position() {
            this.moving = true;
        }

        @Override
        public void read(PacketBuffer buf) {
            x = buf.readDouble();
            y = buf.readDouble();
            z = buf.readDouble();
            super.read(buf);
        }
    }

    ///////////////////////////////////////////////////////////////////////////

    public static class PositionRotation extends CPacketPlayer {

        public PositionRotation() {
            this.moving = true;
            this.rotating = true;
        }

        @Override
        public void read(PacketBuffer buf) {
            x = buf.readDouble();
            y = buf.readDouble();
            z = buf.readDouble();
            yaw = buf.readFloat();
            pitch = buf.readFloat();
            super.read(buf);
        }
    }

    ///////////////////////////////////////////////////////////////////////////

    public static class Rotation extends CPacketPlayer {

        public Rotation() {
            this.rotating = true;
        }

        @Override
        public void read(PacketBuffer buf) {
            yaw = buf.readFloat();
            pitch = buf.readFloat();
            super.read(buf);
        }
    }
}
