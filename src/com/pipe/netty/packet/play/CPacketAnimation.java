package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;
import com.pipe.util.EnumHand;

public class CPacketAnimation implements Packet<INetHandlerPlay> {

    private EnumHand hand;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        hand = buf.readEnumValue(EnumHand.class);
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handleAnimation(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public EnumHand getHand() {
        return hand;
    }
}
