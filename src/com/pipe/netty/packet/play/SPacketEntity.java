package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketEntity implements Packet<INetHandlerPlay> {

    protected int entityId;
    protected int posX;
    protected int posY;
    protected int posZ;
    protected byte yaw;
    protected byte pitch;
    protected boolean onGround;

    public SPacketEntity(int entityId) {
        this.entityId = entityId;
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarIntToBuffer(entityId);
    }

    public static class SPacketEntityRelMove extends SPacketEntity {

        public SPacketEntityRelMove(int entityId, short posX, short posY, short posZ, boolean onGround) {
            super(entityId);
            this.posX = posX;
            this.posY = posY;
            this.posZ = posZ;
            this.onGround = onGround;
        }

        @Override
        public void write(PacketBuffer buf) {
            super.write(buf);
            buf.writeShort(posX);
            buf.writeShort(posY);
            buf.writeShort(posZ);
            buf.writeBoolean(onGround);
        }
    }

    public static class SPacketEntityLook extends SPacketEntity {

        public SPacketEntityLook(int entityId, byte yaw, byte pitch, boolean onGround) {
            super(entityId);
            this.yaw = yaw;
            this.pitch = pitch;
            this.onGround = onGround;
        }

        @Override
        public void write(PacketBuffer buf) {
            super.write(buf);
            buf.writeByte(yaw);
            buf.writeByte(pitch);
            buf.writeBoolean(onGround);
        }
    }

    public static class SPacketEntityLookMove extends SPacketEntity {

        public SPacketEntityLookMove(int entityId, short posX, short posY, short posZ, byte yaw, byte pitch, boolean onGround) {
            super(entityId);
            this.posX = posX;
            this.posY = posY;
            this.posZ = posZ;
            this.yaw = yaw;
            this.pitch = pitch;
            this.onGround = onGround;
        }

        @Override
        public void write(PacketBuffer buf) {
            super.write(buf);
            buf.writeShort(posX);
            buf.writeShort(posY);
            buf.writeShort(posZ);
            buf.writeByte(yaw);
            buf.writeByte(pitch);
            buf.writeBoolean(onGround);
        }
    }
}
