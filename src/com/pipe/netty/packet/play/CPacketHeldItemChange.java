package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class CPacketHeldItemChange implements Packet<INetHandlerPlay> {

    private int slotId;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        slotId = buf.readShort();
    }

    @Override
    public void write(PacketBuffer buf) {
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handleHeldItemChange(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public int getSlotId() {
        return slotId;
    }
}
