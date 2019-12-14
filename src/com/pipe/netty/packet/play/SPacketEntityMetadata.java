package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketEntityMetadata implements Packet<INetHandlerPlay> {

    private int entityId;
    private byte FLAGS;

    public SPacketEntityMetadata(int entityId, byte FLAGS) {
        this.entityId = entityId;
        this.FLAGS = FLAGS;
    }

    @Override
    public void write(PacketBuffer buf) {
        buf.writeVarIntToBuffer(entityId);
        buf.writeByte(0x00); // BYTE
        buf.writeByte(0x00); // BYTE
        buf.writeByte(FLAGS);
        buf.writeByte(0xFF);
    }
}
