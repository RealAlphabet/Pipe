package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketEntityStatus implements Packet<INetHandlerPlay> {

    private int entityId;
    private EnumEntityStatus status;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketEntityStatus(int entityId, EnumEntityStatus status) {
        this.entityId = entityId;
        this.status = status;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeInt(entityId);
        buf.writeByte(status.ordinal());
    }

    ///////////////////////////////////////////////////////////////////////////

    // TODO
    public enum EnumEntityStatus {
        TIPPED_ARROW,
        RABBIT_JUMP,
        HURT,
        DIE
    }
}
