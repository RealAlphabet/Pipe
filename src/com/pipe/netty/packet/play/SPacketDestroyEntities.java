package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketDestroyEntities implements Packet<INetHandlerPlay> {

    private int[] entitiesId;

    public SPacketDestroyEntities(int... entitiesId) {
        this.entitiesId = entitiesId;
    }

    ///////////////////////////////////////////////////////////////////////////
    //  WRITE
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarIntToBuffer(entitiesId.length);

        for (int entityId : entitiesId) {
            buf.writeVarIntToBuffer(entityId);
        }
    }
}
