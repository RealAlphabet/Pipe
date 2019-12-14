package com.pipe.netty.packet;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerLogin;
import com.pipe.util.text.ITextComponent;

public class SPacketDisconnect implements Packet<INetHandlerLogin> {

    private ITextComponent reason;

    public SPacketDisconnect(ITextComponent reason) {
        this.reason = reason;
    }

    ///////////////////////////////////////////////////////////////////////////
    //  WRITE
    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void write(PacketBuffer buf) {
        buf.writeTextComponent(reason);
    }
}
