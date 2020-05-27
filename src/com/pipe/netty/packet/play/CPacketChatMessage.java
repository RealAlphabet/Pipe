package com.pipe.netty.packet.play;

import com.pipe.netty.PacketBuffer;
import com.pipe.netty.handler.INetHandlerPlay;
import com.pipe.netty.packet.Packet;

public class CPacketChatMessage implements Packet<INetHandlerPlay> {

    private String message;

    ///////////////////////////////////////////////////////////////////////////

    @Override
    public void read(PacketBuffer buf) {
        message = buf.readStringFromBuffer(256);
    }

    @Override
    public void process(INetHandlerPlay handler) {
        handler.handleChatMessage(this);
    }

    ///////////////////////////////////////////////////////////////////////////

    public String getMessage() {
        return message;
    }
}
