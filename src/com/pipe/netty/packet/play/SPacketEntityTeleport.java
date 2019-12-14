package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketEntityTeleport implements Packet<INetHandlerPlay> {

    private int entityId;
    private double posX;
    private double posY;
    private double posZ;
    private byte yawStep;
    private byte pitchStep;
    private boolean onGround;

    // TODO MODIFY WITH LOCATION UTIL AND USE STEP ANGLE (1/256)
    public SPacketEntityTeleport(int entityId, double posX, double posY, double posZ, float yawStep, float pitchStep, boolean onGround) {
        this.entityId = entityId;
        this.posX = posX;
        this.posY = posY;
        this.posZ = posZ;
        this.yawStep = (byte) yawStep;
        this.pitchStep = (byte) pitchStep;
        this.onGround = onGround;
    }


    ///////////////////////////////////////////////////////////////////////////
    //  SEND
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarIntToBuffer(entityId);
        buf.writeDouble(posX);
        buf.writeDouble(posY);
        buf.writeDouble(posZ);
        buf.writeByte(yawStep);
        buf.writeByte(pitchStep);
        buf.writeBoolean(onGround);
    }

    @Override
    public void process(INetHandlerPlay handler) {
    }
}
