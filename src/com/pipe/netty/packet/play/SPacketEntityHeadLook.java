package com.pipe.netty.packet.play;

import com.pipe.entity.Entity;
import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketEntityHeadLook implements Packet<INetHandlerPlay> {

    private int entityId;
    private byte yaw;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketEntityHeadLook(Entity entity, byte yaw) {
        this.entityId = entity.id;
        this.yaw = yaw;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarIntToBuffer(entityId);
        buf.writeByte(yaw);
    }
}
