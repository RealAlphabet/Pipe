package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketEntityHeadLook implements Packet<INetHandlerPlay> {

    private int entityId;
    private byte yaw;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketEntityHeadLook(int entityId, byte yaw) {
        this.entityId = entityId;
        this.yaw = yaw;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarIntToBuffer(entityId);
        buf.writeByte(yaw);
    }
}
