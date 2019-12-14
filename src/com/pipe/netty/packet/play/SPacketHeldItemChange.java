package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class SPacketHeldItemChange implements Packet<INetHandlerPlay> {

    private int slot;

    public SPacketHeldItemChange(int slot) {
        this.slot = slot;
    }

    ///////////////////////////////////////////////////////////////////////////
    //  WRITE
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeByte(slot);
    }
}
