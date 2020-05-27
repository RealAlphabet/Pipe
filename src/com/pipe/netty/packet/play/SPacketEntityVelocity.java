package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketEntityVelocity implements Packet<INetHandlerPlay> {

    private int entityId;
    private int motionX;
    private int motionY;
    private int motionZ;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketEntityVelocity(int entityId, double motionX, double motionY, double motionZ) {
        this.entityId = entityId;
        this.motionX = (int) (motionX * 8000.0D);
        this.motionY = (int) (motionY * 8000.0D);
        this.motionZ = (int) (motionZ * 8000.0D);
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarIntToBuffer(this.entityId);
        buf.writeShort(this.motionX);
        buf.writeShort(this.motionY);
        buf.writeShort(this.motionZ);
    }
}
