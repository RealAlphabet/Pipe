package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketCamera implements Packet<INetHandlerPlay> {

    public int entityId;

    ///////////////////////////////////////////////////////////////////////////

    public SPacketCamera(int entityId) {
        this.entityId = entityId;
    }

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarIntToBuffer(entityId);
    }

    @Override
    public void process(INetHandlerPlay handler) {
    }
}
